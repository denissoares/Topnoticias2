package com.example.denis.topnoticiasnovo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis on 04/06/2018.
 */

public class ListaNoticia {

    public List<Noticia> noticias = new LinkedList<Noticia>();
    public Conexao conexao = new Conexao();

    public void getNoticias() {
        try {
            noticias = conexao.enviarRequisicao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getTodasDatas() {
        List<String> datas = new LinkedList<String>();
        for (Noticia noticia : noticias) {
            if(datas.contains(noticia.getDataPost()))
                continue;
            else
                datas.add(noticia.getDataPost());
        }
        return datas;
    }

    public List<String> getAutorPorData(String dataPost){
        List<String> encontrados = new LinkedList<String>();
        for(Noticia noticia:noticias){
            if(noticia.getDataPost().equals(dataPost))
                if(encontrados.contains(noticia.getAutor()))
                    continue;
                else
                    encontrados.add(noticia.getAutor());
        }
        return encontrados;
    }

    public List<String> getTitulosPorDataAutor(String dataPost, String autor){
        List<String> encontrados = new LinkedList<String>();
        for(Noticia noticia : noticias){
            if(noticia.getDataPost().equals(dataPost) && noticia.getAutor().equals(autor))
                encontrados.add(noticia.getTitulo());
        }
        return encontrados;
    }

    public String getNoticiaPorTitulo(String titulo){
        for(Noticia noticia : noticias){
            if(noticia.getTitulo().equals(titulo))
                return noticia.getConteudo();
        }
        return "Notícia não encontrada";
    }
}
