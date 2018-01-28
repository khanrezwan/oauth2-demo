package com.rezwanislam.oauth2demo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotNull;
import java.util.*;

@Document
public class Client implements ClientDetails {

    @Id
    private ObjectId id;

    @NotNull
    @Indexed(unique = true)
    private String clientId;
    @NotNull
    private String clientSecret;

    private Set<String> scope;

    @NotNull
    private Set<String> authorizedGrantTypes;

    public Client(String clientId, String clientSecret, Set<String> scope) {
        this.clientSecret = clientSecret;
        this.clientId = clientId;
        this.scope = scope;
        this.authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add("password");
        authorizedGrantTypes.add("authorization_code");
        authorizedGrantTypes.add("refresh_token");

        //this.authorizedGrantTypes = new AuthorizedGrantTypes();
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {


        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new MyGrantedAuthority("password"));
        authorities.add(new MyGrantedAuthority("refresh_token"));
        return  authorities;

    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 120;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 120;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
