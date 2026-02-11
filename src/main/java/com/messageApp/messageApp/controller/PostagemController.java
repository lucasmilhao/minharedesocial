package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.messageApp.messageApp.usuario.Usuario;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("usuarios/{nomeUsuario}/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public ResponseEntity<List<PostagemResponseDTO>> getAll (@PathVariable String nomeUsuario) {
        Usuario usuario = usuarioService.getUsuarioByNome(nomeUsuario).orElse(null);

        if(usuario == null) return ResponseEntity.notFound().build();
        
        List<PostagemResponseDTO> listaPostagens = postagemRepository
        .findByPoster(usuario)
        .stream()
        .map(PostagemResponseDTO::new)
        .toList();
        
        return ResponseEntity.ok(listaPostagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> getPostagem(@PathVariable String nomeUsuario, @PathVariable Long id) {
        Postagem post = postagemService.verificarCaminhoPostagem(nomeUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    @PostMapping
    public ResponseEntity<PostagemResponseDTO> criarPostagem (@RequestBody PostagemRequestDTO data, @PathVariable String nomeUsuario) {
        return postagemService.criarPostagem(data, nomeUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostagemResponseDTO> editarPostagem(@PathVariable String nomeUsuario, @PathVariable Long id, @RequestBody PostagemRequestDTO data) {
        Postagem post = postagemService.verificarCaminhoPostagem(nomeUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();
        
        if(data.descricao() != null && !data.descricao().isBlank()) post.setDescricao(data.descricao());

        postagemRepository.save(post);

        return ResponseEntity.ok(new PostagemResponseDTO(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable String nomeUsuario, @PathVariable Long id) {

        Postagem post = postagemService.verificarCaminhoPostagem(nomeUsuario, id);

        if(post == null) return ResponseEntity.notFound().build();

        postagemRepository.delete(post);
        return ResponseEntity.ok().build();
    }
}
