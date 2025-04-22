import React, {useEffect, useState} from "react";
import { useParams, Link } from 'react-router-dom'
import axios from "axios";

function DetalhesPostagem(){
    const { id } = useParams();
    const [postagem, setPostagem] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/postagens/${id}`)
            .then(resposta => setPostagem(resposta.data)).catch(erro => console.error("Erro ao detalhar postagem", erro))
    }, [id]);

    if(!postagem) return <p>Carregando...</p>

    return (
        <div className="detalhes-postagem">
            <Link to="/">⬅ Voltar</Link>
            <h2>{postagem.titulo}</h2>
            <img
                src={postagem.imagemUrl}
                alt="Imagem da postagem"
                style={{
                    maxWidth: '500px',
                    width: '100%',
                    display: 'block',
                    margin: '20px auto',
                    borderRadius: '10px'
                }}
            />
            <p>{postagem.texto}</p>
            <p><strong>Data:</strong> {postagem.dataHora} </p>
        </div>
    );
}

export default DetalhesPostagem;