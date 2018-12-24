package com.hansel.greendao.entity;
/**
 * 我们可以自定义一个converter,实体类存入的是枚举的类型
 * 数据库中存转化过的类型
 */
public enum GenderType {
    MALE(1),
    FEMALE(0),
    UNKNOWN(-1);
    int gender;

    GenderType(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

}
