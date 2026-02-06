package com.messageApp.messageApp.usuario;

public record UsuarioResponseDTO(Long id, String nome, String email, String senha) {
    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getSenha());
    }
}
