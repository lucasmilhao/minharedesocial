import { useMutation } from "@tanstack/react-query";
import type { PostRequestDTO } from "../../interface/PostRequestDTO";
import axios from "axios";

const API_URL = "http://localhost:8080";

export function usePostDataMutate() {
    return useMutation ({
        mutationFn: async (data : PostRequestDTO) => {
            const token = localStorage.getItem("token");

            const response = await axios.post(
                `${API_URL}/usuarios/${data.nomeUsuario}/postagens`, data,
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            return response.data;
        }
    })
}