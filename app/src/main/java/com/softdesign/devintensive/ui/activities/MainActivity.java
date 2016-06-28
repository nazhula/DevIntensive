package com.softdesign.devintensive.ui.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.ui.utils.ConstantManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolBar;
    private ImageView mUserAvatar;
    private DrawerLayout mNavigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mUserAvatar = (ImageView) findViewById(R.id.user_avatar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);

        setupToolbar();
        //setupDrawer();

        if (savedInstanceState == null) {
            //активити запускается впервые
        } else {
            //активити уже создавалось
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    private void setupToolbar() {
        setSupportActionBar(mToolBar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //по кнопке back закрываем Navigation Drawer
    @Override
    public void onBackPressed() {
        if (mNavigationDrawer.isDrawerOpen(Gravity.LEFT)) {
            mNavigationDrawer.closeDrawer(GravityCompat.START);
            return;
        } else {
            super.onBackPressed();
        }
    }

}



