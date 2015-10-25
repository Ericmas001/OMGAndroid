package com.ericmas001.omgandroid.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {
    private static String last = null;
    public static void setLocale(Context ctx) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String lang = prefs.getString("gen_app_language", "");
        if(last == null)
        {
            last = lang;
            Locale locale = Locale.getDefault();
            if(!lang.isEmpty())
                locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            ctx.getResources().updateConfiguration(config,
            ctx.getResources().getDisplayMetrics());
        }
        else if(!last.equals(lang))
        {
            last = null;
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage( ctx.getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ctx.startActivity(i);
        }
    }
}
