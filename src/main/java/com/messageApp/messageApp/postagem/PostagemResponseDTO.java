package com.messageApp.messageApp.postagem;

import com.messageApp.messageApp.usuario.Usuario;

public record PostagemResponseDTO(Long id_postagem, String imagem, String descricao, Usuario poster) {
   public PostagemResponseDTO(Postagem postagem) {
        this(postagem.getId_postagem(), postagem.getImagem(), postagem.getDescricao(), postagem.getPoster());
   }
}
