/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Formatters;

import com.prod.prodServer.Enums.WorkerTypeConfigEnum;
import com.prod.prodServer.Interfaces.IFormatter;

/**
 *
 * @author shubham
 */
public class WorkerTypeEnumFormatter implements IFormatter<WorkerTypeConfigEnum, String> {

    @Override
    public WorkerTypeConfigEnum formatData(String data) {
        return WorkerTypeConfigEnum.valueOf(data);
    }

}
