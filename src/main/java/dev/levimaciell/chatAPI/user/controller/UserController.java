package dev.levimaciell.chatAPI.user.controller;

import dev.levimaciell.chatAPI.tokens.TokenResponseDto;
import dev.levimaciell.chatAPI.tokens.TokenService;
import dev.levimaciell.chatAPI.user.dto.UserDto;
import dev.levimaciell.chatAPI.user.dto.UserUpdateDto;
import dev.levimaciell.chatAPI.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Users",
        description = "Endpoint for managing users"
)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/sign-up")
    @Transactional
    @Operation(
            summary = "Creates a new user",
            description = "Creates a new user. It's necessary to have one to send and receive messages. " +
                    "Endpoint does not require authentication",
            tags = {"Users"}
    )
    public ResponseEntity createUser(@RequestBody @Valid UserDto dto){

        userService.createUser(dto);

        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/users")
    @Transactional
    @Operation(
            summary = "Deletes a user",
            description = "Removes an user from the service, not being able to be used again. Needs authentication.",
            tags = {"Users"}
    )
    public ResponseEntity deleteUser(HttpServletRequest req) {

        var subject = tokenService.validateTokenAndGetSubject(req.getHeader("Authorization")
                .replace("Bearer ", ""));

        userService.deleteUser(subject);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users")
    @Transactional
    @Operation(
            summary = "Updates a user",
            description = "Updates an user. You can only change the username and the password(following " +
                    "the same constraints of when creating it). Needs authentication.",
            tags = {"Users"}
    )
    public ResponseEntity<TokenResponseDto> updateUser(@RequestBody @Valid UserUpdateDto dto,
                                                       HttpServletRequest req){

        var subject = tokenService.validateTokenAndGetSubject(req.getHeader("Authorization")
                .replace("Bearer ", ""));

        var user = userService.updateUser(dto, subject);

        var token = tokenService.createToken(user);
        return ResponseEntity.ok(new TokenResponseDto(token));
    }




}
