package com.hansel.greendao;

import android.app.Application;

import com.hansel.greendao.ktfile.Constants;


/**
 *
 */
public class GreenApplication extends Application {
    private static GreenApplication mApp;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        DBHelper openHelper = new DBHelper(this, Constants.DB_NAME);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }





    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static GreenApplication getApp() {
        return mApp;
    }

    /**
     * release greenDao
     */
    public void release() {
        daoSession.getAllDaos().clear();
        daoSession.getUserDao().detachAll();
        daoSession.clear();
        daoSession = null;
    }
}