import React, { useEffect, useState } from "react";
import { useParams, Link } from 'react-router-dom';
import axios from "axios";

function DetalhesPostagem() {
    const { id } = useParams();
    const [postagem, setPostagem] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/postagens/${id}`)
            .then(resposta => setPostagem(resposta.data))
            .catch(erro => console.error("Erro ao detalhar postagem", erro));
    }, [id]);

    if (!postagem) return <p className="carregando">Carregando...</p>;

    return (
        <div className="pagina">
            <div className="post-card"
                 style={{
                     maxWidth: '800px',
                     margin: '0 auto',
                     width: '90%',
                     borderRadius: '20px',
                     overflow: 'hidden',
                     boxShadow: '0 8px 15px rgba(0, 0, 0, 0.1)',
                     background: 'linear-gradient(135deg, #f09433, #e6683c, #dc2743, #cc2366, #bc1888)',
                     color: 'white',
                     fontFamily: 'Arial, sans-serif' }}>
                <div className="post-header">
                    <div className="profile-pic"></div>
                    <div className="username">usuario_exemplo</div>
                </div>

                <div
                    className="post-image"
                    style={{ backgroundImage: `url(${postagem.imagemUrl})`, height: '300px' }}
                ></div>

                <div className="post-content">
                    <h2 style={{ fontSize: '26px', marginBottom: '10px' }}>{postagem.titulo}</h2>
                    <p style={{ whiteSpace: 'pre-wrap', fontSize: '16px' }}>{postagem.texto}</p>
                </div>

                <div className="post-footer">
                    <span><strong>Data:</strong> {postagem.dataHora}</span>
                    <Link to="/" style={{ color: 'white', textDecoration: 'underline' }}>⬅ Voltar</Link>
                </div>
            </div>
        </div>
    );
}

export default DetalhesPostagem;
