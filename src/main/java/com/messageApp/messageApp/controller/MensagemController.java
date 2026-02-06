package com.messageApp.messageApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageApp.messageApp.mensagem.Mensagem;
import com.messageApp.messageApp.mensagem.MensagemRepository;
import com.messageApp.messageApp.mensagem.MensagemRequestDTO;
import com.messageApp.messageApp.mensagem.MensagemResponseDTO;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    @Autowired 
    private MensagemRepository mensagemRepository;

    @PostMapping
    public void criarMensagem(@RequestBody MensagemRequestDTO data) {
        Mensagem mensagem = new Mensagem(data);
        mensagemRepository.save(mensagem);
    }

    @GetMapping
    public List<MensagemResponseDTO> getAll() {
        List<MensagemResponseDTO> listaMensagens = mensagemRepository.findAll().stream().map(MensagemResponseDTO::new).toList();
        return listaMensagens;
    }
}
