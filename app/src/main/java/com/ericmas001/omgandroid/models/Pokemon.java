package com.ericmas001.omgandroid.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Pokemon {
    private int id;
    private String name;
    private String type;
    private boolean selected = false;
    private Bitmap bmp;

    public Pokemon(int id, String name, String type, final String photoUrl) throws ExecutionException, InterruptedException {
        this.id = id;
        this.name = name;
        this.type = type;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    InputStream in = new URL(photoUrl).openStream();
                    bmp = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    // log error
                }
                return null;
            }
        }.execute().get();
    }

    private static final List<Pokemon> Pokemons = new ArrayList<>();

    public static List<Pokemon> getPokemons() {
        return Pokemons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Bitmap getBmp() {
        return bmp;
    }
}
