package com.alpha.malukhiah.profile_pkg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.profilePozoPkg.ProfilePozo;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.alpha.malukhiah.utility.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    private View toolbar;
    private AppCompatTextView tv_title;
    private AppCompatImageView iv_notification, ivCameraImgEdit;
    private static Animation shakeAnimation;
    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 100;
    private static final int SELECT_PICTURE = 101;
    private String profilImgPath, userid, fullName, userEmail, userImage, userNumber, gender, selectedGender, profile;
    private CircleImageView civProfil;
    private TextInputEditText tv_FnameProfile_, tv_emailProfile_, tv_numberProfile_;
    private RadioGroup radioGrp;
    private AppCompatButton btn_post;
    private RadioButton rbMale, rbFeMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        userid = AppSession.getStringPreferences(getApplicationContext(), Constants.USER_ID);
        userEmail = AppSession.getStringPreferences(getApplicationContext(), Constants.EMAIL);
        fullName = AppSession.getStringPreferences(getApplicationContext(), Constants.USERNAME);
        userImage = AppSession.getStringPreferences(getApplicationContext(), Constants.PROFILE_PIC);
        userNumber = AppSession.getStringPreferences(getApplicationContext(), Constants.MOBILE_NUMBER);
        gender = AppSession.getStringPreferences(getApplicationContext(), Constants.GENDER);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        btn_post = findViewById(R.id.btn_post);
        radioGrp = findViewById(R.id.radioGrp);
        rbMale = findViewById(R.id.radioM);
        rbFeMale = findViewById(R.id.radioF);
        tv_FnameProfile_ = findViewById(R.id.tv_FnameProfile_id);
        tv_emailProfile_ = findViewById(R.id.tv_emailProfile_id);
        tv_numberProfile_ = findViewById(R.id.tv_numberProfile_id);
        ivCameraImgEdit = findViewById(R.id.ivCameraImgEditId);
        civProfil = findViewById(R.id.civProfilId);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        ivCameraImgEdit.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.editprofile));
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);


        tv_FnameProfile_.setText(fullName);
        tv_emailProfile_.setText(userEmail);
        tv_numberProfile_.setText(userNumber);

        if (userImage == null || userImage.isEmpty()) {
        } else {
            Picasso.with(getApplicationContext())
                    .load(RetrofitClient.PROFILE + userImage)
                    .resize(200, 200)
                    .into(civProfil);
        }


        if (gender == null || gender.isEmpty()) {
        } else {
            if (gender.equalsIgnoreCase("male")) {
                rbMale.setChecked(true);
            } else {
                rbFeMale.setChecked(true);
            }
        }

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioM:
                        selectedGender = "Male";
                        break;
                    case R.id.radioF:
                        selectedGender = "FeMale";
                        break;

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                validation(v);
                break;
            case R.id.ivCameraImgEditId:
                askStoragePermission();
                break;
            case R.id.id_notification:
                startActivity(new Intent(EditProfile_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }


    private void validation(View v) {
        if (tv_FnameProfile_.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getString(R.string.full_name_cant_empty));
            tv_FnameProfile_.startAnimation(shakeAnimation);
            tv_FnameProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_FnameProfile_.requestFocus();
        } else if (!tv_FnameProfile_.getText().toString().matches("^[a-zA-Z\\s]+")) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_fname));
            tv_FnameProfile_.startAnimation(shakeAnimation);
            tv_FnameProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_FnameProfile_.requestFocus();

        } else if (tv_emailProfile_.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getString(R.string.email_cant_empty));
            tv_emailProfile_.startAnimation(shakeAnimation);
            tv_emailProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_emailProfile_.requestFocus();

        } else if (!Constants.isValidEmail(tv_emailProfile_.getText().toString())) {
            new CustomToast().Show_Toast(this, v, getString(R.string.invalid_email));
            tv_emailProfile_.startAnimation(shakeAnimation);
            tv_emailProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_emailProfile_.requestFocus();
        } else if (tv_numberProfile_.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getString(R.string.mobile_number_cant_empty));
            tv_numberProfile_.startAnimation(shakeAnimation);
            tv_numberProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_numberProfile_.requestFocus();

        } else if (tv_numberProfile_.getText().toString().length() < 10) {
            new CustomToast().Show_Toast(this, v, getString(R.string.invalid_mobile));
            tv_numberProfile_.startAnimation(shakeAnimation);
            tv_numberProfile_.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_numberProfile_.requestFocus();
        } else if (selectedGender == null) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.select_gender));
        } else {
            if (CheckNetwork.isNetAvailable(getApplicationContext())) {
                updateProfile(profilImgPath);
            } else {
                new CustomToast().Show_Toast(this, v, getResources().getString(R.string.chek_network_connection));
            }
        }
    }

    private void updateProfile(String profilImgPath) {
        CustomProgressbar.showProgressBar(this, false);
        MultipartBody.Part imgFileStation = null;
        if (profilImgPath == null) {
        } else {
            File fileForImage = new File(profilImgPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileForImage);
            imgFileStation = MultipartBody.Part.createFormData("file", fileForImage.getName(), requestFileOne);
        }


        String tv_FnameProfile = tv_FnameProfile_.getText().toString();
        String tv_emailProfile = tv_emailProfile_.getText().toString();
        String tv_numberProfile = tv_numberProfile_.getText().toString();
        MultipartBody.Part user_id = MultipartBody.Part.createFormData("user_id", userid);
        MultipartBody.Part user_fname = MultipartBody.Part.createFormData("name", tv_FnameProfile);
        MultipartBody.Part user_email = MultipartBody.Part.createFormData("email", tv_emailProfile);
        MultipartBody.Part user_mobile = MultipartBody.Part.createFormData("number", tv_numberProfile);
        MultipartBody.Part user_gender = MultipartBody.Part.createFormData("gender", selectedGender);
        System.out.println("id===" + userid + "==user_fullname===" + tv_FnameProfile + "==tv_emailAddress==" + tv_emailProfile
                + "==tv_mobileNumber==" + tv_numberProfile + "===image==" + imgFileStation);
        (RetrofitClient.getClient().editProfile(user_id, user_fname, user_email, user_mobile,
                user_gender, imgFileStation)).enqueue(new Callback<ProfilePozo>() {
            @Override
            public void onResponse(Call<ProfilePozo> call, Response<ProfilePozo> response) {
                CustomProgressbar.hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        Log.e("TAG", "edit profile  response : " + new Gson().toJson(response.body()));
                        ProfilePozo userInfoPojo = response.body();
                        Boolean repmsg = userInfoPojo.getStatus();
                        String message = userInfoPojo.getMessage();
                        if (repmsg) {
                            AppSession.setStringPreferences(EditProfile_Activity.this, Constants.USERNAME, userInfoPojo.getData().getUserFullname());
                            AppSession.setStringPreferences(EditProfile_Activity.this, Constants.MOBILE_NUMBER, userInfoPojo.getData().getUserPhone());
                            AppSession.setStringPreferences(EditProfile_Activity.this, Constants.EMAIL, userInfoPojo.getData().getUserEmail());
                            AppSession.setStringPreferences(EditProfile_Activity.this, Constants.GENDER, userInfoPojo.getData().getGender());
                            AppSession.setStringPreferences(EditProfile_Activity.this, Constants.PROFILE_PIC, userInfoPojo.getData().getUserImage());
                            Toast.makeText(EditProfile_Activity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditProfile_Activity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ProfilePozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
                Toast.makeText(EditProfile_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });

    }


    //    =================== image upload ======
    private void askStoragePermission() {
        if (ActivityCompat.checkSelfPermission(EditProfile_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(EditProfile_Activity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_READ_EXTERNAL_STORAGE);
            }
        } else {
            chooseFromGallery();
        }
    }

    private void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), SELECT_PICTURE);
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseFromGallery();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.permission_denied), Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = ImagePicker.getImageFromResult(getApplicationContext(), resultCode, data);
                    civProfil.setImageBitmap(bitmap);
                    profilImgPath = getRealPathFromURI(getApplicationContext(), imageUri);
                    System.out.println("profile path===" + profilImgPath);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //    ===============================


}
