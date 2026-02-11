package com.messageApp.messageApp.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "usuario")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Column(name="foto_perfil")
    private String fotoPerfil;

    public Usuario(UsuarioRequestDTO request) {
        this.nome = request.nome();
        this.email = request.email();
        this.senha = request.senha();
    }
}
