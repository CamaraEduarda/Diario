package br.edu.ifpb.daw.diario.service;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.entity.Postagem;

import java.util.List;

public interface PostagemService {
    Postagem salvar(PostagemDTO postagemDTO);
    Postagem atualizar(Long id, PostagemDTO postagemDTO);
    void excluir(Long id);
    List<PostagemDTO> listarTodas();
}
