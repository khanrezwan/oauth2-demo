package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.MongoOAuth2AccessToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOAuth2AccessTokenRepository extends MongoRepository<MongoOAuth2AccessToken, String>, MongoOAuth2AccessTokenRepositoryBase {

}
