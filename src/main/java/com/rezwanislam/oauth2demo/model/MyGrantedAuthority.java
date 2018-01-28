package com.rezwanislam.oauth2demo.model;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    private String name;

    public MyGrantedAuthority(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return this.name;
    }
}
