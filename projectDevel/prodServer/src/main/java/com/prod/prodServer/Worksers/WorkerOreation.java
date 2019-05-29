/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Worksers;

import javax.inject.Inject;
import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.CloudSql.CloudSqlQueryExecutor;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Helpers.WorkerOpreationHelper;
import java.sql.SQLException;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkerOreation {

    private CloudSqlQueryBuilder m_querybuilder;
    private WorkerOpreationHelper m_helper;

    @Inject
    public WorkerOreation(CloudSqlQueryBuilder querybuilder, WorkerOpreationHelper helper) {
        m_querybuilder = querybuilder;
        m_helper = helper;
    }

    public void insertWorker() {

        // m_querybuilder.insertQuery(CloudSQLTableEnum.WORKER_TABLE, WorkersTableSchema.getWorkersSchema(),Map<String,String>);
    }

    public JSONObject insertWorker(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> workerInformation) throws SQLException {
        Map<String, String> updatedconfig = m_helper.addWorkerConfig(workerInformation);
        String insertQuery = m_querybuilder.insertQuery(cloudSQLTableEnum.WORKER_TABLE, updatedconfig);
        System.err.println("" + insertQuery);
        boolean value = CloudSqlQueryExecutor.insertIntoTable(insertQuery);
        System.out.println("Query executed result"+value);
        if (value) {
            return m_helper.returnInsertJsonSucess();
        } else {
            return m_helper.returnInsertJsonFailed();
        }
    }

}
