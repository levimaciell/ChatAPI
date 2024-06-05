package dev.levimaciell.chatAPI.user.entity;

import dev.levimaciell.chatAPI.user.dto.UserDto;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
        username = dto.username();
        email = dto.email();
        password = dto.password();
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
