package com.ericmas001.omgandroid.viewmodels;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ericmas001.omgandroid.helpers.MenuHelper;
import com.ericmas001.omgandroid.R;

public class MainActivity extends AppCompatActivity {

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
        MenuHelper.doNothing(this, item);
    }

    public void openNormalList(View view) {
        OpenList(MyListActivity.LIST_TYPE_NORMAL);
    }

    public void openCarouselList(View view) {
        OpenList(MyListActivity.LIST_TYPE_CAROUSEL);
    }

    private void OpenList(String parm){
        Intent intent = new Intent(this, MyListActivity.class);
        intent.putExtra(MyListActivity.LIST_TYPE, parm);
        startActivity(intent);
    }

    public void sendMail(View view) {
        Snackbar.make(view, getString(R.string.action_sendmail), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
