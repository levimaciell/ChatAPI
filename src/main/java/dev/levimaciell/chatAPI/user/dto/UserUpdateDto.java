package dev.levimaciell.chatAPI.user.dto;

import jakarta.validation.constraints.Pattern;

public record UserUpdateDto(

        String username,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
                message = "A senha deve no mínimo 8 caracteres, incluir pelo menos uma letra maiúscula" +
                        ", uma letra minúscula, um número e um caractere especial.")
        String password

) {
}
