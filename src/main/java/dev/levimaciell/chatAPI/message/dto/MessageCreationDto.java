package dev.levimaciell.chatAPI.message.dto;

import java.util.UUID;

public record MessageCreationDto(
        String message,
        UUID receiverId
) {
}
