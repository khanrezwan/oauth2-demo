package com.rezwanislam.oauth2demo.repository;

import com.rezwanislam.oauth2demo.model.MongoApproval;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface MongoApprovalRepositoryBase {
    boolean updateOrCreate(Collection<MongoApproval> mongoApprovals);

    boolean updateExpiresAt(Date now, MongoApproval mongoApproval);

    boolean deleteByUserIdAndClientIdAndScope(MongoApproval mongoApproval);

    List<MongoApproval> findByUserIdAndClientId(String userId, String clientId);
}
