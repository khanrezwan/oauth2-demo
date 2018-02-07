package com.rezwanislam.oauth2demo.service;

import com.rezwanislam.oauth2demo.model.MongoOAuth2AccessToken;
import com.rezwanislam.oauth2demo.model.MongoOAuth2RefreshToken;
import com.rezwanislam.oauth2demo.repository.MongoOAuth2AccessTokenRepository;
import com.rezwanislam.oauth2demo.repository.MongoOAuth2RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MongoTokenStore implements TokenStore {
    private final MongoOAuth2AccessTokenRepository mongoOAuth2AccessTokenRepository;
    private final AuthenticationKeyGenerator authenticationKeyGenerator;
    private final MongoOAuth2RefreshTokenRepository mongoOAuth2RefreshTokenRepository;

    @Autowired
    public MongoTokenStore(MongoOAuth2AccessTokenRepository mongoOAuth2AccessTokenRepository, AuthenticationKeyGenerator authenticationKeyGenerator,
                           MongoOAuth2RefreshTokenRepository mongoOAuth2RefreshTokenRepository) {
        this.mongoOAuth2AccessTokenRepository = mongoOAuth2AccessTokenRepository;
        this.authenticationKeyGenerator = authenticationKeyGenerator;
        this.mongoOAuth2RefreshTokenRepository = mongoOAuth2RefreshTokenRepository;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return readAuthentication(oAuth2AccessToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String tokenValue) {
        final String tokenId = extractTokenKey(tokenValue);
        final MongoOAuth2AccessToken mongoOAuth2AccessToken = mongoOAuth2AccessTokenRepository.findByTokenId(tokenId);
        if (mongoOAuth2AccessToken != null) {
            try {
                return deserializeAuthentication(mongoOAuth2AccessToken.getAuthentication());
            } catch (IllegalArgumentException e) {
                removeAccessToken(tokenValue);
            }
        }
        return null;
    }

    protected String extractTokenKey(String value) {
        return value;
    }

    @Override
    public void storeAccessToken(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication oAuth2Authentication) {
        String refreshToken = null;
        if (oAuth2AccessToken.getRefreshToken() != null) {
            refreshToken = oAuth2AccessToken.getRefreshToken().getValue();
        }
        if (readAccessToken(oAuth2AccessToken.getValue()) != null) {
            readAccessToken(oAuth2AccessToken.getValue());
        }
        final String tokenKey = extractTokenKey(oAuth2AccessToken.getValue());
        final MongoOAuth2AccessToken oAuth2AccessToken1 = new MongoOAuth2AccessToken(tokenKey, serializeAccessToken(oAuth2AccessToken),
                authenticationKeyGenerator.extractKey(oAuth2Authentication), oAuth2Authentication.isClientOnly() ? null : oAuth2Authentication.getName(),
                oAuth2Authentication.getOAuth2Request().getClientId(), serializeAuthentication(oAuth2Authentication),
                extractTokenKey(refreshToken));
        mongoOAuth2AccessTokenRepository.save(oAuth2AccessToken1);
    }

    @Override
    public OAuth2AccessToken readAccessToken(final String tokenValue) {
        final String tokenKey = extractTokenKey(tokenValue);
        final MongoOAuth2AccessToken mongoOAuth2AccessToken = mongoOAuth2AccessTokenRepository.findByTokenId(tokenKey);
        if (mongoOAuth2AccessToken != null) {
            try {
                return deserializeAccessToken(mongoOAuth2AccessToken.getToken());
            } catch (IllegalArgumentException e) {
                removeAccessToken(tokenValue);
            }
        }
        return null;
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        removeAccessToken(oAuth2AccessToken.getValue());
    }

    public void removeAccessToken(final String tokenValue) {
        final String tokenKey = extractTokenKey(tokenValue);
        mongoOAuth2AccessTokenRepository.deleteByTokenId(tokenKey);
    }

    @Override
    public void storeRefreshToken(final OAuth2RefreshToken oAuth2RefreshToken, final OAuth2Authentication oAuth2Authentication) {

        final String tokenKey = extractTokenKey(oAuth2RefreshToken.getValue());
        final byte[] token = serializeRefreshToken(oAuth2RefreshToken);
        final byte[] authentication = serializeAuthentication(oAuth2Authentication);

        final MongoOAuth2RefreshToken mongoOAuth2RefreshToken = new MongoOAuth2RefreshToken(tokenKey, token, authentication);

        mongoOAuth2RefreshTokenRepository.save(mongoOAuth2RefreshToken);
    }


    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        final String tokenKey = extractTokenKey(tokenValue);
        final MongoOAuth2RefreshToken mongoOAuth2RefreshToken = mongoOAuth2RefreshTokenRepository.findByTokenId(tokenKey);

        if (mongoOAuth2RefreshToken != null) {
            try {
                return deserializeRefreshToken(mongoOAuth2RefreshToken.getToken());
            } catch (IllegalArgumentException e) {
                removeRefreshToken(tokenValue);
            }
        }

        return null;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return readAuthenticationForRefreshToken(oAuth2RefreshToken.getValue());

    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        removeRefreshToken(oAuth2RefreshToken.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        removeAccessTokenUsingRefreshToken(oAuth2RefreshToken.getValue());
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;

        String key = authenticationKeyGenerator.extractKey(authentication);

        final MongoOAuth2AccessToken oAuth2AccessToken = mongoOAuth2AccessTokenRepository.findByAuthenticationId(key);

        if (oAuth2AccessToken != null) {
            accessToken = deserializeAccessToken(oAuth2AccessToken.getToken());
        }

        if (accessToken != null
                && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            removeAccessToken(accessToken.getValue());
            // Keep the store consistent (maybe the same user is represented by this authentication but the details have
            // changed)
            storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        final List<MongoOAuth2AccessToken> oAuth2AccessTokens = mongoOAuth2AccessTokenRepository.findByUsernameAndClientId(userName, clientId);
        final Collection<OAuth2AccessToken> noNullsTokens= new ArrayList<>();
        oAuth2AccessTokens.forEach(mongoOAuth2AccessToken -> {
            if(mongoOAuth2AccessToken !=null){
                noNullsTokens.add((OAuth2AccessToken)mongoOAuth2AccessToken); //todo check
            }
        });
        return  noNullsTokens;
        //= filter(oAuth2AccessTokens, byNotNulls());
        //return transform(noNullsTokens, toOAuth2AccessToken());
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        final List<MongoOAuth2AccessToken> mongoOAuth2AccessTokens = mongoOAuth2AccessTokenRepository.findByClientId(clientId);
        final Collection<OAuth2AccessToken> oAuth2AccessTokens = new ArrayList<>();
        oAuth2AccessTokens.forEach(mongoOAuth2AccessToken -> {
            if(mongoOAuth2AccessToken !=null){
                oAuth2AccessTokens.add((OAuth2AccessToken)mongoOAuth2AccessToken); //todo check
            }
        });
        return oAuth2AccessTokens;
    }

    protected byte[] serializeAccessToken(OAuth2AccessToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
        return SerializationUtils.serialize(token);
    }

    protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
        return SerializationUtils.serialize(authentication);
    }

    protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
        return SerializationUtils.deserialize(token);
    }

    protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
        return SerializationUtils.deserialize(authentication);

    }
    public void removeRefreshToken(String token) {
        final String tokenId = extractTokenKey(token);
        mongoOAuth2RefreshTokenRepository.deleteByTokenId(tokenId);
    }

    public void removeAccessTokenUsingRefreshToken(final String refreshToken) {
        final String tokenId = extractTokenKey(refreshToken);
        mongoOAuth2AccessTokenRepository.deleteByRefreshTokenId(tokenId);

    }
    public OAuth2Authentication readAuthenticationForRefreshToken(String value) {
        final String tokenId = extractTokenKey(value);

        final MongoOAuth2RefreshToken mongoOAuth2RefreshToken = mongoOAuth2RefreshTokenRepository.findByTokenId(tokenId);

        if (mongoOAuth2RefreshToken != null) {
            try {
                return deserializeAuthentication(mongoOAuth2RefreshToken.getAuthentication());
            } catch (IllegalArgumentException e) {
                removeRefreshToken(value);
            }
        }

        return null;
    }
}
