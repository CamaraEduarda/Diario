package br.edu.ifpb.daw.diario.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostagemDTO {

    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 50, message = "O título deve ter no máximo 50 caracteres")
    private String titulo;

    @NotBlank(message = "O texto não pode estar em branco.")
    private String texto;

    @NotBlank(message = "A URL da imagem não pode estar em branco.")
    private String imagemUrl;

    public PostagemDTO(){}

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
