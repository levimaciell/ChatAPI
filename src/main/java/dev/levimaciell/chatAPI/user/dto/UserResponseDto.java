package dev.levimaciell.chatAPI.user.dto;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username
) {
}
