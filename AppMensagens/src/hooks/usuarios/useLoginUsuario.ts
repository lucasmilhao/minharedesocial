import type { AxiosPromise } from "axios";
import type { UsuarioData } from "../../interface/UsuarioData";
import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { UsuarioLoginRequest } from "../../interface/UsuarioLoginRequest";


const API_URL = "http://localhost:8080";

const fetchData = async (data : UsuarioLoginRequest) : AxiosPromise<UsuarioData> => {
    const response = await axios.post(API_URL + "/auth/login", data);

    const token = response.data.token;
    localStorage.setItem("authToken", token);

    return response;
}

export function useLoginUsuario() {
    const queryClient = useQueryClient();

    return useMutation ({
        mutationFn: fetchData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey : ['usuario-data']})
        }
    })
}