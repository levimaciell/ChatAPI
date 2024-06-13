package dev.levimaciell.chatAPI.user.service;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.entity.User;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private List<Validation<UserUpdateDto>> validacoesUpdate;

    @Autowired
    private PasswordEncoder encoder;

    public void createUser(UserDto dto) {
        var updatedDto = new UserDto(dto.username(), dto.email(), encoder.encode(dto.password()));
        var user = new User(updatedDto);
        repository.save(user);
    }

    public void deleteUser(String username){
        var user = repository.getReferenceByUsername(username);
        if(user == null || !user.getUserActive())
            throw new RuntimeException("User not found with given id!");

        user.setUserActive(false);

    }

    public User updateUser(UserUpdateDto dto, String username) {

        //Validating the given DTO
        validacoesUpdate.forEach(v -> v.validate(dto));

        var foundUser = repository.getReferenceByUsername(username);

        if(foundUser == null)
                throw new RuntimeException("It was not possible to update any info.");

        if(dto.username() != null)
            foundUser.setUsername(dto.username());

        if(dto.password() != null)
            foundUser.setPassword(encoder.encode(dto.password()));

        return foundUser;
    }
}
