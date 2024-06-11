package dev.levimaciell.chatAPI.authentication;

import dev.levimaciell.chatAPI.tokens.TokenResponseDto;
import dev.levimaciell.chatAPI.tokens.TokenService;
import dev.levimaciell.chatAPI.user.entity.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    @Transactional
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginDto dto){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = manager.authenticate(authenticationToken);

        var token = service.createToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDto(token));
    }


}
