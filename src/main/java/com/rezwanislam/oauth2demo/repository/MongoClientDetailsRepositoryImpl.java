package com.rezwanislam.oauth2demo.repository;

import com.mongodb.WriteResult;
import com.rezwanislam.oauth2demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class MongoClientDetailsRepositoryImpl implements MongoClientDetailsRepositoryBase {

    public static final String ID = "_id";
    public static final String CLIENT_SECRET = "clientSecret";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoClientDetailsRepositoryImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean deleteByClientId(String clientId) {
        final Query query = Query.query(Criteria.where(ID).is(clientId));
        final WriteResult writeResult = mongoTemplate.remove(query, Client.class);
        return writeResult.getN() == 1;
    }

    @Override
    public boolean update(final Client mongoClientDetails) {
        final Query query = Query.query(Criteria.where(ID).is(mongoClientDetails.getClientId()));

        final Update update = Update.update("scope", mongoClientDetails.getScope())
                .set("resourceIds", mongoClientDetails.getResourceIds())
                .set("authorizedGrantTypes", mongoClientDetails.getAuthorizedGrantTypes())
                .set("authorities", mongoClientDetails.getAuthorities())
                .set("accessTokenValiditySeconds", mongoClientDetails.getAccessTokenValiditySeconds())
                .set("refreshTokenValiditySeconds", mongoClientDetails.getRefreshTokenValiditySeconds())
                .set("additionalInformation", mongoClientDetails.getAdditionalInformation())
                .set("autoApproveScopes", mongoClientDetails.getAutoApproveScopes())
                .set("registeredRedirectUris", mongoClientDetails.getRegisteredRedirectUri());

        final WriteResult writeResult = mongoTemplate.updateFirst(query, update, Client.class);

        return writeResult.getN() == 1;
    }

    @Override
    public boolean updateClientSecret(final String clientId,
                                      final String newSecret) {
        final Query query = Query.query(Criteria.where(ID).is(clientId));

        final Update update = Update.update(CLIENT_SECRET, newSecret);

        final WriteResult writeResult = mongoTemplate.updateFirst(query, update, Client.class);

        return writeResult.getN() == 1;
    }

    @Override
    public Client findByClientId(final String clientId) throws IllegalArgumentException {
        final Query query = Query.query(Criteria.where(ID).is(clientId));
        final Client mongoClientDetails = mongoTemplate.findOne(query, Client.class);
        if (mongoClientDetails == null) {
            throw new IllegalArgumentException("No valid client id");
        }
        return mongoClientDetails;
    }


}
