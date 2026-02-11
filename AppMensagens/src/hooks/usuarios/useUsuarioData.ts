import { useQuery } from "@tanstack/react-query";
import type { AxiosPromise } from "axios";
import axios from "axios";
import type { UsuarioData } from "../../interface/UsuarioData";

const API_URL = "http://localhost:8080";

const fetchData = async (): AxiosPromise<UsuarioData[]> => {
    const response = axios.get(API_URL + "/usuarios")
    return response;
}

export function useUsuarioData() {
    const query = useQuery({
        queryFn : fetchData,
        queryKey : ['usuario-data'],
        retry : 2
    })

    return {
        ...query,
        data: query.data?.data
    }
}