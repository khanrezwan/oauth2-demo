package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String>, MongoClientDetailsRepositoryBase{
   // Client findByClientId(String id);

}
