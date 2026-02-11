import type { UsuarioData } from "./UsuarioData";

export interface PostData {
    id?: number,
    imagem: string,
    descricao: string,
    poster: UsuarioData
}