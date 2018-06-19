package com.example.denis.topnoticiasnovo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class DrawerActivityNoticia extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListaNoticia noticias = new ListaNoticia();

    String autor;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_noticia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* AQUI COMEÇA NOSSO CÓDIGO */

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

                Intent parametro = new Intent(getApplicationContext(), DrawerActivityTelaNoticia.class);
                parametro.putExtra("autor", autor);
                parametro.putExtra("data", data);
                parametro.putExtra("titulo", tituloSelecionado);
                parametro.putExtra("noticia", resultado);
                startActivity(parametro);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_escolher) {
            startActivity(new Intent(this, DrawerActivityNoticia.class));
        } else if (id == R.id.nav_sobre) {
            startActivity(new Intent(this, DrawerActivitySobre.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
