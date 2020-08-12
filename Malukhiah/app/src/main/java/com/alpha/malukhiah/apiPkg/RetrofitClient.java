package com.alpha.malukhiah.apiPkg;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

   // http://15.206.103.57/GrocerieIndia/api

   // http://15.206.103.57/Malukhiah/api/index.php/Authentication/login

    public static final String PROFILE = "http://15.206.103.57/Malukhiah/api/uploads/";
    public static final String BASE_URL = "http://15.206.103.57/Malukhiah/api/index.php/";
    public static final String PRODUCT_IMAGE_URL = "http://15.206.103.57//Malukhiah/api/uploads/post_adds/";
    public static final String CATEGORY_IMAGE_URL = "http://15.206.103.57/Malukhiah/uploads/category/";
    public static final String SUB_CATEGORY_IMAGE_URL = "http://15.206.103.57/Malukhiah/uploads/subcat/";
    public static final String BANNER_URL = "http://15.206.103.57/Malukhiah/api/Banner/";
    public static final String OFFER_BANNER_URL = "http://15.206.103.57/Malukhiah/uploads/sliders/";

    public static final String MEMBERSHIP_URL = "http://15.206.103.57/Malukhiah/uploads/plan";
    public static final String User_Profile ="http://15.206.103.57//Malukhiah/api/uploads/";


    private static OkHttpClient client;
    private static Retrofit retrofit = null;



    public static ApiServices getClient() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(5, TimeUnit.MINUTES);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        ApiServices api = retrofit.create(ApiServices.class);
        return api;
    }

}