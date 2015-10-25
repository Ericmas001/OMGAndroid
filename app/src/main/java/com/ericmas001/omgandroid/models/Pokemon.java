package com.ericmas001.omgandroid.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
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
    private String bmpUrl;

    public Pokemon(int id, String name, String type, final String photoUrl) throws ExecutionException, InterruptedException {
        this.id = id;
        this.name = name;
        this.type = type;
        this.bmpUrl = photoUrl;
    }

    public void BindImage(ImageView iv)
    {
        iv.setImageBitmap(null);
        if(bmp == null) {
            BitmapWorkerTask task = new BitmapWorkerTask(iv);
            task.execute();
        }
        else
            iv.setImageBitmap(bmp);
    }
    class BitmapWorkerTask extends AsyncTask<Void, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Void... params) {
            InputStream in = null;
            try {
                in = new URL(bmpUrl).openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return BitmapFactory.decodeStream(in);
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bmp = bitmap;
            if (bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
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
