import { useState } from "react";
import { useUsuarioLogado } from "../../hooks/usuarios/useUsuarioLogado"
import type { UsuarioPut } from "../../interface/UsuarioPut";
import { api } from "../../service/api";
import { useNavigate } from "react-router-dom";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import "./modal.css"
import type { PostProps } from "../../interface/PostProps";
import { postPostagem } from "../../service/postPostagemService";

interface ModalProps {
    closeModal() : void;
}

export function PostModal( {closeModal} : ModalProps ) {
    const [imagem, setImagem] = useState("")
    const [descricao, setDescricao] = useState("")
    const queryClient = useQueryClient();
    const {data : usuario} = useUsuarioLogado();
    const navigate = useNavigate();


    const mutation = useMutation ({
        mutationFn: ( data : PostProps) => {
            return postPostagem( usuario?.nome, data )
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["postagem-data"]})
            closeModal()
        }
    })

    const submit = () => {
        if(!usuario?.nome)return
        
        mutation.mutate ({
            imagem,
            descricao
        })
    }

    return (
        <div className="modal-overlay">
            <div className="modal-body">
            <button className="btn-primary" onClick={closeModal}>X</button>
                <p>Coloque a imagem que quer postar:</p>
                <input id="imagem-input" aria-label="image" type="text" value={imagem} onChange={e => setImagem(e.target.value)} placeholder="selecione sua imagem"/>
                <p>Descricao:</p>
                <input id="descricao-input" aria-label="name" type="text" value={descricao} onChange={e => setDescricao(e.target.value)} placeholder="descrição"/>
                <button id="botao-postar" className="btn-secondary" onClick={submit}>{mutation.isPending? "Carregando..." : "Aplicar"}</button>
            </div>
        </div>
    )
}