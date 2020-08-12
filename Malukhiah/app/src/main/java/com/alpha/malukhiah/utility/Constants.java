package com.alpha.malukhiah.utility;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Constants {

    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static String STATUS = "preference_data";
    public static final String NOTIFICATION="notification";
    public static final String SETTING="settings";
    public static final String MYORDER="myorder";
    public static final String CATEGORY="category";
    public static final String HOME="home";
    public static final String HELP="help";
    public static final String PROFILE="profile";
    public static final String WISHLIST="wishlist";
    public static final String SEARCH="search";
    public static final String STORE="store";
    public static final String ALLTABLAYOUTS="alltab";
    public static final String PRODUCT_DETAIL_BY_WISHLIST="productDetailbyWishList";
    public static final String PRODUCT_DETAIL_BY_SUBCATEGORY="productDetailbySubcategory";
    public static final String PRODUCT_DETAIL_BY_FIRST_CATEGORY="productDetailbyFirstCategroy";
    public static final String PRODUCT_DETAIL_BY_SEC_CATEGORY="productDetailbySecCategroy";
    public static final String PRODUCT_DETAIL_BY_MOST="productDetailbymost";
    public static final String PRODUCT_DETAIL_BY_SEARCH="productDetailbySearch";



    public static final void customToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidEmail(String target) {
        boolean flag;
        if (TextUtils.isEmpty(target)) {
            return true;
        } else {
            flag = emailValidator(target);
            if (flag) {
                // return flag;
            }
            return flag;
        }
        // return (!TextUtils.isEmpty(target) || Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean vehicleNumberValidator(String number) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[A-Za-z]{2}[ -][0-9]{1,2}(?: [A-Za-z])?(?: [A-Za-z]*)? [0-9]{4}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }


    public static boolean isValidVehicleNumber(String target) {
        boolean flag;
        if (TextUtils.isEmpty(target)) {
            return true;
        } else {
            flag = vehicleNumberValidator(target);
            if (flag) {
                // return flag;
            }
            return flag;
        }
        // return (!TextUtils.isEmpty(target) || Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    public static String currentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        return strDate;
    }

    public static String changeTimeFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MM-yyy hh:mm";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
