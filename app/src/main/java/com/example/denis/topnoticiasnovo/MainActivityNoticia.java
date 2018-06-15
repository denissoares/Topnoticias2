package com.example.denis.topnoticiasnovo;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivityNoticia extends Activity {
    ListaNoticia noticias = new ListaNoticia();

    String autor;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        noticias.getNoticias();

        //TextView enc = (TextView) findViewById(R.id.encontradas);
        //enc.setText(String.valueOf(noticias.noticias));

        List<String> datas = noticias.getTodasDatas();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, datas);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner spinnerData = (Spinner) findViewById(R.id.escolherData);
        final Spinner spinnerAutor = (Spinner) findViewById(R.id.escolherAutor);
        final ListView listViewAutor = (ListView) findViewById(R.id.escolherTitulo);

        spinnerData.setAdapter(adapter);

        spinnerData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String dataSelecionada = String.valueOf(spinnerData.getSelectedItem());
                List<String> nomesAutores = noticias.getAutorPorData(dataSelecionada);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, nomesAutores);
                adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinnerAutor.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        spinnerAutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String dataSelecionada = String.valueOf(spinnerData.getSelectedItem());
                String autorSelecionado = String.valueOf(spinnerAutor.getSelectedItem());
                List<String> titulos = noticias.getTitulosPorDataAutor(dataSelecionada, autorSelecionado);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, titulos);
                listViewAutor.setAdapter(adapter3);

                autor = autorSelecionado;
                data = dataSelecionada;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        listViewAutor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String tituloSelecionado = (String) listViewAutor.getItemAtPosition(position);
                 String resultado = noticias.getNoticiaPorTitulo(tituloSelecionado);

                 Intent parametro = new Intent(getApplicationContext(), TelaNoticia.class);
                 parametro.putExtra("autor", autor);
                 parametro.putExtra("data", data);
                 parametro.putExtra("titulo", tituloSelecionado);
                 parametro.putExtra("noticia", resultado);
                 startActivity(parametro);
             }
         });
    }
}
//Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();