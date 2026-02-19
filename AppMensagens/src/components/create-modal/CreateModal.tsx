import { useState } from "react";
import { useUsuarioLogado } from "../../hooks/usuarios/useUsuarioLogado"
import type { UsuarioPut } from "../../interface/UsuarioPut";
import { api } from "../../service/api";
import { useNavigate } from "react-router-dom";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import "./modal.css"

interface ModalProps {
    closeModal() : void;
}

export function CreateModal( {closeModal} : ModalProps ) {
    const usuario = useUsuarioLogado();
    const [nome, setNome] = useState(usuario.data?.nome);
    const [fotoPerfil, setFotoPerfil] = useState(usuario.data?.fotoPerfil);
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    const mutate = useMutation({
        mutationFn: (data : UsuarioPut) => {
            return api.put(`/usuarios/me`, data)
        },
        onSuccess : () => {
            queryClient.invalidateQueries({queryKey: ["usuario-data"]})
            navigate(`/${nome}`)
            closeModal()
        } 
    });

    const editUsuario = () => {
        mutate.mutate({
            fotoPerfil,
            nome
        })
    }

    return (
        <div className="modal-overlay">
            <div className="modal-body">
            <button className="btn-primary" onClick={closeModal}>X</button>
                <p>Edite sua foto de perfil:</p>
                <input aria-label="image" type="text" value={fotoPerfil} onChange={e => setFotoPerfil(e.target.value)} placeholder={usuario.data?.fotoPerfil}/>
                <p>Seu nome de Usuario:</p>
                <input aria-label="name" type="text" value={nome} onChange={e => setNome(e.target.value)} placeholder={usuario.data?.nome}/>
                <button className="btn-secondary" onClick={editUsuario}>{mutate.isPending? "Carregando..." : "Aplicar"}</button>
            </div>
        </div>
    )
}