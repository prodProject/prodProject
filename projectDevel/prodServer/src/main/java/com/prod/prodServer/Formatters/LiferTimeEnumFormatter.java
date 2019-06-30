/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Formatters;

import com.prod.prodServer.Enums.LifeTimeEnum;
import com.prod.prodServer.Interfaces.IFormatter;

/**
 *
 * @author shubham
 */
public class LiferTimeEnumFormatter implements IFormatter<LifeTimeEnum, String> {

    @Override
    public LifeTimeEnum formatData(String data) {
        switch (data) {
            case "UNKNOWN":
                return LifeTimeEnum.UNKNOWN;
            case "ACTIVE":
                return LifeTimeEnum.ACTIVE;
            case "DEACTIVE":
                return LifeTimeEnum.DEACTIVE;
            case "DELETED":
                return LifeTimeEnum.DELETED;
            case "NOT_VERIFIED":
                return LifeTimeEnum.NOT_VERIFIED;
            case "SUSPENDED":
                return LifeTimeEnum.SUSPENDED;
            case "VEFFIED_VIA_OTP":
                return LifeTimeEnum.VEFFIED_VIA_OTP;
            case "VERFIED_VIA_EMAIL":
                return LifeTimeEnum.VERFIED_VIA_EMAIL;
            case "VERIFIED":
                return LifeTimeEnum.VERIFIED;
            default:
                return LifeTimeEnum.UNKNOWN;

        }
    }

}
