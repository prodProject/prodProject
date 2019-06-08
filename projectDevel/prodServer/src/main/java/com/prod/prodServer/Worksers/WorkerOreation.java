/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Worksers;

import javax.inject.Inject;
import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.CloudSql.CloudSqlQueryExecutor;
import com.prod.prodServer.CommonCode.JSONConvertor;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Helpers.WorkerOpreationHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.httprpc.sql.ResultSetAdapter;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkerOreation {

    private CloudSqlQueryBuilder m_querybuilder;
    private WorkerOpreationHelper m_helper;

    private ResultSet response = null;

    @Inject
    public WorkerOreation(CloudSqlQueryBuilder querybuilder, WorkerOpreationHelper helper) {
        m_querybuilder = querybuilder;
        m_helper = helper;
    }

    public JSONObject insertWorker(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> workerInformation) throws SQLException {
        Map<String, String> updatedconfig = m_helper.addWorkerConfigWhileCreated(workerInformation);
        String insertQuery = m_querybuilder.insertQuery(cloudSQLTableEnum.WORKER_TABLE, updatedconfig);
        System.err.println("" + insertQuery);
        boolean value = CloudSqlQueryExecutor.insertIntoTable(insertQuery);
        System.out.println("Query executed result" + value);
        if (value) {
            return m_helper.returnInsertJsonSucess();
        } else {
            return m_helper.returnInsertJsonFailed();
        }
    }

    public JSONObject getWorker(CloudSQLTableEnum cloudSQLTableEnum, String info) throws SQLException, Exception {
        JSONObject result = null;
        if (info.isEmpty()) {
            return m_helper.returnJsonFailed();
        }
        String query = m_querybuilder.getWorkerQuery(cloudSQLTableEnum.WORKER_TABLE, info);
        System.err.println("" + query);
        result = CloudSqlQueryExecutor.selectFromTable(query);
        if ((result != null)) {
            return result;
        } else {
            return m_helper.returnJsonFailed();
        }
    }

    public JSONObject getAndUpdateWorker(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> workerInformation) throws SQLException {
        //Map<String, String> updatedconfig = m_helper.addWorkerConfigWhileCreated(workerInformation);
        String updateQuery = m_querybuilder.updateWorkerQuery(cloudSQLTableEnum.WORKER_TABLE, workerInformation);
        System.err.println("" + updateQuery);
        boolean value = CloudSqlQueryExecutor.updateIntoTable(updateQuery);
        System.out.println("Query executed result" + value);
        if (value) {
            return m_helper.returnUpdateJsonSucess();
        } else {
            return m_helper.returnUpdateJsonFailed();
        }
    }

}
