package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String>, UserRepositoryBase {
    User findUserByUsername(String username);
}
