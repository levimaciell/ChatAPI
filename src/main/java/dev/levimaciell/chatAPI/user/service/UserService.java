package dev.levimaciell.chatAPI.user.service;

import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.entity.User;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void createUser(UserDto dto) {
        var usuario = new User(dto);
        repository.save(usuario);
    }

    public void deleteUser(UUID id){
        var user = repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with given id!")
        );

        if(!user.getUsuarioAtivo())
            throw new RuntimeException("User not found with given id!");

        user.setUsuarioAtivo(false);

    }

    public void updateUser(UserUpdateDto dto) {

    }
}
