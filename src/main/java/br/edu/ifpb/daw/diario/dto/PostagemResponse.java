package br.edu.ifpb.daw.diario.dto;

import java.time.LocalDateTime;

public class PostagemResponse {

    private Long id;
    private String titulo;
    private String texto;
    private String imagemUrl;
    private String resumo;
    private LocalDateTime dataHora;

    public PostagemResponse(Long id, String titulo, String texto, String imagemUrl, String resumo, LocalDateTime dataHora) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.imagemUrl = imagemUrl;
        this.resumo = resumo;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getImagemUrl() {
        return imagemUrl;
    }
    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
    public String getResumo() {
        return resumo;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


}
