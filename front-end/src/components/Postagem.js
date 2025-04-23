import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Postagem.css';
import { Link } from "react-router-dom";

// Função para converter "dd/MM/yyyy HH:mm" para objeto Date
function parseDataHora(dataHoraStr) {
    const [data, hora] = dataHoraStr.split(' ');
    const [dia, mes, ano] = data.split('/');
    const [horaStr, minutoStr] = hora.split(':');
    return new Date(ano, mes - 1, dia, horaStr, minutoStr);
}


function Postagem() {
    const [postagens, setPostagens] = useState([]);
    const [loading, setLoading] = useState(true);
    const [editando, setEditando] = useState(null);
    const [form, setForm] = useState({ titulo: '', texto: '', imagemUrl: '' });
    const [adicionando, setAdicionando] = useState(false);

    useEffect(() => {
        carregarPostagens();
    }, []);

    const carregarPostagens = () => {
        axios.get('http://localhost:8080/api/postagens')
            .then(response => {
                setPostagens(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Erro ao carregar postagens:', error);
                setLoading(false);
            });
    };

    const handleDelete = (id) => {
        const confirm = window.confirm('Tem certeza que deseja deletar esta postagem?');
        if (confirm) {
            axios.delete(`http://localhost:8080/api/postagens/${id}`)
                .then(() => {
                    setPostagens(postagens.filter(p => p.id !== id));
                })
                .catch(error => {
                    console.error('Erro ao deletar a postagem:', error);
                    alert('Erro ao deletar a postagem.');
                });
        }
    };

    const handleEdit = (post) => {
        setEditando(post.id);
        setForm({
            titulo: post.titulo,
            texto: post.texto,
            imagemUrl: post.imagemUrl,
        });
    };

    const handleFormChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSave = (id) => {
        axios.put(`http://localhost:8080/api/postagens/${id}`, {
            titulo: form.titulo,
            texto: form.texto,
            imagemUrl: form.imagemUrl
        })
            .then(response => {
                const novasPostagens = postagens.map(p =>
                    p.id === id ? response.data : p
                );
                setPostagens(novasPostagens);
                setEditando(null);
                setForm({ titulo: '', texto: '', imagemUrl: '' });
            })
            .catch(error => {
                console.error('Erro ao atualizar a postagem:', error);
                alert('Erro ao atualizar a postagem.');
            });
    };

    const handleCreate = () => {
        axios.post('http://localhost:8080/api/postagens', {
            titulo: form.titulo,
            texto: form.texto,
            imagemUrl: form.imagemUrl
        })
            .then(response => {
                setPostagens([response.data, ...postagens]);
                setAdicionando(false);
                setForm({ titulo: '', texto: '', imagemUrl: '' });
            })
            .catch(error => {
                console.error('Erro ao criar a postagem:', error);
                alert('Erro ao criar a postagem.');
            });
    };

    const handleCancel = () => {
        setEditando(null);
        setAdicionando(false);
        setForm({ titulo: '', texto: '', imagemUrl: '' });
    };

    return (
        <div className="pagina">
            <header className="diario-header">
                <h1>📔 Diário de Postagem</h1>
            </header>

            {loading ? (
                <p className="carregando">Carregando postagens...</p>
            ) : (
                <div className="postagem-container">
                    {adicionando && (
                        <div className="post-card">
                            <div className="edit-form">
                                <input
                                    name="titulo"
                                    placeholder="Título"
                                    value={form.titulo}
                                    onChange={handleFormChange}
                                />
                                <textarea
                                    name="texto"
                                    placeholder="Texto"
                                    value={form.texto}
                                    onChange={handleFormChange}
                                />
                                <input
                                    name="imagemUrl"
                                    placeholder="URL da imagem"
                                    value={form.imagemUrl}
                                    onChange={handleFormChange}
                                />
                                <div className="form-buttons">
                                    <button onClick={handleCreate}>➕ Criar</button>
                                    <button onClick={handleCancel}>❌ Cancelar</button>
                                </div>
                            </div>
                        </div>
                    )}
                    {postagens.map(post => (
                        <div key={post.id} className="post-card">
                            {editando === post.id ? (
                                <div className="edit-form">
                                    <input
                                        name="titulo"
                                        placeholder="Título"
                                        value={form.titulo}
                                        onChange={handleFormChange}
                                    />
                                    <textarea
                                        name="texto"
                                        placeholder="Texto"
                                        value={form.texto}
                                        onChange={handleFormChange}
                                    />
                                    <input
                                        name="imagemUrl"
                                        placeholder="URL da imagem"
                                        value={form.imagemUrl}
                                        onChange={handleFormChange}
                                    />
                                    <div className="form-buttons">
                                        <button onClick={() => handleSave(post.id)}>💾 Salvar</button>
                                        <button onClick={handleCancel}>❌ Cancelar</button>
                                    </div>
                                </div>
                            ) : (
                                <>
                                    <div className="post-header">
                                        <div className="profile-pic"></div>
                                        <div className="username">usuario_exemplo</div>
                                    </div>
                                    <div
                                        className="post-image"
                                        style={{ backgroundImage: `url(${post.imagemUrl})` }}
                                    ></div>
                                    <div className="post-content">
                                        <h3>{post.titulo}</h3>
                                        <p>{post.resumo}</p>
                                        <Link to={`/postagem/${post.id}`}>
                                            <p>Ver mais</p>
                                        </Link>
                                    </div>
                                    <div className="post-footer">
                                        <small>{parseDataHora(post.dataHora).toLocaleString()}</small>
                                    </div>
                                    <div className="post-actions">
                                        <button onClick={() => handleEdit(post)}>✏️ Editar</button>
                                        <button onClick={() => handleDelete(post.id)}>🗑️ Deletar</button>
                                    </div>
                                </>
                            )}
                        </div>
                    ))}
                </div>
            )}

            {!adicionando && !editando && (
                <button className="float-button" onClick={() => setAdicionando(true)}>
                    ➕
                </button>
            )}
        </div>
    );
}

export default Postagem;
