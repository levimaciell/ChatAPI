package dev.levimaciell.chatAPI.user.validations.createUser;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.exceptions.UserCreationValidationException;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateUsernameAlreadyExists implements Validation<UserDto> {

    @Autowired
    private UserRepository repository;

    @Override
    public void validate(UserDto value) {
        if(repository.existsByUsername(value.username()))
            throw new UserCreationValidationException("It is not possible to create an user with this username");
    }
}
//org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: