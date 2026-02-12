import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import type { PostData } from "../../interface/PostData";
import axios from "axios";
import { Card } from "../../components/cards/cards";
import "./usuario.css"
import { useUsuarioData } from "../../hooks/usuarios/useUsuarioData";

const API_URL = "http://localhost:8080";

export function Usuario() {
    const { nomeUsuario } = useParams();
    const navigate = useNavigate();
    const [post, setPost] = useState<PostData[]>([])
    const token = localStorage.getItem("authToken");

    useEffect(() => {
        if (!nomeUsuario) return;

        axios.get(API_URL + "/usuarios/" + nomeUsuario + "/postagens", {
            headers: {
                Authorization: `Bearer ${token}`
            }

        }).then(response => setPost(response.data))
    }, [nomeUsuario, token]);

    const usuario = useUsuarioData();
    console.log(usuario.data?.fotoPerfil)

    return (
        <div className="container">
            <svg onClick={() => navigate("/")} viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" ><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M224 480h640a32 32 0 1 1 0 64H224a32 32 0 0 1 0-64z"></path><path d="m237.248 512 265.408 265.344a32 32 0 0 1-45.312 45.312l-288-288a32 32 0 0 1 0-45.312l288-288a32 32 0 1 1 45.312 45.312L237.248 512z"></path></g></svg>
            <div className="cabecalho">
                <img src={usuario.data?.fotoPerfil} alt="" />
                <h1>{usuario.data?.nome}</h1>
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