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

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private Boolean usuarioAtivo;


    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        usuarioAtivo = true;
    }

    public User() {
    }

    public User(UserDto dto) {
        username = dto.username();
        email = dto.email();
        password = dto.password();
        usuarioAtivo = true;
    }

    public Boolean getUsuarioAtivo() {
        return usuarioAtivo;
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

    public void setUsuarioAtivo(Boolean usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }
}
