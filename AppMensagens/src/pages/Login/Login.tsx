import { useEffect, useState } from "react";
import type { UsuarioRequest as UsuarioRegisterRequest } from "../../interface/UsuarioRegisterRequest";
import { useCreateUsuario } from "../../hooks/usuarios/useCreateUsuario";
import { useNavigate } from "react-router-dom";
import "./login.css"
import type { UsuarioLoginRequest } from "../../interface/UsuarioLoginRequest";
import { useLoginUsuario } from "../../hooks/usuarios/useLoginUsuario";

export function Login() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const {mutate, isSuccess, isPending} = useLoginUsuario();

    const submit = () => {
        const usuarioData : UsuarioLoginRequest = {
            email,
            senha
        }

        mutate(usuarioData)
    }
    
    useEffect(() => {
        if(!isSuccess) return;
        else {
            navigate("/");
        }
    }, [isSuccess])

    return (
        <div className="body">
            
        <div className="container">
        <div className="texto">
            <h1>Faça Login</h1>
        </div>
        <div className="links">
            <div className="login">
                <div className="campo">
                    <label htmlFor="email">Email*</label>
                    <input type="text" placeholder="Digite seu email" value={email} name="email" onChange={e => setEmail(e.target.value)} id="email"></input>
                </div>
                <div className="campo">
                    <label htmlFor="senha">Senha*</label>
                    <input type="password" placeholder="Digite sua senha" name="senha" id="senha" value={senha} onChange={e => setSenha(e.target.value)}></input>
                </div>
                <p>ainda não tem uma conta? <a title="cadastro" id="link-cadastro" href="/auth/register">Cadastre-se</a> </p>

                <button id="submit" onClick={submit}>{isPending? "Fazendo login..." : "Entrar"}</button>
            </div>
        </div>
    </div>
    </div>
    )
}