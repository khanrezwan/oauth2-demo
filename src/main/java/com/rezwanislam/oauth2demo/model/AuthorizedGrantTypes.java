package com.rezwanislam.oauth2demo.model;

public enum AuthorizedGrantTypes {
    PASSWORD("password"),
    RefreshToken("refresh_token");

    private final String name;

    AuthorizedGrantTypes(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
