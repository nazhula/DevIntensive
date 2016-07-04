package com.softdesign.devintensive.data.managers;


public class DataManager {
    private static DataManager INSTANCE = null;

    private PreferencesManager mPpreferencesManager;

    public DataManager() {
     this.mPpreferencesManager = new PreferencesManager();

    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();

        }
        return  INSTANCE;

    }

    public PreferencesManager getPreferencesManager() {
        return mPpreferencesManager;
    }


}
