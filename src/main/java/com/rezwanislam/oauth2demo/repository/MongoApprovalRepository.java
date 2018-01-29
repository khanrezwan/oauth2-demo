package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.MongoApproval;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoApprovalRepository extends MongoRepository<MongoApproval, String>, MongoApprovalRepositoryBase {
}
