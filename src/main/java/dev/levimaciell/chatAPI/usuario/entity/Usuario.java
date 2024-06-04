package dev.levimaciell.chatAPI.usuario.entity;

import dev.levimaciell.chatAPI.usuario.dto.UserDto;

import java.util.UUID;

public class Usuario {

    private UUID id;
    private String username;
    private String email;
    private String password;

    public Usuario(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }

    public Usuario(UserDto dto) {
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
