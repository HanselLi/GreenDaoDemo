package com.hansel.greendao;

import android.util.Log;

import com.hansel.greendao.entity.User;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoManager {

    private DaoSession daoSession;

    private GreenDaoManager() {
        daoSession = GreenApplication.getApp().getDaoSession();
    }

    public static GreenDaoManager getInstance() {
        return singletonHolder.mInstance;
    }

    public List<User> query() {

        Query<User> build = daoSession.getUserDao().queryBuilder()
                .limit(3)// 符合条件的结果中限制个数
                .offset(1)// 和limit结合使用，从第一个（不包含）后算返回的结果
//                .orderAsc(UserDao.Properties.Age)
                .where(UserDao.Properties.Sex.eq(1))
                .distinct().build();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return build.list();
    }
    /**
     * 多线程调用
     */
    public void queryInOtherThread(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                //内部build后设置了forCurrentThread，parameters are reset to their initial values specified
                Query<User> query = daoSession.getUserDao().queryBuilder()
                        .where(UserDao.Properties.Name.eq("LiLei"))
                        .build();
                List<User> list = query.list();
                Log.d("liyang"," list size : " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    Log.d("liyang"," other thread name " + list.get(i).getId());
                }
            }
        }.start();
    }

    private static class singletonHolder {
        private static final GreenDaoManager mInstance = new GreenDaoManager();
    }

    public long insert(User user) {
        return daoSession.getUserDao().insert(user);
    }
}
