package com.example.recyclerviewwithsearchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Infla la vista con el menú creado en recursos
        getMenuInflater().inflate(R.menu.main_menu,menu);

        // Obtiene el item Creado en recursos (main_menu.xml)
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView =(SearchView) menuItem.getActionView();

        //
        searchView.setOnQueryTextListener(MainActivity.this);
        return  true;
    }

    @Override
    public boolean onQueryTextSubmit(String s)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s)
    {
        // AQUÍ SE IMPLEMENTA LA LÓGICA PARA FILTRAR
        return false;
    }
}
