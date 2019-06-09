package com.prod.prodProject.Formatters;

import com.prod.prodProject.Enums.UserTypeEnum;
import com.prod.prodProject.Interfaces.IFormatter;

public class UserTypeEnumFormatter implements IFormatter<String> {
    @Override
    public UserTypeEnum getFormattedData(String data) {

        switch (data.trim()) {
            case "UNKNOWN":
                return UserTypeEnum.UNKNOWN;
            case "STUDENT":
                return UserTypeEnum.STUDENT;
            case "CLASS_REPRESENTATIV":
                return UserTypeEnum.CLASS_REPRESENTATIV;
            case "TEACHER":
                return UserTypeEnum.TEACHER;
            default:
                return UserTypeEnum.UNKNOWN;
        }
    }
}
