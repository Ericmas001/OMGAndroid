package com.ericmas001.omgandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Pokemon {
    int id;
    String name;
    String type;
    boolean selected = false;
    Bitmap bmp;

    Pokemon(int id, String name, String type, final String photoUrl) throws ExecutionException, InterruptedException {
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

    public static final List<Pokemon> Pokemons = new ArrayList<>();
}
