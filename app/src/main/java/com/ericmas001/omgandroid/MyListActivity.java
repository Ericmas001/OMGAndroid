package com.ericmas001.omgandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {
    public final static String LIST_TYPE = "com.ericmas001.omgandroid.LIST_TYPE";
    public final static String LIST_TYPE_NORMAL = "NORMAL";
    public final static String LIST_TYPE_CAROUSEL = "CAROUSEL";
    private List<Pokemon> pokemons;

    private void initializeData(){
        pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", "Grass / Poison", R.drawable.pokemon_bulbasaur));
        pokemons.add(new Pokemon("Charmander", "Fire", R.drawable.pokemon_charmander));
        pokemons.add(new Pokemon("Squirtle", "Water", R.drawable.pokemon_squirtle));
        pokemons.add(new Pokemon("Caterpie", "Bug", R.drawable.pokemon_caterpie));
        pokemons.add(new Pokemon("Pikachu", "Electric", R.drawable.pokemon_pikachu));
        pokemons.add(new Pokemon("Oddish", "Grass / Poison", R.drawable.pokemon_oddish));
        pokemons.add(new Pokemon("Meowth", "Normal", R.drawable.pokemon_meowth));
        pokemons.add(new Pokemon("Psyduck", "Water", R.drawable.pokemon_psyduck));
        pokemons.add(new Pokemon("Magikarp", "Water", R.drawable.pokemon_magikarp));
        pokemons.add(new Pokemon("Snorlax", "Normal", R.drawable.pokemon_snorlax));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
        Intent intent = getIntent();
        String type = intent.getStringExtra(LIST_TYPE);
        switch (type)
        {
            case LIST_TYPE_NORMAL:
                setContentView(R.layout.activity_my_list_normal);
                RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
                rv.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(this);
                rv.setLayoutManager(llm);
                RVAdapter adapter = new RVAdapter(pokemons);
                rv.setAdapter(adapter);
                break;
            case LIST_TYPE_CAROUSEL:
                setContentView(R.layout.activity_my_list_carousel);
                break;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void doNothing(MenuItem item) {
        GlobalMenu.doNothing(this, item);
    }

}

