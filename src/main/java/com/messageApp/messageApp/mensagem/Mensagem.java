package com.messageApp.messageApp.mensagem;

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
@Table
@Entity(name= "mensagem")
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    @JoinColumn(name="id_sender")
    private Usuario sender;

    @ManyToOne
    @JoinColumn(name="id_receiver")
    private Usuario receiver;

    public Mensagem(MensagemRequestDTO data) {
        this.mensagem = data.mensagem();
        this.sender = data.sender();
        this.receiver = data.receiver();
    }
}
