package com.alpha.malukhiah.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alpha.malukhiah.R;

public class CheckNetwork {
    public static boolean isNetAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void nextScreenWithoutFinish(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }



    public static void nextScreenFinishAllTop(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);


    }

    public static void nextScreenWithFinish(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        ((Activity) (context)).finish();


    }


    public static void goTobackScreen(Context context, Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        ((Activity) (context)).finish();
    }

    public static void backScreenWithFinis(Context context) {
        ((Activity) (context)).finish();
        ((Activity) (context)).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }
}
