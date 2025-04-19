package br.edu.ifpb.daw.diario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.daw.diario.dto.PostagemDTO;
import br.edu.ifpb.daw.diario.dto.PostagemResponse;
import br.edu.ifpb.daw.diario.service.PostagemService;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @GetMapping
    public ResponseEntity<List<PostagemResponse>> listarPostagens() {
        List<PostagemResponse> postagens = postagemService.listarTodas();
        return ResponseEntity.ok(postagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemResponse> buscarPostagemPorId(@PathVariable Long id){
        Optional<PostagemResponse> postagem = postagemService.buscarPostagemPorId(id);
        return postagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());  
    }

    @PostMapping
    public ResponseEntity<PostagemResponse> salvarPostagem(@RequestParam("titulo") String titulo, @RequestParam("texto") String texto, @RequestParam("imagem") String fotoUrl) {
        PostagemDTO postagemDTO = new PostagemDTO(titulo, texto, fotoUrl);
        PostagemResponse postagemSalva = postagemService.salvar(postagemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemSalva);
    }

    @PutMapping("/{id}")
     public ResponseEntity<PostagemResponse> atualizarPostagem(@PathVariable Long id, @RequestParam("titulo") String titulo, @RequestParam("texto") String texto, @RequestParam("imagem") String fotoUrl) {
        PostagemDTO postagemDTO = new PostagemDTO(titulo, texto, fotoUrl);
        Optional<PostagemResponse> postagemAtualizada = postagemService.atualizar(id, postagemDTO);
        return postagemAtualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable Long id) {
        postagemService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
}
