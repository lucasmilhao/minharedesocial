import { useEffect, useState } from "react";
import { useCreateUsuario } from "../../hooks/usuarios/useCreateUsuario"
import type { UsuarioRequest } from "../../interface/UsuarioRegisterRequest";
import "./login.css"
import { useNavigate } from "react-router-dom";

export function Cadastro() {
    const navigate = useNavigate();
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const {mutate, isSuccess, isPending} = useCreateUsuario();

    const submit = () => {
        const usuarioData : UsuarioRequest = {
            nome,
            email,
            senha
        }

        mutate(usuarioData)
    }
    
    useEffect(() => {
        if(!isSuccess) return;
        else {
            navigate("/auth/login");
        }
    }, [isSuccess])

    return (
        <div className="body">
            
        <div className="container">
        <div className="texto">
            <h1>Crie sua conta</h1>
        </div>
        <div className="links">
            <div className="login">
                <div className="campo">
                    <label htmlFor="email">Email*</label>
                    <input type="text" placeholder="Digite seu email" value={email} name="email" onChange={e => setEmail(e.target.value)} id="email"></input>
                </div>
                <div className="campo">
                    <label htmlFor="nome">Nome*</label>
                    <input type="text" placeholder="Digite seu nome" name="nome" id="nome" value={nome} onChange={e => setNome(e.target.value)}></input>
                </div>
                    
                <div className="campo">
                    <label htmlFor="senha">Senha*</label>
                    <input type="password" placeholder="Digite sua senha" name="senha" id="senha" value={senha} onChange={e => setSenha(e.target.value)}></input>
                    <p>Deve haver no mínimo 8 caracteres.</p>
                </div>

                <button onClick={submit}>{isPending? "Criando conta..." : "Criar conta"}</button>
            </div>
        </div>
    </div>
    </div>
    )
}