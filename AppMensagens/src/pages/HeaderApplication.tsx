import { useNavigate } from "react-router-dom";
import { useUsuarioLogado } from "../hooks/usuarios/useUsuarioLogado";
import "./header.css"
import { useState } from "react";
import { PostModal } from "../components/create-modal/PostModal";

export function HeaderApplication () {
    const user = useUsuarioLogado();
    const navigate = useNavigate();
    const [isModalOpen, setModalOpen] = useState(false)

    const handleOpenModal = () => {
        setModalOpen(prev => !prev);
    }

    return (
        <>
        <header>
            <button onClick={handleOpenModal} className="btn-add" id="btn-add">+</button>
            <img src="./src/porcogram.png" alt="" className="titulo"/>
            <img onClick={() => navigate("/" + user.data?.nome)} src={user.data?.fotoPerfil} alt="" id="user-in" className="user"/>
        </header>
        <div>
            {isModalOpen && <PostModal closeModal={handleOpenModal}/> }
        </div>
        </>
    )
}