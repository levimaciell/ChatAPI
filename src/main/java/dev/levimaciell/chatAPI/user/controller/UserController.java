package dev.levimaciell.chatAPI.user.controller;

import dev.levimaciell.chatAPI.tokens.TokenResponseDto;
import dev.levimaciell.chatAPI.tokens.TokenService;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.service.UserService;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid UserDto dto){

        userService.createUser(dto);

        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users")
    @Transactional
    public ResponseEntity<TokenResponseDto> updateUser(@RequestBody @Valid UserUpdateDto dto,
                                                       HttpServletRequest req){

        var subject = tokenService.validateTokenAndGetSubject(req.getHeader("Authorization")
                .replace("Bearer ", ""));

        var user = userService.updateUser(dto, subject);

        var token = tokenService.createToken(user);
        return ResponseEntity.ok(new TokenResponseDto(token));
    }




}
