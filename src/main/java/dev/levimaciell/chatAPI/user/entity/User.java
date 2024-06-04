package dev.levimaciell.chatAPI.user.entity;

import dev.levimaciell.chatAPI.user.dto.UserDto;

import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String email;
    private String password;

    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public User(UserDto dto) {
    }



    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
