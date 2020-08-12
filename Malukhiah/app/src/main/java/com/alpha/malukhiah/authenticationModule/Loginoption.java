package com.alpha.malukhiah.authenticationModule;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.utility.CheckNetwork;


public class Loginoption extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton abtnSignInCustomer,abtnSignInVendor;
    LinearLayout li_continue_email;
    AppCompatTextView tv_new_user,tvRegisteredNowId,tv_continue_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        tv_continue_email = findViewById(R.id.tv_continue_email);
        tv_new_user  = findViewById(R.id.tv_new_user);
        tvRegisteredNowId = findViewById(R.id.tvRegisteredNowId);
   //     abtnSignInCustomer.setOnClickListener(this);
        tvRegisteredNowId.setOnClickListener(this);
        tv_new_user.setOnClickListener(this);
        tv_continue_email.setOnClickListener(this);
        // apiServices = RetrofitClient.getClient().create(ApiServices.class);
        // init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_continue_email:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, Login_Activity.class);
                break;
            case R.id.tv_new_user:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, SignUp_Activity.class);
                break;
            case R.id.tvRegisteredNowId:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, SignUp_Activity.class);
                break;
        }
    }
}
