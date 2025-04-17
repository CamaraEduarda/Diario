package br.edu.ifpb.daw.diario.service;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.entity.Postagem;
import br.edu.ifpb.daw.diario.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostagemServiceImpl implements PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Override
    public Postagem salvar(PostagemDTO postagemDTO) {
        return null;
    }

    @Override
    public Postagem atualizar(Long id, PostagemDTO postagemDTO) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }

    @Override
    public List<PostagemDTO> listarTodas() {
        return List.of();
    }
}
