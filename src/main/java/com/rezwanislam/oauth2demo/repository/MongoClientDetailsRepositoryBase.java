package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.Client;

public interface MongoClientDetailsRepositoryBase {
    boolean deleteByClientId(String clientId);

    boolean update(Client mongoClientDetails);

    boolean updateClientSecret(String clientId, String newSecret);

    Client findByClientId(String clientId) throws IllegalArgumentException;
}
