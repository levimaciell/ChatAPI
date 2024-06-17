package dev.levimaciell.chatAPI.user.validations.updateUser;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.exceptions.UserUpdateValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidateBlankValues implements Validation<UserUpdateDto> {

    @Override
    public void validate(UserUpdateDto value) {
        if(value.username() != null && value.username().isBlank())
            throw new UserUpdateValidationException("When username is informed, it cannot be blank");

        if(value.password() != null && value.password().isBlank())
            throw new UserUpdateValidationException("When password is informed, it cannot be blank");
    }
}
