package dev.levimaciell.chatAPI.user.validations.updateUser;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.exceptions.UserUpdateValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidateNullValues implements Validation<UserUpdateDto> {

    @Override
    public void validate(UserUpdateDto value) {
        if(value.username() == null && value.password() == null)
            throw new UserUpdateValidationException("Both username and password fields of request body are empty!");
    }
}
