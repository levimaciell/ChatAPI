package dev.levimaciell.chatAPI.user.validations.updateUser;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.exceptions.UserUpdateValidationException;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateAlreadyExistingUsername implements Validation<UserUpdateDto> {
    @Autowired
    private UserRepository repository;

    @Override
    public void validate(UserUpdateDto value) {
        var foundUsername = repository.findByUsername(value.username());

        if(foundUsername.isPresent())
            throw new UserUpdateValidationException("It is not possible to update your username to " + value.username());
    }
}
