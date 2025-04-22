package br.edu.ifpb.daw.diario.service.impl;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.entity.Postagem;
import br.edu.ifpb.daw.diario.repository.PostagemRepository;
import br.edu.ifpb.daw.diario.service.PostagemServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostagemServiceImplTest {

    private PostagemRepository postagemRepository;
    private PostagemServiceImpl postagemService;

    @BeforeEach
    void setUp() {
        postagemRepository = mock(PostagemRepository.class);
        postagemService = new PostagemServiceImpl(postagemRepository);
    }

    @Test
    void testSalvar() {
    PostagemDTO postagemDTO = new PostagemDTO();
    postagemDTO.setTitulo("Título Teste");
    postagemDTO.setTexto("Texto Teste");
    
    Postagem postagemSalva = new Postagem();
    postagemSalva.setId(1L);
    postagemSalva.setTitulo("Título Teste");
    postagemSalva.setTexto("Texto Teste");

    // Supondo que há um mapper sendo usado internamente
    when(postagemRepository.save(any(Postagem.class))).thenReturn(postagemSalva);

    PostagemResponse resultado = postagemService.salvar(postagemDTO);

    assertNotNull(resultado);
    assertEquals("Título Teste", resultado.getTitulo());
    assertEquals("Texto Teste", resultado.getTexto());
    }

    @Test
    void testAtualizar_PostagemExistente() {
        Long id = 1L;
        PostagemDTO dto = new PostagemDTO("Novo título", "Novo texto", null);
        Postagem existente = new Postagem();

        when(postagemRepository.findById(id)).thenReturn(Optional.of(existente));
        when(postagemRepository.save(any(Postagem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<PostagemResponse> responseOpt = postagemService.atualizar(id, dto);

        assertTrue(responseOpt.isPresent());
        PostagemResponse response = responseOpt.get();
        assertEquals("Novo título", response.getTitulo());
        assertEquals("Novo texto", response.getTexto());
    }

    @Test
    void testAtualizar_PostagemNaoExiste() {
        when(postagemRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PostagemResponse> response = postagemService.atualizar(99L, new PostagemDTO());

        assertTrue(response.isEmpty());
    }

    @Test
    void testExcluir_ComIdValido() {
        Long id = 1L;
        when(postagemRepository.existsById(id)).thenReturn(true);

        postagemService.excluir(id);

        verify(postagemRepository).deleteById(id);
    }

    @Test
    void testExcluir_ComIdInvalido() {
        when(postagemRepository.existsById(999L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> postagemService.excluir(999L));
        assertEquals("Postagem não encontrada.", exception.getMessage());
    }

    @Test 
    void testListarTodas() {
    Postagem postagem1 = new Postagem();
    postagem1.setId(1L);
    postagem1.setTitulo("Título 1");
    postagem1.setTexto("Texto da postagem 1");
    postagem1.setDataHora(LocalDateTime.now());

    Postagem postagem2 = new Postagem();
    postagem2.setId(2L);
    postagem2.setTitulo("Título 2");
    postagem2.setTexto("Texto da postagem 2");
    postagem2.setDataHora(LocalDateTime.now());

    List<Postagem> postagens = Arrays.asList(postagem1, postagem2);
    when(postagemRepository.findAll()).thenReturn(postagens);

    List<PostagemResponse> responses = postagemService.listarTodas();

    assertEquals(2, responses.size());
    assertEquals("Título 1", responses.get(0).getTitulo());
    assertEquals("Título 2", responses.get(1).getTitulo());
}

    @Test
    void testBuscarPostagemPorId_Existe() {
    Postagem postagem = new Postagem();
    postagem.setId(1L);
    postagem.setTexto("Texto exemplo");
    postagem.setTitulo("Título");
    postagem.setDataHora(LocalDateTime.now());

    when(postagemRepository.findById(1L)).thenReturn(Optional.of(postagem));

    Optional<PostagemResponse> resultado = postagemService.buscarPostagemPorId(1L);

    assertTrue(resultado.isPresent());
    assertEquals(1L, resultado.get().getId());
    assertEquals("Texto exemplo", resultado.get().getTexto());
}
    

    @Test
    void testBuscarPostagemPorId_NaoExiste() {
        when(postagemRepository.findById(123L)).thenReturn(Optional.empty());

        Optional<PostagemResponse> response = postagemService.buscarPostagemPorId(123L);

        assertTrue(response.isEmpty());
    }
}
