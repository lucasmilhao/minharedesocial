import { useUsuarioLogado } from "../hooks/usuarios/useUsuarioLogado";
import type { PostProps } from "../interface/PostProps";
import { api } from "./api";

export const postPostagem = async (nomeUsuario : any, data : PostProps) => {
    const response = await api.post(`usuarios/${nomeUsuario}/postagens`, data);
    return response.data;
}