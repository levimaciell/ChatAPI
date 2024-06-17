package dev.levimaciell.chatAPI.message.dto;

import dev.levimaciell.chatAPI.message.entity.Message;
import dev.levimaciell.chatAPI.user.dto.UserResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDto(
        UUID messageID,
        String message,
        UserResponseDto sender,
        UserResponseDto receiver,
        Boolean isMessageSeen,
        LocalDateTime sendDate
) {
    public MessageDto(Message message){
        this(message.getId(), message.getMessage(),
                new UserResponseDto(message.getSenderUser().getId(), message.getSenderUser().getUsername()),
                new UserResponseDto(message.getReceiverUser().getId(), message.getReceiverUser().getUsername()),
                message.getSeen(), message.getCreationTime());
    }
}
