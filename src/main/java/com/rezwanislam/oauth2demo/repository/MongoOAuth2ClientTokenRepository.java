package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.MongoOAuth2ClientToken;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoOAuth2ClientTokenRepository extends MongoRepository<MongoOAuth2ClientToken, String>, MongoOAuth2ClientTokenRepositoryBase {
}
