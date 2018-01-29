package com.rezwanislam.oauth2demo.repository;


import com.rezwanislam.oauth2demo.model.MongoOAuth2ClientToken;

public interface MongoOAuth2ClientTokenRepositoryBase {
    boolean deleteByAuthenticationId(String authenticationId);

    MongoOAuth2ClientToken findByAuthenticationId(String authenticationId);
}
