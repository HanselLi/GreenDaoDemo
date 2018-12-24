package com.hansel.greendao.entity;

import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.hansel.greendao.DaoSession;
import com.hansel.greendao.UserDao;
import com.hansel.greendao.AddressDao;

@Entity(nameInDb = "addressTable")
public class Address {
    //FIXME: 此处有个问题，按照官方文档，自增的主键可以为long或者Long类型，
    /**
     * Note that we did not pass an id when creating the note. In this case the database decides on the note id.
     * The DAO takes care of automatically setting the new id before returning from insert (see the log statement).
     * 实际上并非如此，long类型的id默认值为0，指定了id为0，插入后rowId仍为0（首个插入），再次插入则会报unique_id唯一约束异常
     * (long类型的id做主键创建的sql为INTEGER PRIMARY KEY NOT NULL非空主键)
     * 但是默认null的Long类型Id则没问题，{@linkplain AddressDao#bindValues(SQLiteStatement, Address)}判断只有非空的id才会
     * 绑定到sql语句中，然后执行{@linkplain SQLiteStatement#executeInsert()},内部会有对null字段的处理，即id会自增写入表中
     */

    @Id
    private Long id;

    @Property(nameInDb = "userId")
    private long userId;
    // ToOne关系型数据库表示
    @NotNull
    private String address;
    @ToOne(joinProperty = "userId")
    private User user;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1580986028)
    private transient AddressDao myDao;

    @Generated(hash = 1339916026)
    public Address(Long id, long userId, @NotNull String address) {
        this.id = id;
        this.userId = userId;
        this.address = address;
    }

    public Address(long userId, @NotNull String address) {
        this.userId = userId;
        this.address = address;
    }

    @Generated(hash = 388317431)
    public Address() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Generated(hash = 251390918)
    private transient Long user__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 115391908)
    public User getUser() {
        long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
                user__resolvedKey = __key;
            }
        }
        return user;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 462495677)
    public void setUser(@NotNull User user) {
        if (user == null) {
            throw new DaoException(
                    "To-one property 'userId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            userId = user.getId();
            user__resolvedKey = userId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 543375780)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAddressDao() : null;
    }


}
