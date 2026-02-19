import { useNavigate } from "react-router-dom";
import type { UsuarioData } from "../../interface/UsuarioData";
import "./cards.css";

interface CardProps {
    imagem: string,
    descricao: string,
    poster: UsuarioData
}

export function Card({ descricao, imagem, poster } : CardProps) {
    const navigate = useNavigate();

    return (
        <div className="container">
            <div className="card">
                <div className="head">
                        <img onClick={() => navigate("/" + poster.nome)} src={poster.fotoPerfil} alt="" id="user" className="foto-perfil" />
                        <h2 onClick={() => navigate("/" + poster.nome)}>{poster.nome}</h2>
                    </div>
                <div className="conteudo">
                    <img src={imagem} alt="" />
                    <p>{descricao}</p>
                </div>
            </div>
        </div>
    )
}