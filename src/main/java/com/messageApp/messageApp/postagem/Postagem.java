package com.messageApp.messageApp.postagem;

import com.messageApp.messageApp.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="postagem")
@Table(name="postagem")
public class Postagem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_postagem;
    private String imagem;
    private String descricao;

    @ManyToOne
    @JoinColumn(name="id_poster", nullable=false)
    private Usuario poster;

    public Postagem(PostagemRequestDTO post) {
        this.imagem = post.imagem();
        this.descricao = post.descricao();
    }
}
