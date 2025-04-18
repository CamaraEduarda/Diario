package br.edu.ifpb.daw.diario.service;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.entity.Postagem;
import br.edu.ifpb.daw.diario.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostagemServiceImpl implements PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Override
    public PostagemResponse salvar(PostagemDTO postagemDTO) {
        return null;
    }

    @Override
    public Optional<PostagemResponse> atualizar(Long id, PostagemDTO postagemDTO) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public List<PostagemResponse> listarTodas() {
        return List.of();
    }

    @Override
    public Optional<PostagemResponse> buscarPostagemPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPostagemPorId'");
    }
}
