package com.messageApp.messageApp.postagem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messageApp.messageApp.usuario.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
    List<Postagem> findByPoster(Usuario user);
}
