package com.rezwanislam.oauth2demo.service;

import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MongoApprovalStore implements ApprovalStore{
    @Override
    public boolean addApprovals(Collection<Approval> collection) {
        return false;
    }

    @Override
    public boolean revokeApprovals(Collection<Approval> collection) {
        return false;
    }

    @Override
    public Collection<Approval> getApprovals(String s, String s1) {
        return null;
    }
}
