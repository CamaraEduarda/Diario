package br.edu.ifpb.daw.diario.dto;


public class PostagemDTO {
    
    private String titulo;
    private String texto;
    private String imagemUrl;

    public PostagemDTO(String titulo, String texto, String imagemUrl) {
        this.titulo = titulo;
        this.texto = texto;
        this.imagemUrl = imagemUrl;
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

}
