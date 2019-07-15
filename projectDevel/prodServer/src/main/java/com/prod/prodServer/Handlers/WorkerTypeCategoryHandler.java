/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Handlers;

import com.prod.prodServer.CloudSql.CloudSqlQueryExecutor;
import com.prod.prodServer.Enums.WorkerTypeConfigEnum;
import com.prod.prodServer.Helpers.WorkerTypeConfigHelper;
import com.prod.prodServer.QueryBuilders.WorkerTypeQueryBuilder;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkerTypeCategoryHandler {

    private WorkerTypeQueryBuilder m_queryBuilder;
    private WorkerTypeConfigHelper m_helper;

    public WorkerTypeCategoryHandler(WorkerTypeQueryBuilder queryBuilder, WorkerTypeConfigHelper helper) {
        m_queryBuilder = queryBuilder;
        m_helper = helper;
    }

    public JSONObject getWorkersCategory(ArrayList<WorkerTypeConfigEnum> typeConfig) throws Exception {
        String query = m_queryBuilder.getWorkerCategoryViaWorkerType(typeConfig);
        System.out.println(query);
        JSONObject result = CloudSqlQueryExecutor.selectFromTable(query);
        System.out.println(result);
        return m_helper.getFormattedResponse(result);
    }
}
