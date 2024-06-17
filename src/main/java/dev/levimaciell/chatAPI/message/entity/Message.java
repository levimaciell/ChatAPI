package dev.levimaciell.chatAPI.message.entity;

import dev.levimaciell.chatAPI.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Message")
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
    private Boolean isSeen;

    public Message() {
    }

    public Message(String message, User senderUser, User receiverUser) {
        this.message = message;
        this.creationTime = LocalDateTime.now();
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.isSeen = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }
}
