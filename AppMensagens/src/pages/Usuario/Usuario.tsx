import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import type { PostData } from "../../interface/PostData";
import axios from "axios";
import { Card } from "../../components/cards/cards";
import "./usuario.css"
import { useUsuarioData } from "../../hooks/usuarios/useUsuarioData";
import { useUsuarioLogado } from "../../hooks/usuarios/useUsuarioLogado";
import { CreateModal } from "../../components/create-modal/CreateModal";
import { getPostagensUsuario } from "../../hooks/posts/usePostDataUsuario";

const API_URL = "http://localhost:8080";

export function Usuario() {
    const { nomeUsuario } = useParams();
    const navigate = useNavigate();
    const [post, setPost] = useState<PostData[]>([])
    const [isModalOpen, setModalOpen] = useState(false);

    const handleOpenModal = () => {
        setModalOpen(prev => !prev);
    }

    useEffect(() => {
        if (!nomeUsuario) return;

        getPostagensUsuario(nomeUsuario)
        .then(data => setPost(data))
    }, [nomeUsuario]);

    const usuario = useUsuarioData();
    const usuarioLogado = useUsuarioLogado();

    if(usuario.data?.id != usuarioLogado.data?.id) {
        return (
            <div className="container">
                <svg onClick={() => navigate("/")} viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" ><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M224 480h640a32 32 0 1 1 0 64H224a32 32 0 0 1 0-64z"></path><path d="m237.248 512 265.408 265.344a32 32 0 0 1-45.312 45.312l-288-288a32 32 0 0 1 0-45.312l288-288a32 32 0 1 1 45.312 45.312L237.248 512z"></path></g></svg>
                <div className="cabecalho">
                    <img src={usuario.data?.fotoPerfil} alt="" />
                    <h1>@{usuario.data?.nome}</h1>
                </div>
                {post.map(postData => (
                    <Card
                        key={postData.id}
                        descricao={postData.descricao}
                        imagem={postData.imagem}
                        poster={postData.poster}
                    />
                ))}
            </div>
        );
    }

    return (
        <>
        <div>
            {isModalOpen && <CreateModal closeModal={handleOpenModal}/>}
        </div>
        <div className="container">
            <svg onClick={() => navigate("/")} viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" ><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M224 480h640a32 32 0 1 1 0 64H224a32 32 0 0 1 0-64z"></path><path d="m237.248 512 265.408 265.344a32 32 0 0 1-45.312 45.312l-288-288a32 32 0 0 1 0-45.312l288-288a32 32 0 1 1 45.312 45.312L237.248 512z"></path></g></svg>
            <div className="cabecalho">
                <img src={usuario.data?.fotoPerfil} alt="" />
                <h1>@{usuario.data?.nome}</h1>
                <button onClick={handleOpenModal}>Editar perfil</button>
            </div>
            {post.map(postData => (
                <Card
                key={postData.id}
                descricao={postData.descricao}
                imagem={postData.imagem}
                poster={postData.poster}
                />
            ))}
        </div>
            </>
    );

}