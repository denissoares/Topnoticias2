package com.example.denis.topnoticiasnovo;

/**
 * Created by denis on 04/06/2018.
 */

public class Noticia {

    private String dataPost;
    private String autor;
    private String titulo;
    private String conteudo;

    public Noticia (String dataPost, String autor, String titulo, String conteudo){
        this.dataPost = dataPost;
        this.autor = autor;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getDataPost() {
        return dataPost;
    }
    public String getAutor (){
        return autor;
    }
    public String getTitulo () {
        return titulo;
    }
    public String getConteudo () {
        return conteudo;
    }
}
