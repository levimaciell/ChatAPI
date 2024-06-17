package dev.levimaciell.chatAPI.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(

        @NotBlank(message = "To create an user, it's necessary to have a username!")
        @Size(min = 3, message = "Username must have at least 3 characters")
        String username,

        @NotBlank(message = "To create an user, it's necessary to have an email!")
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
         message = "Password must have the following requisites: " +
                 "At least 8 characters, a lowercase letter, an uppercase letter, " +
                 "a number and a special character.")
//TODO: Transformar cada pequena validação dessa em um pequeno erro.
        String password
) {
}
