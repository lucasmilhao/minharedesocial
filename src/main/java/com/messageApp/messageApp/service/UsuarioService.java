package com.messageApp.messageApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isUser(Long id) {
        return usuarioRepository.existsById(id);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.getReferenceById(id);
    }
}
