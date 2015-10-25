package com.ericmas001.omgandroid.viewmodels;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ericmas001.omgandroid.helpers.LocaleHelper;
import com.ericmas001.omgandroid.helpers.MenuHelper;
import com.ericmas001.omgandroid.models.Pokemon;
import com.ericmas001.omgandroid.R;
import com.ericmas001.omgandroid.adapters.RVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GeneralPokedexActivity extends AppCompatActivity {
    public final static String LIST_TYPE_LIST = "LIST";
    public final static String LIST_TYPE_GRID = "GRID";
    public final static String LIST_TYPE_SUPERGRID = "SUPERGRID";
    ProgressBar progressBar;
    static final String API_URL = "http://webservice.ericmas001.com/api/pokemons";
    private String listType = LIST_TYPE_LIST;

    @Override
    protected void onResume() {
        LocaleHelper.setLocale(this);
        String type = PreferenceManager.getDefaultSharedPreferences(this).getString("gen_display_layout", LIST_TYPE_LIST);
        if(!type.equals(listType)){
            listType = type;
            recreate();
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(this);
        listType =  PreferenceManager.getDefaultSharedPreferences(this).getString("gen_display_layout", LIST_TYPE_LIST);
        setContentView(R.layout.activity_my_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        switch (listType) {
            case LIST_TYPE_LIST: {
                LinearLayoutManager llm = new LinearLayoutManager(this);
                rv.setLayoutManager(llm);
                break;
            }
            case LIST_TYPE_GRID: {
                GridLayoutManager glm = new GridLayoutManager(this, 2);
                rv.setLayoutManager(glm);
                break;
            }
            case LIST_TYPE_SUPERGRID: {
                StaggeredGridLayoutManager glm = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
                rv.setLayoutManager(glm);
                break;
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        if(Pokemon.getPokemons().isEmpty())
            new RetrieveFeedTask().execute();
        else
            fillWithPokemons();
    }

    private void fillWithPokemons() {
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        RVAdapter adapter = new RVAdapter(Pokemon.getPokemons());
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void doNothing(MenuItem item) {

        MenuHelper.openSettings(this, item);
    }

    public void refresh(View view) {
        new RetrieveFeedTask().execute();
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Pokemon.getPokemons().clear();
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            try {
                JSONArray json = new JSONArray(response);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject j = json.getJSONObject(i);
                    Pokemon p = new Pokemon(j.getInt("Id"),j.getString("Name"),j.getString("Type"), j.getString("Photo"));
                    Pokemon.getPokemons().add(p);
                }
                fillWithPokemons();
            } catch (JSONException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

