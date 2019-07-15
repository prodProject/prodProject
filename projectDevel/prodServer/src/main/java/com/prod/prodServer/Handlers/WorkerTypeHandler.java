/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Handlers;

import com.prod.prodServer.CloudSql.CloudSqlQueryExecutor;
import com.prod.prodServer.QueryBuilders.WorkerTypeQueryBuilder;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkerTypeHandler {

    private WorkerTypeQueryBuilder m_queryBuilder;

    public WorkerTypeHandler(WorkerTypeQueryBuilder queryBuilder) {
        m_queryBuilder = queryBuilder;
    }

    public JSONObject getAllWorkerType() throws Exception {
        String query = m_queryBuilder.getAllWorkerTypeQuery();
        System.out.println(query);
        JSONObject response = CloudSqlQueryExecutor.selectFromTable(query);
        return response;
    }

}
