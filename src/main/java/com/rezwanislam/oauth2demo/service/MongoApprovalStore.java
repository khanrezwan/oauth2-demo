package com.rezwanislam.oauth2demo.service;

import com.rezwanislam.oauth2demo.model.MongoApproval;
import com.rezwanislam.oauth2demo.repository.MongoApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class MongoApprovalStore implements ApprovalStore{
    private final MongoApprovalRepository mongoApprovalRepository;

    public void setHandleRevocationsAsExpiry(boolean handleRevocationsAsExpiry) {
        this.handleRevocationsAsExpiry = handleRevocationsAsExpiry;
    }

    private boolean handleRevocationsAsExpiry = false;

    @Autowired
    public MongoApprovalStore(final MongoApprovalRepository mongoApprovalRepository) {
       this.mongoApprovalRepository = mongoApprovalRepository;
    }

    @Override
    public boolean addApprovals(Collection<Approval> approvals) {
        final Collection<MongoApproval> mongoApprovals = new ArrayList<>();

        approvals.forEach(approval -> {
            mongoApprovals.add((MongoApproval) approval); //todo will it work?
        });

        return this.mongoApprovalRepository.updateOrCreate(mongoApprovals);
    }

    @Override
    public boolean revokeApprovals(Collection<Approval> approvals) {
        final boolean[] success = {true};
        final Collection<MongoApproval> mongoApprovals = new ArrayList<>();
        approvals.forEach(approval -> {
            mongoApprovals.add((MongoApproval) approval); //todo will it work?
        });
        mongoApprovals.forEach(mongoApproval -> {
            if (handleRevocationsAsExpiry) {
            final boolean updateResult = mongoApprovalRepository.updateExpiresAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), mongoApproval);
            if (!updateResult) {
                success[0] = false;
            }}
            else {
                final boolean deleteResult = mongoApprovalRepository.deleteByUserIdAndClientIdAndScope(mongoApproval);
                if(!deleteResult){
                   success[0] = false;
                }
            }
        });
        return success[0];

    }

    @Override
    public Collection<Approval> getApprovals(String userId, String clientId) {
        final List<MongoApproval> mongoApprovals = mongoApprovalRepository.findByUserIdAndClientId(userId,clientId);
        final Collection<Approval> approvals = new ArrayList<>();
        final boolean b = approvals.addAll(mongoApprovals);

        return approvals;
    }
}
