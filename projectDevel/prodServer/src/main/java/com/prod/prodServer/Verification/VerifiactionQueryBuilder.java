/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Verification;

import com.healthmarketscience.sqlbuilder.InsertQuery;
import com.healthmarketscience.sqlbuilder.SelectQuery;
import com.healthmarketscience.sqlbuilder.UpdateQuery;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Enums.LifeTimeEnum;
import com.prod.prodServer.Enums.VerificationModeEnum;
import java.util.Map;

/**
 *
 * @author shubham
 */
public class VerifiactionQueryBuilder {

    public String insertQuery(CloudSQLTableEnum cloudSQLTableEnum, Map<String, String> updatedconfig) {
        String Query = "";
        InsertQuery insertQuery = new InsertQuery(cloudSQLTableEnum.OTP_EMAIL_VERIFICATION.name());

        for (Map.Entry<String, String> data : updatedconfig.entrySet()) {
            insertQuery.addCustomColumn(data.getKey(), data.getValue());
        }
        return insertQuery.toString().concat(";");
    }

    public String getVerificationQuery(CloudSQLTableEnum cloudSQLTableEnum, String uid, String token) {
        String Query = "";
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllColumns();
        selectQuery.addCustomFromTable(cloudSQLTableEnum.name());
        selectQuery.getWhereClause().addCustomConditions("uid = " + "'" + uid + "'", "token = " + "'" + token + "'"/*, "lifeTime = " + "'" + LifeTimeEnum.ACTIVE.name() + "'"*/);
        return selectQuery.toString().concat("LIMIT 1 ;");
    }
    
    public String getUSerInfo(CloudSQLTableEnum cloudSQLTableEnum, String uid) {
        String Query = "";
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addAllColumns();
        selectQuery.addCustomFromTable("worker_prod");
        selectQuery.getWhereClause().addCustomConditions("worker_uid = " + "'" + uid + "'");
        return selectQuery.toString().concat("LIMIT 1 ;");
    }

    

    public String userInfo(CloudSQLTableEnum cloudSQLTableEnum, String uid, Map<String, String> data) {
        String Query= "";
        UpdateQuery updateQuery = new UpdateQuery("worker_prod");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            updateQuery.addCustomSetClause(entry.getKey(), entry.getValue());
        }
        updateQuery.getWhereClause().addCustomConditions("worker_uid = " + "'" + uid + "'");
        return updateQuery.toString().concat(";");
    }
    

}
