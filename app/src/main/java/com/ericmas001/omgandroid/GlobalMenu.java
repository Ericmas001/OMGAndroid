package com.ericmas001.omgandroid;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.Toast;

public class GlobalMenu {
    public static void doNothing(Activity activity, MenuItem item) {
        Toast.makeText(activity, activity.getString(R.string.action_nothing_message), Toast.LENGTH_SHORT).show();
    }
}
