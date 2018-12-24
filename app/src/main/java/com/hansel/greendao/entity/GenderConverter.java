package com.hansel.greendao.entity;
/***
 * convert Entity property type to
 * the database recognized type
 *
 */

import org.greenrobot.greendao.converter.PropertyConverter;

public class GenderConverter implements PropertyConverter<GenderType, Integer> {


    @Override
    public GenderType convertToEntityProperty(Integer databaseValue) {
        switch (databaseValue) {
            case 1:
                return GenderType.MALE;
            case 0:
                return GenderType.FEMALE;
            default:
                return GenderType.UNKNOWN;
        }
    }

    @Override
    public Integer convertToDatabaseValue(GenderType entityProperty) {
        return entityProperty.getGender();
    }
}
