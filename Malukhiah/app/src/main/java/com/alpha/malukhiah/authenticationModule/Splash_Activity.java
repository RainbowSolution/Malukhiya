package com.alpha.malukhiah.authenticationModule;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Splash_Activity extends AppCompatActivity {
    private String loginEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hash();
        try {
            loginEntry = AppSession.getStringPreferences(getApplicationContext(), Constants.STATUS);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loginEntry == null || loginEntry.isEmpty()) {
                        CheckNetwork.nextScreenWithFinish(Splash_Activity.this, Loginoption.class);
                    } else {
                        CheckNetwork.nextScreenWithFinish(Splash_Activity.this, HomeActivity.class);
                    }
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hash(){
        PackageInfo info;
        try {
             info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }
    }
}
