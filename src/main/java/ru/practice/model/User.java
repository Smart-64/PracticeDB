package ru.practice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class User {

    private String id;
    private String username;
    private String nickname;

    public User() {

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
