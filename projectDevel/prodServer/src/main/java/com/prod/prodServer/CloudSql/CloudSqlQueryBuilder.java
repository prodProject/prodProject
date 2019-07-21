/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CloudSql;

import com.healthmarketscience.sqlbuilder.CreateTableQuery;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.UpdateQuery;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
import com.prod.prodServer.SQLQuery.SqlMaker;
import java.util.Map;

/**
 *
 * @author shubham
 */
public class CloudSqlQueryBuilder {

    private CloudSqlEnumsFormatter m_formetter;

    public CloudSqlQueryBuilder(CloudSqlEnumsFormatter formetter) {
        m_formetter = formetter;
    }


    public String insertQuery(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> updatedconfig) {
        String Query = "";
        InsertQuery insertQuery = new InsertQuery(m_formetter.formatData(cloudSQLTableEnum));

        for (Map.Entry<String, String> data : updatedconfig.entrySet()) {
            insertQuery.addCustomColumn(data.getKey(), data.getValue());
        }
        return insertQuery.toString().concat(";");
    }
    
    public String insertQueryWorKerConfig(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> updatedconfig) {
        String Query = "";
        InsertQuery insertQuery = new InsertQuery(cloudSQLTableEnum.name());

        for (Map.Entry<String, String> data : updatedconfig.entrySet()) {
            insertQuery.addCustomColumn(data.getKey(), data.getValue());
        }
        return insertQuery.toString().concat(";");
    }

    public String getWorkerQuery(CloudSQLTableEnum cloudSQLTableEnum, String info) {
        String Query = "";
        SelectQuery selectQuery = new SelectQuery();
        //selectQuery.addAllColumns();
        //selectQuery.
        selectQuery.addCustomFromTable(m_formetter.formatData(cloudSQLTableEnum));
        selectQuery.getWhereClause().addCustomCondition("worker_uid = " + "'" + info + "'");
        return selectQuery.toString().concat("LIMIT 1 ;");
    }

    public String updateWorkerQuery(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> updatedconfig) {
        String Query = "";
        String uid = "";
        UpdateQuery updateQuery = new UpdateQuery(m_formetter.formatData(cloudSQLTableEnum));
        for (Map.Entry<String, String> data : updatedconfig.entrySet()) {
            if (data.getKey() == "worker_uid") {
                uid = data.getValue();
            } else {
                updateQuery.addCustomSetClause(data.getKey(), data.getValue());
            }
        }
        updateQuery.getWhereClause().addCustomCondition("worker_uid = " + "'" + uid + "'");
        return updateQuery.toString().concat(";");

    }
    
    public String createTableQuery(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> updatedconfig) {
        
        String Query = "";
        
        CreateTableQuery createTableQuery = new CreateTableQuery(cloudSQLTableEnum.name());
        for (Map.Entry<String, String> entry : updatedconfig.entrySet()) {
            
            
        }
        return createTableQuery.toString().concat(";");

    }

    public String getWorkerUser() {
        //String
        return "";
    }

    public String getWorkerUser(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> map) {
        String Query = "";
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllColumns();
        //selectQuery.
        selectQuery.addCustomFromTable(m_formetter.formatData(cloudSQLTableEnum));
        for(Map.Entry<String, String> data:map.entrySet())
        {
             selectQuery.getWhereClause().addCustomCondition(data.getKey()+" = " + "'" + data.getValue()+ "'");
        }
       
        return selectQuery.toString().concat("LIMIT 1 ;");
    }

    public String insertQueryWorkerType(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> map) {
        SqlMaker query = SqlMaker.builder().INSERTINTO(cloudSQLTableEnum,map);
        return query.build();
    }
}
