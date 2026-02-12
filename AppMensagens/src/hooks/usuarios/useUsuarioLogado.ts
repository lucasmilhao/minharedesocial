import { useQuery } from "@tanstack/react-query";
import type { UsuarioData } from "../../interface/UsuarioData";
import axios from "axios";
const API_URL = "http://localhost:8080";

const fetchData = async () => {
    const token = localStorage.getItem("authToken");

    const response = await axios.get<UsuarioData>(API_URL + "/usuarios/me", {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
}

export function useUsuarioLogado() {

    const query = useQuery({
        queryFn : fetchData,
        queryKey : ['usuario-data'],
        retry : 2
    })

    return {
        ...query,
        data: query.data
    }
}