package br.edu.ifpb.daw.diario.controller;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.service.PostagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostagemControllerTest {

    private PostagemService postagemService;
    private PostagemController postagemController;

    @BeforeEach
    void setUp() {
        postagemService = mock(PostagemService.class);
        postagemController = new PostagemController(postagemService);
    }

    @Test
    void listarPostagens_DeveRetornarListaDePostagens() {
        PostagemResponse p1 = new PostagemResponse(1L, "Título 1", "Texto 1", null, null, null);
        PostagemResponse p2 = new PostagemResponse(2L, "Título 2", "Texto 2", null, null, null);
        when(postagemService.listarTodas()).thenReturn(Arrays.asList(p1, p2));

        ResponseEntity<List<PostagemResponse>> resposta = postagemController.listarPostagens();

        assertEquals(HttpStatus.OK.value(), resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());
    }

    @Test
    void buscarPostagemPorId_QuandoExiste_DeveRetornarOk() {
        PostagemResponse postagem = new PostagemResponse(1L, "Título", "Texto", null, null, null);
        when(postagemService.buscarPostagemPorId(1L)).thenReturn(Optional.of(postagem));

        ResponseEntity<PostagemResponse> resposta = postagemController.buscarPostagemPorId(1L);

        assertEquals(HttpStatus.OK.value(), resposta.getStatusCodeValue());
        assertEquals("Título", resposta.getBody().getTitulo());
    }

    @Test
    void buscarPostagemPorId_QuandoNaoExiste_DeveRetornarNotFound() {
        when(postagemService.buscarPostagemPorId(99L)).thenReturn(Optional.empty());

        ResponseEntity<PostagemResponse> resposta = postagemController.buscarPostagemPorId(99L);

        assertEquals(HttpStatus.NOT_FOUND.value(), resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    void salvarPostagem_DeveRetornarCreatedComPostagem() {
        PostagemDTO dto = new PostagemDTO("Novo", "Texto novo", null);
        PostagemResponse salvo = new PostagemResponse(1L, "Novo", "Texto novo", null, null, null);
        when(postagemService.salvar(dto)).thenReturn(salvo);

        ResponseEntity<PostagemResponse> resposta = postagemController.salvarPostagem(dto);

        assertEquals(HttpStatus.CREATED.value(), resposta.getStatusCodeValue());
        assertEquals("Novo", resposta.getBody().getTitulo());
    }

    @Test
    void atualizarPostagem_QuandoExiste_DeveRetornarAtualizado() {
        PostagemDTO dto = new PostagemDTO("Atualizado", "Texto atualizado", null);
        PostagemResponse atualizado = new PostagemResponse(1L, "Atualizado", "Texto atualizado", null, null, null);
        when(postagemService.atualizar(1L, dto)).thenReturn(Optional.of(atualizado));

        ResponseEntity<PostagemResponse> resposta = postagemController.atualizarPostagem(1L, dto);

        assertEquals(HttpStatus.OK.value(), resposta.getStatusCodeValue());
        assertEquals("Atualizado", resposta.getBody().getTitulo());
    }

    @Test
    void atualizarPostagem_QuandoNaoExiste_DeveRetornarNotFound() {
        PostagemDTO dto = new PostagemDTO("Atualizado", "Texto atualizado", null);
        when(postagemService.atualizar(99L, dto)).thenReturn(Optional.empty());

        ResponseEntity<PostagemResponse> resposta = postagemController.atualizarPostagem(99L, dto);

        assertEquals(HttpStatus.NOT_FOUND.value(), resposta.getStatusCodeValue());
    }

    @Test
    void deletarPostagem_DeveChamarServiceEDevolverNoContent() {
        doNothing().when(postagemService).excluir(1L);

        ResponseEntity<Void> resposta = postagemController.deletarPostagem(1L);

        assertEquals(HttpStatus.NO_CONTENT.value(), resposta.getStatusCodeValue());
        verify(postagemService, times(1)).excluir(1L);
    }

    // Teste para falha inesperada no serviço
    @Test
    void salvarPostagem_QuandoFalhaNoService_DeveRetornarInternalServerError() {
        PostagemDTO dto = new PostagemDTO("Novo", "Texto novo", null);
        when(postagemService.salvar(dto)).thenThrow(new RuntimeException("Erro inesperado"));

        try {
            postagemController.salvarPostagem(dto);
            fail("Esperado RuntimeException");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}
