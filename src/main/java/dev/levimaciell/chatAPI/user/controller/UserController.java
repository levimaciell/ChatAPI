package dev.levimaciell.chatAPI.user.controller;

import dev.levimaciell.chatAPI.user.TokenResponseDto;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser
            (@RequestBody @Valid UserDto dto, HttpServletRequest req){

        userService.createUser(dto);

        return new ResponseEntity(null, HttpStatus.CREATED);
    }



}
