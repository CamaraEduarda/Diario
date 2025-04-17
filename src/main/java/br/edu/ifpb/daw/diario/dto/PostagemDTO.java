package br.edu.ifpb.daw.diario.dto;


public class PostagemDTO {
    
    private String titulo;
    private String texto;
    private String fotoUrl;

    public PostagemDTO(String titulo, String texto, String fotoUrl) {
        this.titulo = titulo;
        this.texto = texto;
        this.fotoUrl = fotoUrl;
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
    public String getFotoUrl() {
        return fotoUrl;
    }
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

}
