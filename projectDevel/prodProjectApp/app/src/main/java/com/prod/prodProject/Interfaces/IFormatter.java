package com.prod.prodProject.Interfaces;


import com.prod.prodProject.Enums.UserTypeEnum;

public interface IFormatter<T> {

    public UserTypeEnum getFormattedData(T data);

}
