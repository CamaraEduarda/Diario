package br.edu.ifpb.daw.diario.dto;


public class PostagemResponse {

    private Long id;
    private String titulo;
    private String texto;
    private String imagemUrl;
    private String resumo;
    private String dataHora;

    public PostagemResponse(Long id, String titulo, String texto, String imagemUrl, String resumo, String dataHora) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.imagemUrl = imagemUrl;
        this.resumo = resumo;
        this.dataHora = dataHora;
    }

    //construtor vazio
    public PostagemResponse() {}

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
    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }


}
