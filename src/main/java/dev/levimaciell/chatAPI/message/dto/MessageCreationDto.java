package dev.levimaciell.chatAPI.message.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record MessageCreationDto(
        @NotBlank
        String message,
        @NotBlank
        UUID receiverId
) {
}
