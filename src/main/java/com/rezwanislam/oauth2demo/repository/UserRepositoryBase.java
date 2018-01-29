package com.rezwanislam.oauth2demo.repository;

public interface UserRepositoryBase {

    boolean changePassword(String oldPassword, String newPassword, String username);

}
