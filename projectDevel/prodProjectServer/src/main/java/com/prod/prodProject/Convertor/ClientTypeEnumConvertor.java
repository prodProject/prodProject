/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodProject.Convertor;

import com.prod.prodProject.Enums.ClientTypeEnum;
import com.prod.prodProject.Interfaces.IFormatter;

/**
 *
 * @author shubham
 */
public class ClientTypeEnumConvertor implements IFormatter<ClientTypeEnum, String> {

    @Override
    public ClientTypeEnum getFormattedData(String data) {
        switch (data.trim()) {
            case "UNKNOWN":
                return ClientTypeEnum.UNKNOW_CLIENT;
            case "WORKER_CLIENT":
                return ClientTypeEnum.WORKER_CLIENT;
            case "CONSUMER_CLIENT":
                return ClientTypeEnum.CONSUMER_CLIENT;

        }
        return ClientTypeEnum.UNKNOW_CLIENT;
    }

}
