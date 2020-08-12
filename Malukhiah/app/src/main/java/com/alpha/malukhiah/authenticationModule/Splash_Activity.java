package com.alpha.malukhiah.authenticationModule;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;

public class Splash_Activity extends AppCompatActivity {
    private String loginEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
      /*  try {
            loginEntry = AppSession.getStringPreferences(getApplicationContext(), Constants.STATUS);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loginEntry == null || loginEntry.isEmpty()) {
                        CheckNetwork.nextScreenWithFinish(Splash_Activity.this, Loginoption.class);
                    } else {
                        CheckNetwork.nextScreenWithFinish(Splash_Activity.this, Loginoption.class);
                    }
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
