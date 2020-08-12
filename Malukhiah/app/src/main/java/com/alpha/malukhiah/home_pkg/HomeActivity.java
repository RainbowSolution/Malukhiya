package com.alpha.malukhiah.home_pkg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.alpha.malukhiah.About_App_Activity;
import com.alpha.malukhiah.BuildConfig;
import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.ContactUs_Activity;
import com.alpha.malukhiah.MyPurchase_pkg.MyPurchase_Adv_Activity;
import com.alpha.malukhiah.Priavacy_Activity;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.TermOfUse_Activity;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.authenticationModule.Login_Activity;
import com.alpha.malukhiah.authenticationModule.Splash_Activity;
import com.alpha.malukhiah.category_pkg.Category_Activity;
import com.alpha.malukhiah.chat_pkg.Chat_Activity;
import com.alpha.malukhiah.membership_pkg.Membership_Activity;
import com.alpha.malukhiah.model.NotificaitonCountPkg.NotificationCount;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.profile_pkg.MyProfile_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    NavigationExpandableListAdapter navigationExpandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    AppCompatImageView ivLogoHomeId;
    DrawerLayout drawer;
    NavigationView navigationView;
    Context con;
    ImageView closs_img;
    AppCompatTextView ivNofificationcoutId;
    Constant constant;
    LinearLayout ll_home,ll_chat,ll_add_post,ll_profile,ll_notice;
     String userid;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        con = getApplicationContext();
        constant = new Constant(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivLogoHomeId = findViewById(R.id.id_drawerMenu);
        ivNofificationcoutId = findViewById(R.id.ivNofificationcoutId);
        userid = AppSession.getStringPreferences(HomeActivity.this, Constants.USER_ID);

//        ================
        ll_home = findViewById(R.id.ll_home);
        ll_chat = findViewById(R.id.ll_chat);
        ll_add_post = findViewById(R.id.ll_add_post);
        ll_profile = findViewById(R.id.ll_profile);
        ll_notice = findViewById(R.id.ll_notice);

        ll_home.setOnClickListener(this);
        ll_chat.setOnClickListener(this);
        ll_add_post.setOnClickListener(this);
        ll_profile.setOnClickListener(this);
        ll_notice.setOnClickListener(this);
//        ===================
        expandableListView = findViewById(R.id.expandableListView);

        prepareMenuData();
        populateExpandableList();
        replaceFragement(new HomeFragment());
        NotificationCount();
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        closs_img = navigationView.getHeaderView(0).findViewById(R.id.closs_img);
        ivLogoHomeId.setOnClickListener(this);
        closs_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(Gravity.LEFT);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    private void replaceFragement(HomeFragment homeFragment) {
        FragmentTransaction home = getSupportFragmentManager().beginTransaction();
        home.replace(R.id.flHomeId, homeFragment);
        home.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_drawerMenu:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(Gravity.LEFT);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;

            case R.id.ll_home:
                break;

            case R.id.ll_chat:
                startActivity(new Intent(HomeActivity.this, Chat_Activity.class));
                break;

            case R.id.ll_add_post:
             startActivity(new Intent(HomeActivity.this,PostActivity.class));
                break;

            case R.id.ll_notice:
                startActivity(new Intent(HomeActivity.this, Notification_Activity.class));
                break;

            case R.id.ll_profile:
                startActivity(new Intent(HomeActivity.this, MyProfile_Activity.class));
                break;
        }
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel(getResources().getString(R.string.home), true, true, R.drawable.ic_home_white); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.category), true, true, R.drawable.ic_category); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.myprofile), true, true, R.drawable.ic_myprofile); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.membership), true, true, R.drawable.ic_membership); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.invitefrind), true, true, R.drawable.ic_invite_friends); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.shareMyProfile), true, true, R.drawable.ic_share); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.abouttheapp), true, true, R.drawable.ic_about_app); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.privacypolicy), true, true, R.drawable.ic_privacy_policy); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.termofuse), true, true, R.drawable.ic_termofuser); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.contactus), true, true, R.drawable.ic_contact_us); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel(getResources().getString(R.string.logout), true, true, R.drawable.ic_logout); //Menu of Java Tutorials
        headerList.add(menuModel);
    }

    private void populateExpandableList() {
        navigationExpandableListAdapter = new NavigationExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(navigationExpandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (groupPosition == 0) {
                        drawer.closeDrawer(Gravity.LEFT);  //home
                    } else if (groupPosition == 1) {
                        startActivity(new Intent(HomeActivity.this, Category_Activity.class)); //category
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 2) {
                        startActivity(new Intent(HomeActivity.this, MyProfile_Activity.class)); //myprofile
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 3) {
                        startActivity(new Intent(HomeActivity.this, Membership_Activity.class));
                        drawer.closeDrawer(Gravity.LEFT);  // membership
                    } else if (groupPosition == 4) {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                            String shareMessage = "\nLet me recommend you this application\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch (Exception e) {
                        }
                        drawer.closeDrawer(Gravity.LEFT); //invite friend
                    } else if (groupPosition == 5) {
                        drawer.closeDrawer(Gravity.LEFT);  //share my profile
                    } else if (groupPosition == 6) {
                        drawer.closeDrawer(Gravity.LEFT);
                        startActivity(new Intent(HomeActivity.this, About_App_Activity.class));//about app
                    } else if (groupPosition == 7) {
                        startActivity(new Intent(HomeActivity.this, Priavacy_Activity.class)); // privacy
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 8) {
                        startActivity(new Intent(HomeActivity.this, TermOfUse_Activity.class)); // term condition
                    drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 9) {
                        startActivity(new Intent(HomeActivity.this, ContactUs_Activity.class)); // contact us
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 10) {
                        //log_out(); // logout
                        AppSession.setStringPreferences(HomeActivity.this, Constants.STATUS, "");
                        startActivity(new Intent(HomeActivity.this, Splash_Activity.class)); // contact us
                        finishAffinity();
                        drawer.closeDrawer(Gravity.LEFT);
                }
                }
                return false;
            }
        });
    }

//    public void log_out() {
//        final Dialog dialog = new Dialog(HomeActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_log_out);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        ImageView ivlogoutClose = dialog.findViewById(R.id.ivlogoutCloseId);
//        AppCompatImageView rllogout = dialog.findViewById(R.id.ivlogoutCloseId);
//        Button btn_logout = dialog.findViewById(R.id.btn_logout);
//
//        ivlogoutClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//
//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, Login_Activity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//                finishAffinity();
//            }
//        });
//
//        rllogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//        dialog.show();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void NotificationCount() {
       RetrofitClient.getClient().notificaoticout(userid).enqueue(new Callback<NotificationCount>() {
            @Override
            public void onResponse(Call<NotificationCount> call, Response<NotificationCount> response) {
                if (response.isSuccessful()) {
                    NotificationCount getLoginModle = response.body();
                    if (getLoginModle.getStatus() == true) {
                        ivNofificationcoutId.setText(String.valueOf(getLoginModle.getCount()));
                    }
                }else {
                    if (response.code() == 400) {
                        if(!response.isSuccessful()) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationCount> call, Throwable t) {
                Log.d("test", String.valueOf(t));

            }
        });
    }

}
