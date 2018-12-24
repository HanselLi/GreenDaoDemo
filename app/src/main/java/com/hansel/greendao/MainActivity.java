package com.hansel.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansel.greendao.entity.Address;
import com.hansel.greendao.entity.GenderType;
import com.hansel.greendao.entity.User;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private UserDao userDao;
    private List<User> mData;
    private ArrayList<Address> mAddressData;
    private AddressDao mAddressDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDao = GreenApplication.getApp().getDaoSession().getUserDao();
        mAddressDao = GreenApplication.getApp().getDaoSession().getAddressDao();
        mAddressData = new ArrayList<>();
        initViews();
    }

    private void insertUserData() {
        initUserTestData();
        for (int i = 0; i < mData.size(); i++) {
            userDao.insert(mData.get(i));
            LogUtil.INSTANCE.d(" insert result " + mData.get(i).getId());
        }
        /** User的Id在添加数据库后GreenDao会自增User的Id,若不重新初始化数据
         * 再次insert的User的id是上次插入库中的id，导致ndroid.database.sqlite.SQLiteConstraintException:
         * UNIQUE constraint failed: UserTable._id (code 1555)
         * 自增操作见{@link org.greenrobot.greendao.AbstractDao#updateKeyAfterInsert}
         */
        mData.clear();
    }

    private void initUserTestData() {
        mData = new ArrayList<>();
        mData.add(new User("Hansel", 22, GenderType.MALE));
        mData.add(new User("LiLy", 23, GenderType.FEMALE));
        mData.add(new User("LiLei", 21, GenderType.MALE));
        mData.add(new User("韩梅梅", 25, GenderType.FEMALE));
        mData.add(new User("李磊", 20, GenderType.MALE));
        mData.add(new User("韩赛尔", 27, GenderType.MALE));
    }


    private void initViews() {
        Button insertBtn = (Button) findViewById(R.id.insert);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserData();
            }
        });

        Button queryBtn = findViewById(R.id.query);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //==============查询然后插入地址表===============
                List<User> userList = userDao.queryBuilder()
                        .list();
                mAddressData.clear();
                for (int i = 0; i < userList.size(); i++) {
                    // insert data to Address Table ...
                    Long id = userList.get(i).getId();
                    mAddressData.add(new Address(id, String.format("%s道口", i)));
                }

                for (int i = 0; i < mAddressData.size(); i++) {
                    LogUtil.INSTANCE.d("AddressEntity _id : " + mAddressData.get(i).getId());
                    mAddressDao.insert(mAddressData.get(i));
                    LogUtil.INSTANCE.d("After insert AddressEntity _id : " + mAddressData.get(i).getId());
                }
                updateText();
            }
        });

    }


    private void updateText() {
        StringBuilder sb = new StringBuilder();
        List<User> userList = GreenDaoManager.getInstance().query();
        ((TextView) findViewById(R.id.showResult)).setText("");
        for (int i = 0; i < userList.size(); i++) {
            LogUtil.INSTANCE.d(" return : " + userList.get(i).getName());
            sb.append(userList.get(i).getName() + " : ");
        }
        ((TextView) findViewById(R.id.showResult)).setText(sb);
//        GreenDaoManager.getInstance().queryInOtherThread();
        // 查询地址是五道口的username and age
        QueryBuilder.LOG_SQL = true;
        QueryBuilder<User> userQueryBuilder = userDao.queryBuilder();
        userQueryBuilder.join(Address.class, AddressDao.Properties.UserId)
                .where(AddressDao.Properties.Address.eq("1道口"));
        List<User> users = userQueryBuilder.list();
        for (int i = 0; i < users.size(); i++) {
            LogUtil.INSTANCE.d(" user who live in wudaokou : " + users.get(i).toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GreenApplication.getApp().release();
    }
}
