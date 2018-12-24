package com.hansel.greendao.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * only Java classes are supported. If you prefer another language like Kotlin,
 * your entity classes must still be Java.
 */
@Entity(nameInDb = "UserTable" /*,indexes = {}*/)
public class User {

    /**
     * Marks field is the primary key of the entity's table
     * works only for Long/long fields()
     * @see Address 详细描述
     */
    @Id
    @Property(nameInDb = "userId")
    private Long id;
    @Property(nameInDb = "username")
    private String name;
    private int age;
    @Convert(converter = GenderConverter.class, columnType = int.class)
    private GenderType sex;
    //Transient fields are not persisted in the database.
    @Transient
    private String state;

    @Generated(hash = 49499506)
    public User(Long id, String name, int age, GenderType sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    // 这里自己添加一个构造器，方便构造实体类，不用传id
    public User(String name, int age, GenderType sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderType getSex() {
        return this.sex;
    }

    public void setSex(GenderType sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", state='" + state + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }
}
