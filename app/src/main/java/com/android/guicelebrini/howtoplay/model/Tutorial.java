package com.android.guicelebrini.howtoplay.model;

public class Tutorial {

    private String titulo;
    private String autor;
    private String imagem;

    public Tutorial(String titulo, String autor, String imagem){
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setImagem(imagem);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImagem() {
        return imagem;
    }

    public String getAutor() {
        return autor;
    }
}
