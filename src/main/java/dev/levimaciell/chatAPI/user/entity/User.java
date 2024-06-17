package dev.levimaciell.chatAPI.user.entity;

import dev.levimaciell.chatAPI.message.entity.Message;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private Boolean userActive;

    @OneToMany(mappedBy = "senderUser")
    private List<Message> sentMessages;
    @OneToMany(mappedBy = "receiverUser")
    private List<Message> receivedMessages;


    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        userActive = true;
    }

    public User() {
    }

    public User(UserDto dto) {
        username = dto.username();
        email = dto.email();
        password = dto.password();
        userActive = true;
    }

    public Boolean getUserActive() {
        return userActive;
    }

    public UUID getId() {
        return id;
    }

    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
