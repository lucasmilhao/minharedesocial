package com.messageApp.messageApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.messageApp.messageApp.postagem.Postagem;
import com.messageApp.messageApp.postagem.PostagemRepository;
import com.messageApp.messageApp.postagem.PostagemRequestDTO;
import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioRepository;

@Service
public class PostagemService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem criarPostagem(@RequestBody PostagemRequestDTO dto, Long idUsuario) {
        
        Usuario user = usuarioRepository.getReferenceById(idUsuario);

        Postagem post = new Postagem(dto);
        post.setPoster(user);

        return postagemRepository.save(post);
    }

    public Postagem verificarCaminhoPostagem(String nomeUsuario, Long idPostagem) {
        Usuario user = usuarioRepository.findByNomeIgnoreCase(nomeUsuario).orElseThrow(() -> new RuntimeException());

        if(!postagemRepository.existsById(idPostagem)) return null;
        if(user == null) return null;

        
        Postagem post = postagemRepository.getReferenceById(idPostagem);

        if(!post.getPoster().getId().equals(user.getId())) {
            return null;
        }

        return post;
    }
}
