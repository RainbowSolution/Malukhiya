package com.alpha.malukhiah.authenticationModule;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.ApiServices;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.model.signupPkg.SignUpPozo;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    private static Animation shakeAnimation;
    private ApiServices apiServices;
    private String token;
    private AppCompatImageView ivBackForgetId;
    private AppCompatTextView tv_back_signup;
    private AppCompatTextView tv_add_location;
    private AppCompatButton btn_signup;
    int AUTOCOMPLETE_REQUEST_CODE = 1;
    private TextInputEditText tv_email, tv_password, tv_confirm_password, tv_mobile, tv_name;
    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_signup);
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        init();
    }


    private void init() {
        tv_back_signup = findViewById(R.id.tv_back_signup);
        ivBackForgetId = findViewById(R.id.ivBackForgetId);
        tv_email = findViewById(R.id.tv_email);
        tv_name = findViewById(R.id.tv_name);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_password = findViewById(R.id.tv_password);
        tv_confirm_password = findViewById(R.id.tv_confirm_password);
        tv_add_location = findViewById(R.id.tv_add_location);
        btn_signup = findViewById(R.id.btn_signup);
        tv_back_signup.setOnClickListener(this);
        ivBackForgetId.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        tv_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(SignUp_Activity.this);
                startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                tv_add_location.setText(place.getAddress());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
        @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackForgetId:
                CheckNetwork.backScreenWithFinis(SignUp_Activity.this);
                break;
            case R.id.btn_signup:
                 validation(v);
                break;
            case R.id.tv_back_signup:
                CheckNetwork.backScreenWithFinis(SignUp_Activity.this);
                break;
        }
    }

    private void validation(View v) {
        if (tv_name.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.name_cannot_empty));
            tv_name.startAnimation(shakeAnimation);
            tv_name.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_name.requestFocus();
        } else if (!tv_name.getText().toString().matches("^[a-zA-Z\\s]+")) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_name));
            tv_name.startAnimation(shakeAnimation);
            tv_name.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_name.requestFocus();
        }
        else if (tv_mobile.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.mobile_number_cant_empty));
            tv_mobile.startAnimation(shakeAnimation);
            tv_mobile.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_mobile.requestFocus();
        }
        else if (tv_mobile.getText().toString().length() < 10) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_mobile));
            tv_mobile.startAnimation(shakeAnimation);
            tv_mobile.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_mobile.requestFocus();
        }else if (tv_add_location.getText().toString().isEmpty()){
            new CustomToast().Show_Toast(this, v, "Invalid Location");
            tv_add_location.startAnimation(shakeAnimation);
            tv_add_location.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_add_location.requestFocus();
        } else if (tv_email.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.email_cant_empty));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_email.requestFocus();
        } else if (!Constants.isValidEmail(tv_email.getText().toString())) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_email));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_email.requestFocus();
        } else if (tv_password.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.password_cant_empty));
            tv_password.startAnimation(shakeAnimation);
            tv_password.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_password.requestFocus();
        } else if (tv_password.getText().toString().length() <= 6) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.pass_digit_must_be_greater_than_six));
            tv_password.startAnimation(shakeAnimation);
            tv_password.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_password.requestFocus();
        } else if (CheckNetwork.isNetAvailable(this)) {
            signup(tv_name.getText().toString().trim(),
                    tv_mobile.getText().toString(),
                    tv_email.getText().toString().trim(),
                    tv_password.getText().toString().trim());
        } else {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.chek_network_connection));
        }
    }

    private void signup(String fullName,String phone, String email, String pass) {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().registration(fullName,phone,email,"Gapan",pass,"jkjkjkjkjkj")).enqueue(new Callback<SignUpPozo>() {
            @Override
            public void onResponse(Call<SignUpPozo> call, Response<SignUpPozo> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    SignUpPozo registrationResponseModle = response.body();
                    if (registrationResponseModle.getStatus()) {
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.STATUS, "auth");
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.USER_ID, registrationResponseModle.getData().getUserId());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.USERNAME, registrationResponseModle.getData().getUserFullname());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.MOBILE_NUMBER, registrationResponseModle.getData().getUserPhone());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.EMAIL, registrationResponseModle.getData().getUserEmail());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.GENDER, registrationResponseModle.getData().getGender());
                        CheckNetwork.nextScreenFinishAllTop(SignUp_Activity.this, HomeActivity.class);
                    } else {
                        Toast.makeText(getApplicationContext(), registrationResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
