package dev.levimaciell.chatAPI.message.repository;

import dev.levimaciell.chatAPI.message.entity.Message;
import dev.levimaciell.chatAPI.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("""
            SELECT m FROM Message m
            WHERE m.senderUser = :user or
            m.receiverUser = :user
            """)
    Page<Message> findAllByUsername(Pageable pageable, User user);
}
