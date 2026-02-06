package com.messageApp.messageApp.mensagem;

import com.messageApp.messageApp.usuario.Usuario;

public record MensagemResponseDTO(Long id, String mensagem, Usuario sender, Usuario receiver) {
    public MensagemResponseDTO(Mensagem msg) {
        this(msg.getId(), msg.getMensagem(), msg.getSender(), msg.getReceiver());
    }
}
