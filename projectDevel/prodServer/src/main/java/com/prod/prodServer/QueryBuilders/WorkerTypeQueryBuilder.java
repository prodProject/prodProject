/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.QueryBuilders;

import com.prod.prodServer.DatabaseSchema.WorkerTypeConfigSchema;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Enums.WorkerTypeConfigEnum;
import com.prod.prodServer.SQLQuery.SqlMaker;
import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author shubham
 */
public class WorkerTypeQueryBuilder {
    
    public String getAllWorkerTypeQuery() {
        SqlMaker workerType = SqlMaker.builder(WorkerTypeConfigSchema.getWorkerType());
        SqlMaker from = SqlMaker.builder(CloudSQLTableEnum.WORKER_TYPE_CONFIG.name());
        SqlMaker query = SqlMaker.builder().SELECT(workerType).FROM(from);
        return query.build();
    }
    
    public String getWorkerCategoryViaWorkerType(ArrayList<WorkerTypeConfigEnum> typeConfig) {
        SqlMaker all = SqlMaker.builder("*");
        SqlMaker from = SqlMaker.builder(CloudSQLTableEnum.WORKER_TYPE_CONFIG.name());
        SqlMaker where = SqlMaker.builder().OR(getListofCond(typeConfig));
        SqlMaker query = SqlMaker.builder().SELECT(all).FROM(from).WHERE(where);
        return query.build();
    }
    
    private Object[] getListofCond(ArrayList<WorkerTypeConfigEnum> typeConfig) {
        Object[] cond = new Object[typeConfig.size()];
        int i = 0;
        for (WorkerTypeConfigEnum condition : typeConfig) {
            SqlMaker exp = SqlMaker.builder().getEqualsTo(SqlMaker.builder("worker_type"), SqlMaker.builder(SqlMaker.builder().getQuotedValue(condition.name())));
            cond[i] = exp;
            i++;
        }
        return cond;
    }
    
}
