package dev.levimaciell.chatAPI.usuario.service;

import dev.levimaciell.chatAPI.usuario.dto.UserDto;
import dev.levimaciell.chatAPI.usuario.entity.Usuario;
import dev.levimaciell.chatAPI.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void criarUsuario(UserDto dto) {
        var usuario = new Usuario(dto);

    }
}
