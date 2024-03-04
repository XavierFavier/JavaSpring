package com.example.demo.dao;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
//@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String uuid;

    private String name;

    private String password;

    public Users() {

    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {return password; }
    public void setPassword(String name) {
        this.password = password;
    }
}
