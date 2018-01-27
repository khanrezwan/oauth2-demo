package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);

}
