package com.rezwanislam.oauth2demo.repository;


import com.rezwanislam.oauth2demo.model.MongoOAuth2AccessToken;

import java.util.List;

public interface MongoOAuth2AccessTokenRepositoryBase {
    MongoOAuth2AccessToken findByTokenId(String tokenId);

    boolean deleteByTokenId(String tokenId);

    boolean deleteByRefreshTokenId(String refreshTokenId);

    MongoOAuth2AccessToken findByAuthenticationId(String key);

    List<MongoOAuth2AccessToken> findByUsernameAndClientId(String username, String clientId);

    List<MongoOAuth2AccessToken> findByClientId(String clientId);
}