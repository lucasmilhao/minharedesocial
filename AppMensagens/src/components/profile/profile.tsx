interface ProfileProps {
    imagem : string,
    nome : string,
    email : string
}

export function Profile({imagem, nome, email} : ProfileProps) {
    return (
        <div className="container">
            <div className="head">
                <img src={imagem} alt="" />
                <h1>{nome}</h1>
                <h3>{email}</h3>
            </div>

        </div>
    )
}