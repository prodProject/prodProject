/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CloudSql;

import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.QueryReader;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
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
        
        for (Map.Entry<String, String> data:updatedconfig.entrySet())
        {
            insertQuery.addCustomColumn(data.getKey(), data.getValue());
        }
        return insertQuery.toString().concat(";");
    }
    
}
