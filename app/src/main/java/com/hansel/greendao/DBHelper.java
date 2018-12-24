package com.hansel.greendao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
/**
 * 自定义的DataBaseHelper管理数据库升级等
 */
public class DBHelper extends DaoMaster.OpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }


    //升级更改字段
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        //update database
        LogUtil.INSTANCE.d(" oldVersion : " + oldVersion + " newVersion : " + newVersion);
        if (oldVersion <= 2){
            db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD " + "height " + " TEXT NOT NULL;");
        }
    }
}
