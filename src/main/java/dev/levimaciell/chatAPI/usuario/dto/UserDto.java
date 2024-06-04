package dev.levimaciell.chatAPI.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(

        @NotBlank(message = "É necessário informar um nome de usuário para criação do usuário!")
        @Size(min = 3, message = "O nome de usuário não pode ser menor que 3 caracteres!")
        String username,

        @NotBlank(message = "É necessário informar um email para criação do usuário!")
        @Email
        String email,

        @NotBlank
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres!")
        //TODO: Adicionar verificações extras da senha
        String password
) {
}
