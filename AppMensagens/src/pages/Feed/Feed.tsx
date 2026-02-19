import { useNavigate } from "react-router-dom";
import { Card } from "../../components/cards/cards";
import { usePostData } from "../../hooks/posts/usePostData";
import { useUsuarioLogado } from "../../hooks/usuarios/useUsuarioLogado";
import {HeaderApplication} from "../HeaderApplication";

export function Feed() { 
  const {data} = usePostData();
  const usuarioLogado = useUsuarioLogado();
  const navigate = useNavigate();

  if(usuarioLogado.data == undefined) {
    navigate("/auth/login")
  }

  return (
    <div>
      <HeaderApplication/>
      <div className='container'>
        <div className='card-grid'>
        {data?.map((postData => 
          <Card
          descricao={postData.descricao}
          imagem={postData.imagem}
          poster={postData.poster}
          />
        ))}
        </div>
      </div>
    </div>
  )
}