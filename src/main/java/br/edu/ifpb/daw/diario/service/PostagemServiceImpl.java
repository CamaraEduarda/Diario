package br.edu.ifpb.daw.diario.service;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.entity.Postagem;
import br.edu.ifpb.daw.diario.repository.PostagemRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostagemServiceImpl implements PostagemService {

    private final PostagemRepository postagemRepository;

    public PostagemServiceImpl(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    @Override
    public PostagemResponse salvar(PostagemDTO postagemDTO) {
        Postagem postagem = new Postagem();
        postagem.setTitulo(postagemDTO.getTitulo());
        postagem.setImagemUrl(postagemDTO.getImagemUrl());
        postagem.setTexto(postagemDTO.getTexto());

        Postagem salva = postagemRepository.save(postagem);
        return toDTO(salva);
    }

    @Override
    public Optional<PostagemResponse> atualizar(Long id, PostagemDTO postagemDTO) {
        Optional<Postagem> postagemOptional = postagemRepository.findById(id);

        if(postagemOptional.isEmpty()){
            return Optional.empty();
        }

        Postagem postagemAtual = postagemOptional.get();
        postagemAtual.setTitulo(postagemDTO.getTitulo());
        postagemAtual.setTexto(postagemDTO.getTexto());
        postagemAtual.setImagemUrl(postagemDTO.getImagemUrl());

        Postagem atualizada = postagemRepository.save(postagemAtual);
        return Optional.of(toDTO(atualizada));
    }

    @Override
    public void excluir(Long id) {
        if(!postagemRepository.existsById(id)) {
            throw new IllegalArgumentException("Postagem não encontrada.");
        }
        postagemRepository.deleteById(id);
    }

    @Override
    public List<PostagemResponse> listarTodas() {
        return postagemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostagemResponse> buscarPostagemPorId(Long id) {
        return postagemRepository.findById(id).map(this::toDTO);
    }

    private String gerarResumo(String texto) {
        if(texto.length() > 50){
            return texto.substring(0, 50) + "...";
        }
        return texto;
    }

    private PostagemResponse toDTO(Postagem postagem) {
        String resumo = gerarResumo(postagem.getTexto());
        String dataHoraFormatada = postagem.getDataHora() != null ? postagem.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")): "";

        PostagemResponse dto = new PostagemResponse();
        dto.setId(postagem.getId());
        dto.setTitulo(postagem.getTitulo());
        dto.setTexto(postagem.getTexto());
        dto.setImagemUrl(postagem.getImagemUrl());
        dto.setResumo(resumo);
        dto.setDataHora(dataHoraFormatada);

        return dto;
    }
}
