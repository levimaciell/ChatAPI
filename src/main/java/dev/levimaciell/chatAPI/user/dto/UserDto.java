package dev.levimaciell.chatAPI.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(

        @NotBlank(message = "É necessário informar um nome de usuário para criação do usuário!")
        @Size(min = 3, message = "O nome de usuário não pode ser menor que 3 caracteres!")
        String username,

        @NotBlank(message = "É necessário informar um email para criação do usuário!")
        @Email
        String email,

        @NotBlank
        @Pattern(
                regexp = "/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$/\n",
                message = "\"A senha deve ter entre 8 e 16 caracteres, incluir pelo menos uma letra maiúscula" +
                        ", uma letra minúscula, um número e um caractere especial.\""
        )
        String password
) {
}
