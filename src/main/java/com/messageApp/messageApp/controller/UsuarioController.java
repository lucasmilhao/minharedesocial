package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioRepository;
import com.messageApp.messageApp.usuario.UsuarioRequestDTO;
import com.messageApp.messageApp.usuario.UsuarioResponseDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioRequestDTO data) {
        if(data.nome() == null || data.email() == null || data.senha() == null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario user = new Usuario(data);
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUser(@PathVariable Long id) {
        if(usuarioRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario user = usuarioRepository.getReferenceById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO data) {
        return usuarioRepository.findById(id).map(
            user -> {
                if(data.nome() != null && !data.nome().isBlank()) user.setNome(data.nome());
                if(data.email() != null && !data.email().isBlank()) user.setEmail(data.email());
                if(data.senha() != null && !data.senha().isBlank()) user.setSenha(data.senha());
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
