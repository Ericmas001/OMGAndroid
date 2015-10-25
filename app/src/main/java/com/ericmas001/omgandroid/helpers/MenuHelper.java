package com.ericmas001.omgandroid.helpers;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.ericmas001.omgandroid.R;
import com.ericmas001.omgandroid.viewmodels.MainActivity;
import com.ericmas001.omgandroid.viewmodels.SettingsActivity;

public class MenuHelper {
    public static void openSettings(Activity activity, MenuItem item) {

        Intent intent = new Intent(activity,SettingsActivity.class);
        activity.startActivity(intent);
    }
}
