package com.messageApp.messageApp.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageApp.messageApp.dto.LoginRequestDTO;
import com.messageApp.messageApp.dto.RegisterRequestDTO;
import com.messageApp.messageApp.dto.ResponseDTO;
import com.messageApp.messageApp.infra.security.TokenService;
import com.messageApp.messageApp.usuario.Usuario;
import com.messageApp.messageApp.usuario.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Usuario user = usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.senha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<Usuario> user = usuarioRepository.findByEmail(body.email());
        if(user.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setSenha(passwordEncoder.encode(body.senha()));
            usuario.setEmail(body.email());
            usuario.setNome(body.nome());
            this.usuarioRepository.save(usuario);
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
