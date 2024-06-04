package dev.levimaciell.chatAPI.usuario.controller;

import dev.levimaciell.chatAPI.usuario.TokenResponseDto;
import dev.levimaciell.chatAPI.usuario.dto.UserDto;
import dev.levimaciell.chatAPI.usuario.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<TokenResponseDto> criarUsuario
            (@RequestBody @Valid UserDto dto, HttpServletRequest req){

        usuarioService.criarUsuario(dto);


        return ResponseEntity.ok(new TokenResponseDto());
    }



}
