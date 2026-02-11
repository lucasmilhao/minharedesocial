package com.messageApp.messageApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.messageApp.messageApp.postagem.Postagem;
import com.messageApp.messageApp.postagem.PostagemRepository;
import com.messageApp.messageApp.postagem.PostagemRequestDTO;
import com.messageApp.messageApp.postagem.PostagemResponseDTO;
import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioRepository;

@Service
public class PostagemService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public ResponseEntity<PostagemResponseDTO> criarPostagem(@RequestBody PostagemRequestDTO dto, String nomeUsuario) {
        
        Usuario user = usuarioRepository.findByNomeIgnoreCase(nomeUsuario).orElseThrow(() -> new RuntimeException());   
        
        Postagem post = new Postagem(dto);
        post.setPoster(user);

        postagemRepository.save(post);

        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    public List<PostagemResponseDTO> getAll() {
        return postagemRepository.findAll().stream().map(PostagemResponseDTO::new).toList();
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
