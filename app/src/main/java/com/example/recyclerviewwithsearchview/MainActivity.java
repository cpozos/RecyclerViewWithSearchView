package com.example.recyclerviewwithsearchview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.util.SortedListAdapterCallback;

import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerviewwithsearchview.databinding.ActivityMainBinding;
import com.example.recyclerviewwithsearchview.databinding.RecyclerviewBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener
{
    /**
     * Comparador para ordenar alfabeticamente
     */
    private static final Comparator<EjemploModel> ALPHABETICAL_COMPARATOR
            = new Comparator<EjemploModel>() {
        @Override
        public int compare(EjemploModel a, EjemploModel b) {
            return a.getText().compareTo(b.getText());
        }
    };

    private EjemploAdapter mAdapter;
    private List<EjemploModel> mlistaModelos;
    private RecyclerView mRecyclerView;
    private ActivityMainBinding mBinding;

    private String[] MOVIES = new String[]{
      "una", "dos", "tres"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAdapter =new EjemploAdapter(this,ALPHABETICAL_COMPARATOR);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);

        mlistaModelos = new ArrayList<>();

        int id = 0;
        for(String movie : MOVIES){
            mlistaModelos.add(new EjemploModel(id,movie));
            id++;
        }
        mAdapter.add(mlistaModelos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Infla la vista con el menú creado en recursos
        getMenuInflater().inflate(R.menu.main_menu,menu);

        // Obtiene el item Creado en recursos (main_menu.xml)
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView =(SearchView) menuItem.getActionView();

        // Asigna esto
        searchView.setOnQueryTextListener(MainActivity.this);
        return  true;
    }

    @Override
    public boolean onQueryTextSubmit(String s)
    {
        return false;
    }

    public static List<EjemploModel> filter(List<EjemploModel> listaEjemplos, String query)
    {
        final String lowerCaseQuery = query.toLowerCase();

        final List<EjemploModel> filteredModelList = new ArrayList<>();
        for (EjemploModel model : listaEjemplos) {
            final String text = model.getText().toLowerCase();
            final String rank = String.valueOf(model.getId());
            if (text.contains(lowerCaseQuery) || rank.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onQueryTextChange(String query)
    {
        // AQUÍ SE IMPLEMENTA LA LÓGICA PARA FILTRAR
        final List<EjemploModel> filteredModeList = filter(mlistaModelos,query);
        mAdapter.replaceAll(filteredModeList);
        mBinding.recyclerView.scrollToPosition(0);
        return true;
    }
}
