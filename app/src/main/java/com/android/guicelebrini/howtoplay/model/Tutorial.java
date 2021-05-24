package com.android.guicelebrini.howtoplay.model;

public class Tutorial {

    private String titulo;
    private String autor;
    private String jogo;
    private String descricaoTutorial;
    private String imagem;

    public Tutorial(){}

    public Tutorial(String titulo, String autor, String jogo, String descricaoTutorial, String imagem){
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setJogo(jogo);
        this.setDescricaoTutorial(descricaoTutorial);
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

    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    public void setDescricaoTutorial(String descricaoTutorial) {
        this.descricaoTutorial = descricaoTutorial;
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

    public String getJogo(){
        return jogo;
    }

    public String getDescricaoTutorial() {
        return descricaoTutorial;
    }
}
