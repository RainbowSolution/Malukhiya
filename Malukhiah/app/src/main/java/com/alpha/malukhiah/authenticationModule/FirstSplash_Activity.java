package com.alpha.malukhiah.authenticationModule;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;

public class FirstSplash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_splash);
        try {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                        CheckNetwork.nextScreenWithFinish(FirstSplash_Activity.this, Splash_Activity.class);
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
