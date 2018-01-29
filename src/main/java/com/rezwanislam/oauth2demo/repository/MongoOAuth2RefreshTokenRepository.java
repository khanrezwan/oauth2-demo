package com.rezwanislam.oauth2demo.repository;


import com.rezwanislam.oauth2demo.model.MongoOAuth2RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoOAuth2RefreshTokenRepository extends MongoRepository<MongoOAuth2RefreshToken, String>, MongoOAuth2RefreshTokenRepositoryBase {
}
