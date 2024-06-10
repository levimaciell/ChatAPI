package dev.levimaciell.chatAPI.user.service;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.entity.User;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private List<Validation<UserUpdateDto>> validacoesUpdate;


    public void createUser(UserDto dto) {
        var usuario = new User(dto);
        repository.save(usuario);
    }

    public void deleteUser(UUID id){
        var user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with given id!")
        );

        if(!user.getUserActive())
            throw new RuntimeException("User not found with given id!");

        user.setUserActive(false);

    }

    //TODO: Trocar esquema do UUID pelo username do Token JWT futuramente
    public void updateUser(UserUpdateDto dto, UUID id) {

        //Validating the given DTO
        validacoesUpdate.forEach(v -> v.validate(dto));

        var foundUser = repository.findById(id).orElseThrow(() ->
                new RuntimeException("The given id is invalid!"));

        if(dto.username() != null)
            foundUser.setUsername(dto.username());

        if(dto.password() != null)
            foundUser.setPassword(dto.password());

    }
}
