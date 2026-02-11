import type { AxiosPromise } from "axios";
import type { PostData } from "../../interface/PostData";
import axios from "axios";
import { useQuery } from "@tanstack/react-query";

const API_URL = "http://localhost:8080";

const fetchData = async (): AxiosPromise<PostData[]> => {
    const response = axios.get(API_URL + "/feed")
    return response;
}

export function usePostData() {
    const query = useQuery({
        queryFn : fetchData,
        queryKey : ["postagem-data"],
        retry : 2
    })

    return {
        ...query,
        data: query.data?.data
    }
}