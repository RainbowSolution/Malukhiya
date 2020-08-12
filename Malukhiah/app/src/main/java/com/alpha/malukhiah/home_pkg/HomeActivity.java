package com.alpha.malukhiah.home_pkg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.R;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvSelectorADoctor;
    NavigationExpandableListAdapter navigationExpandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    AppCompatImageView ivLogoHomeId, ivserachHomeId, ivnoticationHomeId;
    DrawerLayout drawer;
    NavigationView navigationView;
    private AppCompatTextView tvUserName, tvEmail, ivNofificationcoutId, tvhomeId;
    private String Type_id, username, useremail, userimage, patient_id;
    private RelativeLayout rlallProfileId;
    private String language;
    Context con;
    String login;
    ImageView closs_img;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    Constant constant;
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
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        replaceFragement(new HomeFragment());
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        closs_img = navigationView.getHeaderView(0).findViewById(R.id.closs_img);
//        AppCompatTextView tv_username = navigationView.getHeaderView(0).findViewById(R.id.tv_username);
//        CircleImageView user_imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        ivLogoHomeId.setOnClickListener(this);
//        String userImage = constant.getImage();
//        if (userImage.equals("0")) {
//            user_imageView.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
//        } else {
//            Glide.with(getApplicationContext())
//                    .load(Constant.DOCTOR_IMG_URL + userImage)
//                    .into(user_imageView);
//        }
//
//        tv_username.setText(constant.getFname() + " " + constant.getLname());

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
                        drawer.closeDrawer(Gravity.LEFT);

                    } else if (groupPosition == 1) {
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 2) {
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 3) {
                        drawer.closeDrawer(Gravity.LEFT);
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                    } else if (groupPosition == 4) {
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 5) {
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 6) {
//                        language_change();
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 7) {
//                        log_out();
                        drawer.closeDrawer(Gravity.LEFT);
                    }
                }
                return false;
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
