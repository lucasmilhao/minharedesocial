package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageApp.messageApp.postagem.Postagem;
import com.messageApp.messageApp.postagem.PostagemRepository;
import com.messageApp.messageApp.postagem.PostagemRequestDTO;
import com.messageApp.messageApp.postagem.PostagemResponseDTO;
import com.messageApp.messageApp.service.PostagemService;
import com.messageApp.messageApp.service.UsuarioService;

@RestController
@RequestMapping("usuarios/{idUsuario}/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public List<PostagemResponseDTO> getAll (@PathVariable Long idUsuario) {
        List<PostagemResponseDTO> listaPostagens = postagemRepository
        .findByPoster(usuarioService.getUsuarioById(idUsuario))
        .stream()
        .map(PostagemResponseDTO::new)
        .toList();
        
        return listaPostagens;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> getPostagem(@PathVariable Long idUsuario, @PathVariable Long id) {
        Postagem post = postagemService.verificarCaminhoPostagem(idUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    @PostMapping
    public ResponseEntity<PostagemResponseDTO> criarPostagem (@RequestBody PostagemRequestDTO data, @PathVariable Long idUsuario) {

        if(!usuarioService.isUser(idUsuario)) return ResponseEntity.notFound().build();

        Postagem post = postagemService.criarPostagem(data, idUsuario);
        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> editarPostagem(@PathVariable Long idUsuario, @PathVariable Long id, @RequestBody PostagemRequestDTO data) {
        Postagem post = postagemService.verificarCaminhoPostagem(idUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();
        
        if(data.descricao() != null && !data.descricao().isBlank()) post.setDescricao(data.descricao());

        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable Long idUsuario, @PathVariable Long id) {

        Postagem post = postagemService.verificarCaminhoPostagem(idUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();

        postagemRepository.delete(post);
        return ResponseEntity.ok().build();
    }
}
