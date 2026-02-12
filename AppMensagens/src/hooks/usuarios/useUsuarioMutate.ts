import type { AxiosPromise } from "axios";
import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { UsuarioData } from "../../interface/UsuarioData";

const API_URL = "http://localhost:8080";

const postData = async (data : UsuarioData) : AxiosPromise<any> => {
    const response = axios.post(API_URL + "/usuarios", data);
    return response;
}

export function useUsuarioMutate() {
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey : ['usuario-data']});
        }
    })

    return mutate;
}