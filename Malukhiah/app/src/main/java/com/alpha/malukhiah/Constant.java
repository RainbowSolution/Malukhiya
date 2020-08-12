package com.alpha.malukhiah;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.ParseException;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Constant {
    public static final String PACKAGE_NAME = "com.test.test";
    public static final String DOCTOR = "1";
    public static final String APPOINTMENT_All = "0";
    public static final String APPOINTMENT_COMPLETE = "1";
    public static final String APPOINTMENT_OPEN = "2";
    public static final String APPOINTMENT_CANCEL = "3";
    public static final String APPOINTMENT_CLOSE = "4";
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    // Constructor
    @SuppressLint("CommitPrefEdits")
    public Constant(Context context) {
        pref = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
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
        // return (!TextUtils.isEmpty || Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }



    public static String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
//        String outputPattern = "dd-MMM-yyyy h:mm a";
        String outputPattern = "EEE dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getSelectLanguage() { return pref.getString("SelectLanguage", "0"); }
    public void setSelectLanguage(String SelectLanguage) {
        editor.putString("SelectLanguage", SelectLanguage);
        editor.commit();
    }


    public String getIsLogin() { return pref.getString("isLogin", "0"); }
    public void setIsLogin(String isLogin) {
        editor.putString("isLogin", isLogin);
        editor.commit();
    }

    public String getUserID() { return pref.getString("UserID", "0"); }
    public void setUserID(String UserID) {
        editor.putString("UserID", UserID);
        editor.commit();
    }

    public String getFname() { return pref.getString("Fname", "0"); }
    public void setFname(String Fname) {
        editor.putString("Fname", Fname);
        editor.commit();
    }
    public String getLname() { return pref.getString("Lname", "0"); }
    public void setLname(String Lname) {
        editor.putString("Lname", Lname);
        editor.commit();
    }
    public String getEmail() { return pref.getString("Email", "0"); }
    public void setEmail(String Email) {
        editor.putString("Email", Email);
        editor.commit();
    }
    public String getMobile() { return pref.getString("Mobile", "0"); }
    public void setMobile(String Mobile) {
        editor.putString("Mobile", Mobile);
        editor.commit();
    }
    public String getImage() { return pref.getString("Image", "0"); }
    public void setImage(String Image) {
        editor.putString("Image", Image);
        editor.commit();
    }

    public String getState() { return pref.getString("State", "0"); }
    public void setState(String State) {
        editor.putString("State", State);
        editor.commit();
    }

    public String getCity() { return pref.getString("City", "0"); }
    public void setCity(String City) {
        editor.putString("City", City);
        editor.commit();
    }
    public String getAddress() { return pref.getString("Address", "0"); }
    public void setAddress(String Address) {
        editor.putString("Address", Address);
        editor.commit();
    }
    public String getAge() { return pref.getString("Age", "0"); }
    public void setAge(String Age) {
        editor.putString("Age", Age);
        editor.commit();
    }

    public String getGender() { return pref.getString("Gender", "0"); }
    public void setGender(String Gender) {
        editor.putString("Gender", Gender);
        editor.commit();
    }
}