package com.project.management.devboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String name;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean activated;
    private Set<Authority> authorities = new HashSet<>();

    public User(String username, String password, String role, String name) {
        this.username = username;
        this.password = password;
        setAuthorities(role);
        setName(name);
    }

    public String getAuthoritiesString() {
        String authString = "";
        for (Authority auth : authorities) {
            if (authString.length() == 0) {
                authString += auth.getName();
            } else {
                authString += "," + auth.getName();
            }
        }
        return authString;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for(String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role.toUpperCase();
            this.authorities.add(new Authority(authority));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                activated == user.activated &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, activated, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                '}';
    }
}

