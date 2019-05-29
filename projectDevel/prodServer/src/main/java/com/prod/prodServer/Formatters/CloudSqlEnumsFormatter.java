/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Formatters;

import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Interfaces.IFormatter;

/**
 *
 * @author shubham
 */
public class CloudSqlEnumsFormatter implements IFormatter<String, CloudSQLTableEnum> {

    @Override
    public String formatData(CloudSQLTableEnum data) {
        switch (data) {
            case UNKNOWN_TABLE:
                return "";
            case WORKER_TABLE:
                return "worker_prod";
            default:
                return "";
        }
    }

}
