import type { AxiosPromise } from "axios";
import type { UsuarioData } from "../../interface/UsuarioData";
import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { UsuarioRequest } from "../../interface/UsuarioRegisterRequest";


const API_URL = "http://localhost:8080";

const fetchData = async (data : UsuarioRequest) : AxiosPromise<UsuarioData> => {
    const response = await axios.post(API_URL + "/auth/register", data);
    return response;
}

export function useCreateUsuario() {
    const queryClient = useQueryClient();

    return useMutation ({
        mutationFn: fetchData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey : ['usuario-data']})
        }
    })
}