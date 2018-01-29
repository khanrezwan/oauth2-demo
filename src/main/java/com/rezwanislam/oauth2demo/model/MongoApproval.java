package com.rezwanislam.oauth2demo.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Document
public class MongoApproval extends Approval{

    @Id
    private String id;
    private String userId;
    private String clientId;
    private String scope;
    private ApprovalStatus status;
    @CreatedDate
    private LocalDateTime expiresAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    public MongoApproval() {
    }

    @PersistenceConstructor
    public MongoApproval(final String id,
                         final String userId,
                         final String clientId,
                         final String scope,
                         final ApprovalStatus status,
                         final LocalDateTime expiresAt,
                         final LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        this.scope = scope;
        this.status = status;
        this.expiresAt = expiresAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getScope() {
        return scope;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public Date getExpiresAt() {
        //from https://stackoverflow.com/questions/19431234/converting-between-java-time-localdatetime-and-java-util-date
        return   Date.from(this.expiresAt.atZone(ZoneId.systemDefault()).toInstant());
     //   return date;
    }

    public Date getLastUpdatedAt() {
        return Date.from(this.lastUpdatedAt.atZone(ZoneId.systemDefault()).toInstant());

    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, clientId, scope, status, expiresAt, lastUpdatedAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MongoApproval other = (MongoApproval) obj;
        return Objects.equals(this.userId, other.userId)
                && Objects.equals(this.clientId, other.clientId)
                && Objects.equals(this.scope, other.scope)
                && Objects.equals(this.status, other.status)
                && Objects.equals(this.expiresAt, other.expiresAt)
                && Objects.equals(this.lastUpdatedAt, other.lastUpdatedAt);
    }

    @Override
    public String toString() {
        return "MongoApproval{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", scope='" + scope + '\'' +
                ", status=" + status +
                ", expiresAt=" + expiresAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
