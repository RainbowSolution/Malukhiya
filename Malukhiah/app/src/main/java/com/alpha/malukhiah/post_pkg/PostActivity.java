package com.alpha.malukhiah.post_pkg;

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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.authenticationModule.Login_Activity;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.model.AddsPostPkgModel.AddsPost;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
import com.alpha.malukhiah.model.categoryPozoPkg.Datum;

import com.alpha.malukhiah.model.loginPkg.LoginPozo;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildCategoryPozo;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildDatum;
import com.alpha.malukhiah.model.subcategoryPozoPkg.SubCategoryPozo;
import com.alpha.malukhiah.model.subcategoryPozoPkg.SubDatum;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.CategoryAdapter;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.SubCategoryAdapter;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.SubChildCategoryAdapter;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.alpha.malukhiah.utility.ImagePicker;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.android.libraries.places.api.model.Place.Field;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alpha.malukhiah.utility.ImagePicker.getRealPathFromURI;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText tv_tittle, tv_add_price, tv_description;
    AppCompatSpinner sp_selection_category, sp_selection_sub_category, sp_selection_sub_child_category;
    LinearLayout add_image_id;
    AppCompatButton btn_post;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_add_location,tv_image_path;
    AppCompatTextView tv_title;
    CardView card_sub_Category,card_sub_child_Category;
    AppCompatImageView iv_notification;
    private static Animation shakeAnimation;
    List<Datum> categoryList;
    List<SubDatum> subcategoryList;
    private static final int PERMISSION_READ_EXTERNAL_STORAGE = 100;
    private static final int SELECT_PICTURE = 101;
    List<SubChildDatum> subChildDatumList;
    String selectedCategory, selectedSubCategory, selectedSubchildCategory;
    int AUTOCOMPLETE_REQUEST_CODE = 1;
    Double vendor_lat, vendor_long;
    private String userid, profilImgPath;
    private File fileForImage;
    private String selectedCategoryId,selectedSubCategoryId,selectedChildCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_post);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        tv_image_path = findViewById(R.id.tv_image_path);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tv_add_location = findViewById(R.id.tv_add_location_Id);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        tv_title.setText(getResources().getString(R.string.postyourad));
        categoryList = new ArrayList<>();
        subcategoryList = new ArrayList<>();
        subChildDatumList = new ArrayList<>();
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        tv_tittle = findViewById(R.id.tv_tittle_id);
        sp_selection_category = findViewById(R.id.sp_selection_category_Id);
        sp_selection_sub_category = findViewById(R.id.sp_selection_sub_category_Id);
        sp_selection_sub_child_category = findViewById(R.id.sp_selection_sub_child_category_Id);
        tv_add_price = findViewById(R.id.tv_add_price_Id);
        tv_description = findViewById(R.id.tv_description_Id);
        add_image_id = findViewById(R.id.add_image_id);
        card_sub_Category = findViewById(R.id.card_sub_Category_Id);
        card_sub_child_Category = findViewById(R.id.card_sub_child_Category_Id);
        btn_post = findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);
        add_image_id.setOnClickListener(this);
        getCategoryList();
        userid = AppSession.getStringPreferences(PostActivity.this, Constants.USER_ID);
        sp_selection_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryId = categoryList.get(position).getId();
                selectedCategory = categoryList.get(position).getTitle();
                System.out.println("getCityList======" + selectedCategoryId);
                getSubCategory(selectedCategoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_selection_sub_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubCategoryId = subcategoryList.get(position).getSubCatId();
                selectedSubCategory = subcategoryList.get(position).getSubcatName();
                System.out.println("getCityList======" + selectedSubCategoryId);
                getSubChildCategory(selectedSubCategoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_selection_sub_child_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedChildCategoryId = subChildDatumList.get(position).getSubCatId();
                selectedSubchildCategory = subChildDatumList.get(position).getSubcatName();
                System.out.println("getCityList======" + selectedChildCategoryId);
                //getSubChildCategory(selectedStateID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tv_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Field> fields = Arrays.asList(Field.ADDRESS, Field.NAME, Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(PostActivity.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                vendor_lat = place.getLatLng().latitude;
                vendor_long = place.getLatLng().longitude;
                tv_add_location.setText(place.getAddress());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else {
            if (requestCode == SELECT_PICTURE) {
                if (resultCode == RESULT_OK) {
                    try {
                        Uri imageUri = data.getData();
                        Bitmap bitmap = ImagePicker.getImageFromResult(PostActivity.this, resultCode, data);
                        profilImgPath = getRealPathFromURI(PostActivity.this, imageUri);
                        tv_image_path.setText(profilImgPath);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(PostActivity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.add_image_id:
                askStoragePermission();
                break;
            case R.id.btn_post:
                validation(v);
                 break;
        }
    }


        private void validation(View v) {
            if (tv_tittle.getText().toString().isEmpty()) {
                new CustomToast().Show_Toast(this, v, "Tittle Can't Empty");
                tv_tittle.startAnimation(shakeAnimation);
                tv_tittle.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tv_tittle.requestFocus();
            } else if (tv_add_location.getText().toString().isEmpty()) {
                new CustomToast().Show_Toast(this, v, "Add Location Can't Empty");
                tv_add_location.startAnimation(shakeAnimation);
                tv_add_location.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tv_add_location.requestFocus();
            } else if (tv_add_price.getText().toString().isEmpty()) {
                new CustomToast().Show_Toast(this, v, "Add Price Can't Empty");
                tv_add_price.startAnimation(shakeAnimation);
                tv_add_price.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tv_add_price.requestFocus();
            } else if (tv_description.getText().toString().isEmpty()) {
                new CustomToast().Show_Toast(this, v, "Description Can't Empty");
                tv_description.startAnimation(shakeAnimation);
                tv_description.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                tv_description.requestFocus();
            } else if (profilImgPath==null) {
                new CustomToast().Show_Toast(this, v, "Image Can't Empty");
                add_image_id.startAnimation(shakeAnimation);
                add_image_id.requestFocus();
            }else if (CheckNetwork.isNetAvailable(this)) {
                updateUser();
            } else {
                new CustomToast().Show_Toast(this, v, getString(R.string.plz_check_your_intrenet_text));
            }
        }

    public void getCategoryList() {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            (RetrofitClient.getClient().getCategory()).enqueue(new Callback<CategoryPozo>() {
                @Override
                public void onResponse(Call<CategoryPozo> call, Response<CategoryPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            CategoryPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                categoryList = sectorPojo.getData();
                                CategoryAdapter customAdapter = new CategoryAdapter(getApplicationContext(), categoryList, "state");
                                sp_selection_category.setAdapter(customAdapter);

                            } else {
                                Toast.makeText(PostActivity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<CategoryPozo> call, Throwable t) {
                    Toast.makeText(PostActivity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }

    public void getSubCategory(String selectedStateID) {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            (RetrofitClient.getClient().getSubCategory(selectedStateID)).enqueue(new Callback<SubCategoryPozo>() {
                @Override
                public void onResponse(Call<SubCategoryPozo> call, Response<SubCategoryPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            SubCategoryPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                subcategoryList = sectorPojo.getData();
                                SubCategoryAdapter customAdapter = new SubCategoryAdapter(getApplicationContext(), subcategoryList, "state");
                                sp_selection_sub_category.setAdapter(customAdapter);

                            } else {
                                card_sub_Category.setVisibility(View.GONE);
                                Toast.makeText(PostActivity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<SubCategoryPozo> call, Throwable t) {
                    Toast.makeText(PostActivity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }

    public void getSubChildCategory(String selectedStateID) {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            (RetrofitClient.getClient().getSubChildCategory(selectedStateID)).enqueue(new Callback<SubChildCategoryPozo>() {
                @Override
                public void onResponse(Call<SubChildCategoryPozo> call, Response<SubChildCategoryPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            SubChildCategoryPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                subChildDatumList = sectorPojo.getData();
                                SubChildCategoryAdapter customAdapter = new SubChildCategoryAdapter(getApplicationContext(), subChildDatumList, "state");
                                sp_selection_sub_child_category.setAdapter(customAdapter);

                            } else {
                                card_sub_child_Category.setVisibility(View.GONE);
                                Toast.makeText(PostActivity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<SubChildCategoryPozo> call, Throwable t) {
                    Toast.makeText(PostActivity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser() {
        CustomProgressbar.showProgressBar(PostActivity.this, false);
        MultipartBody.Part bnnerimgFileStation = null;
        MultipartBody.Part imgFileStation = null;
        if (profilImgPath == null) {

        } else {
            fileForImage = new File(profilImgPath);
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("multipart/form-data"), fileForImage);
            imgFileStation = MultipartBody.Part.createFormData("image", fileForImage.getName(), requestFileOne);
        }
        MultipartBody.Part user_id = MultipartBody.Part.createFormData("user_id", userid);
        MultipartBody.Part title = MultipartBody.Part.createFormData("title", tv_title.getText().toString());
        MultipartBody.Part cat_id = MultipartBody.Part.createFormData("cat_id", selectedCategoryId);
        MultipartBody.Part price = MultipartBody.Part.createFormData("price",tv_add_price.getText().toString());
        MultipartBody.Part description = MultipartBody.Part.createFormData("description", tv_description.getText().toString());
        MultipartBody.Part location = MultipartBody.Part.createFormData("location", tv_add_location.getText().toString());
        MultipartBody.Part latitude = MultipartBody.Part.createFormData("latitude", String.valueOf(vendor_lat));
        MultipartBody.Part longnitue = MultipartBody.Part.createFormData("longnitue", String.valueOf(vendor_long));
        MultipartBody.Part sub_cat_id = MultipartBody.Part.createFormData("sub_cat_id", String.valueOf(selectedSubCategoryId));
        MultipartBody.Part child_subcat_id = MultipartBody.Part.createFormData("child_subcat_id", String.valueOf(selectedChildCategoryId));
            (RetrofitClient.getClient().AddsPost(user_id, title, cat_id,price,description,location,latitude,longnitue,sub_cat_id,child_subcat_id,imgFileStation))
                    .enqueue(new Callback<AddsPost>() {
                @Override
                public void onResponse(Call<AddsPost> call, Response<AddsPost> response) {
                    if (response.isSuccessful()) {
                        CustomProgressbar.hideProgressBar();
                        AddsPost loginResponseModle = response.body();
                        if (loginResponseModle.getStatus()) {
                            finish();
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                            Toast.makeText(PostActivity.this, loginResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(PostActivity.this, loginResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (response.code() == 400) {
                            Toast.makeText(PostActivity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
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
                public void onFailure(Call<AddsPost> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                }
            });


        }







    private void askStoragePermission() {
        if (ActivityCompat.checkSelfPermission(PostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(PostActivity.this,
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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseFromGallery();
                } else {
                    Toast.makeText(PostActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
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


}
