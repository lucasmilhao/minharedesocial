import { Card } from "../../components/cards/cards";
import { usePostData } from "../../hooks/posts/usePostData";
import { useUsuarioLogado } from "../../hooks/usuarios/useUsuarioLogado";

export function Feed() { 
  const {data} = usePostData();
  const user = useUsuarioLogado();

  return (
    <div className='container'>
      <h1>Feed de {user.data?.nome}</h1>
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
  )
}