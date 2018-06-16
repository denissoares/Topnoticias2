package com.example.denis.topnoticiasnovo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by denis on 04/06/2018.
 */

public class TelaNoticia extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_noticia);

        //Pega a intent de outra activity
        Intent parametro = getIntent();

        //Recupera as strings da outra activity
        String autor = parametro.getStringExtra("autor");
        String data = parametro.getStringExtra("data");
        String titulo = parametro.getStringExtra("titulo");
        String noticia = parametro.getStringExtra("noticia");

        TextView areaAutor = (TextView)findViewById(R.id.autor);
        areaAutor.setText("Por: " + autor);

        TextView areaData = (TextView)findViewById(R.id.data);
        areaData.setText(data);

        TextView areaTitulo = (TextView)findViewById(R.id.titulo);
        areaTitulo.setText(titulo);

        TextView areaNoticia = (TextView)findViewById(R.id.noticia);
        areaNoticia.setText(noticia);
    }
}
