package com.ericmas001.omgandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String LIST_TYPE = "com.ericmas001.omgandroid.LIST_TYPE";
    public final static String LIST_TYPE_NORMAL = "NORMAL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    public void openNormalList(View view) {
        Intent intent = new Intent(this, MyListActivity.class);
        intent.putExtra(LIST_TYPE, LIST_TYPE_NORMAL);
        startActivity(intent);
        //Snackbar.make(view, getString(R.string.action_openNormalList), Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();
    }

    public void sendMail(View view) {
        Snackbar.make(view, getString(R.string.action_sendmail), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
