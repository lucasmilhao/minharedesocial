package com.messageApp.messageApp.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeIgnoreCase(String nome);
    Optional<Usuario> findByNome(String nome);
    
    Optional<Usuario> findByEmailIgnoreCase(String email);
    Optional<Usuario> findByEmail(String email);
}
