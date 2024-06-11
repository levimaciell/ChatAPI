package dev.levimaciell.chatAPI.authentication;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
