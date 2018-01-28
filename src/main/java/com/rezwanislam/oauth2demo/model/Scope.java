package com.rezwanislam.oauth2demo.model;

public class Scope {
    private  String name;

    public Scope(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
