package dev.levimaciell.chatAPI.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MessageCreationDto(
        @NotBlank
        String message,
        @NotNull
        UUID receiverId
) {
}
