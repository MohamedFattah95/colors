
package com.fci.colors_app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.fci.colors_app.R;

public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void openPlayStoreForApp(Context context) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + context.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + context.getPackageName())));
        }
    }

    public static void shareApp(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                context.getResources().getString(R.string.app_name) + "\n" +
                        context.getResources().getString(R.string.app_google_play_store_link) +
                        context.getPackageName());
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static boolean isAppAvailable(Context context, String appName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
