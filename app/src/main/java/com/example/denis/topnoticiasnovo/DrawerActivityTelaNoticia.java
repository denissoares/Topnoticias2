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
import android.widget.TextView;

public class DrawerActivityTelaNoticia extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_tela_noticia);
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
