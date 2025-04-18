package br.edu.ifpb.daw.diario.service;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.entity.Postagem;

import java.util.List;
import java.util.Optional;

public interface PostagemService {
    PostagemResponse salvar(PostagemDTO postagemDTO);
    Optional<PostagemResponse> atualizar(Long id, PostagemDTO postagemDTO);
    void excluir(Long id);
    List<PostagemResponse> listarTodas();
    Optional<PostagemResponse> buscarPostagemPorId(Long id);
}
