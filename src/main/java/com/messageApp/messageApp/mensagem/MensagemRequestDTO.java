package com.messageApp.messageApp.mensagem;

import com.messageApp.messageApp.usuario.Usuario;

public record MensagemRequestDTO(String mensagem, Usuario sender, Usuario receiver) {
    
}
