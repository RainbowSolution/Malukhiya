package com.alpha.malukhiah.post;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import com.alpha.malukhiah.R;
import com.google.android.material.textfield.TextInputEditText;

public class PostActivity extends AppCompatActivity {
    TextInputEditText tv_tittle,tv_add_price,tv_description;
    AppCompatSpinner sp_selection_category;
    LinearLayout  add_image_id;
    AppCompatButton  btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_post);
        //   apiServices = RetrofitClient.getClient().create(ApiServices.class);
        // init();
        tv_tittle = findViewById(R.id.tv_tittle_id);
        sp_selection_category = findViewById(R.id.sp_selection_category_Id);
        tv_add_price = findViewById(R.id.tv_add_price_Id);
        tv_description = findViewById(R.id.tv_description_Id);
        add_image_id = findViewById(R.id.add_image_id);
        btn_post = findViewById(R.id.btn_post);

    }
}
