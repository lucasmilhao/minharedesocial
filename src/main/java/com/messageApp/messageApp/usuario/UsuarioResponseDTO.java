package com.messageApp.messageApp.usuario;

public record UsuarioResponseDTO(Long id, String nome, String email, String fotoPerfil) {
    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(), user.getNome(), user.getEmail(), user.getFotoPerfil());
    }
}
