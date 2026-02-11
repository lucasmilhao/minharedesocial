import { Card } from "../../components/cards/cards";
import { usePostData } from "../../hooks/posts/usePostData";

export function Feed() { 
  const {data} = usePostData();

  return (
    <div className='container'>
      <h1>seu feed</h1>
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