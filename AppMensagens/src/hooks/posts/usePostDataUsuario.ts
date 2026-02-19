import type { PostData } from "../../interface/PostData";
import { api } from "../../service/api";

export const getPostagensUsuario = async (nomeUsuario : string) => {
    const response = await api.get<PostData[]>(`usuarios/${nomeUsuario}/postagens`);

    return response.data;
}