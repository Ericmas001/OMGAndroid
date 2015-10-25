package com.ericmas001.omgandroid.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ericmas001.omgandroid.helpers.LocaleHelper;
import com.ericmas001.omgandroid.helpers.MenuHelper;
import com.ericmas001.omgandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        LocaleHelper.setLocale(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void doNothing(MenuItem item) {
        MenuHelper.openSettings(this, item);
    }

    public void openNormalList(View view) {
        Intent intent = new Intent(this, GeneralPokedexActivity.class);
        startActivity(intent);
    }

    public void sendMail(View view) {
        Snackbar.make(view, getString(R.string.action_sendmail), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
