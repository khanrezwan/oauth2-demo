package com.rezwanislam.oauth2demo.model;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document
public class User implements UserDetails{
    @Id
    private ObjectId id;

    @NotNull
    private String username;

    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    private Boolean accountNonExpired;

    @NotNull
    private Boolean enabled;

    @NotNull
    private String password;

    public User(String username, String email, Boolean accountNonExpired, Boolean enabled, String password) {
        this.username = username;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.enabled = enabled;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return authorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
}
