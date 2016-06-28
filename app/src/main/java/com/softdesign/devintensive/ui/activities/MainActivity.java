package com.softdesign.devintensive.ui.activities;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.data.managers.DataManager;
import com.softdesign.devintensive.ui.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

        private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";
        private int mCurrentEditMode = 0;

       private DataManager mDataManager;
       private Toast mToast;
        private CoordinatorLayout mCoordinatorLayout;
        private Toolbar mToolBar;
        private ImageView mUserAvatar;
        private DrawerLayout mNavigationDrawer;
        private FloatingActionButton mFab;
        private EditText mUserPhone, mUserMail, mUserVk, mUserGit, mUserBio;
        private List<EditText> mUserInfoViews;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Log.d(TAG, "onCreate");

            mDataManager = DataManager.getInstance();
            mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);
            mToolBar = (Toolbar) findViewById(R.id.toolbar);
            mUserAvatar = (ImageView) findViewById(R.id.user_avatar);
            mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
            mFab = (FloatingActionButton) findViewById(R.id.fab);

            mUserPhone = (EditText) findViewById(R.id.my_phone);
            mUserMail = (EditText) findViewById(R.id.my_mail);
            mUserVk = (EditText) findViewById(R.id.my_vk);
            mUserGit = (EditText) findViewById(R.id.my_git);
            mUserBio = (EditText) findViewById(R.id.my_bio);

            mUserInfoViews = new ArrayList<>();
            mUserInfoViews.add(mUserPhone);
            mUserInfoViews.add(mUserMail);
            mUserInfoViews.add(mUserVk);
            mUserInfoViews.add(mUserGit);
            mUserInfoViews.add(mUserBio);

            mFab.setOnClickListener(this);


            setupToolbar();
            //setupDrawer();
            loadUserInfoValue();

           // List<String> test = mDataManager.getPreferencesManager().loadUserProfileData();

            if (savedInstanceState == null) {
                //активити запускается впервые
            } else {
                mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY, 0);
                changeEditMode(mCurrentEditMode);
            }
        }

        //вызов Navigation Drawer по кнопке меню
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                mNavigationDrawer.openDrawer(GravityCompat.START);
            }
            return super.onOptionsItemSelected(item);
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
          switch (v.getId()) {
              case R.id.fab:
                  mToast = Toast.makeText(this, "Клик", Toast.LENGTH_SHORT);
                  mToast.show();
                  if (mCurrentEditMode == 0) {
                      changeEditMode(1);
                      mCurrentEditMode =1;
                  } else{
                      changeEditMode(0);
                      mCurrentEditMode = 0;
                  }

                  break;
          }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.d(TAG, "onSaveInstanceState");

            outState.putInt(ConstantManager.EDIT_MODE_KEY,mCurrentEditMode);
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

    private void changeEditMode(int mode){
       if (mode == 1) {
           mFab.setImageResource(R.drawable.ic_done_24dp);
           for (EditText userValue : mUserInfoViews) {
               userValue.setEnabled(true);
               userValue.setFocusable(true);
               userValue.setFocusableInTouchMode(true);
           }
       }  else {
               mFab.setImageResource(R.drawable.ic_create_24dp);
               for (EditText userValue : mUserInfoViews) {
                   userValue.setEnabled(false);
                   userValue.setFocusable(false);
                   userValue.setFocusableInTouchMode(false);
                   saveUserInfoValue();
               }
       }
    }

    private void loadUserInfoValue(){
        List<String> userData = mDataManager.getPreferencesManager().loadUserProfileData();
           for (int i = 0; i < userData.size(); i++){
               if(!userData.get(i).equals("null")) {
                  mUserInfoViews.get(i).setText(userData.get(i));
               }
           }
    }

    private void saveUserInfoValue() {
        List<String> userData = new ArrayList<>();
           for (EditText userFieldView : mUserInfoViews) {
                 userData.add(userFieldView.getText().toString());
                    }
           mDataManager.getPreferencesManager().saveUserProfileData(userData);
    }

    }



