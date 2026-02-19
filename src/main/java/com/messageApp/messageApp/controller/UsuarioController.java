package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.messageApp.messageApp.infra.security.TokenService;
import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioPutRequestDTO;
import com.messageApp.messageApp.usuario.UsuarioRepository;
import com.messageApp.messageApp.usuario.UsuarioRequestDTO;
import com.messageApp.messageApp.usuario.UsuarioResponseDTO;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUsuario(@RequestBody UsuarioRequestDTO data) {
        if(data.nome() == null || data.email() == null || data.senha() == null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario user = new Usuario(data);
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponseDTO(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMe(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<UsuarioResponseDTO> getUser(@PathVariable String nome, Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Usuario user = usuarioRepository.findByNomeIgnoreCase(nome).
        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(usuarioLogado.getId().equals(user.getId())) return getMe(usuarioLogado);

        return ResponseEntity.ok(new UsuarioResponseDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if(usuarioRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(@AuthenticationPrincipal Usuario usuario, @RequestBody UsuarioPutRequestDTO data) {
        return usuarioRepository.findById(usuario.getId()).map(
            user -> {
                if(data.nome() != null && !data.nome().isBlank()) user.setNome(data.nome());
                if(data.fotoPerfil() != null && !data.fotoPerfil().isBlank()) user.setFotoPerfil(data.fotoPerfil());
                usuarioRepository.save(user);
                return ResponseEntity.ok(new UsuarioResponseDTO(user));
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAll() {
        List<UsuarioResponseDTO> listaUsuarios = usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return listaUsuarios;
    }

}
