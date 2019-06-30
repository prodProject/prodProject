/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Services;

import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
import com.prod.prodServer.Helpers.WorkerOpreationHelper;
import com.prod.prodServer.Interfaces.IService;
import com.prod.prodServer.Worksers.WorkerOreation;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkersService implements IService {

    @Override
    public JSONObject getGetService(String info) {
        JSONObject response = null;
        WorkerOreation opreation = new WorkerOreation(new CloudSqlQueryBuilder(new CloudSqlEnumsFormatter()), new WorkerOpreationHelper());
        try {
            response = opreation.getWorker(CloudSQLTableEnum.WORKER_TABLE, info);
        } catch (SQLException ex) {
            Logger.getLogger(WorkersService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(WorkersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public JSONObject getPostService(Map<String, String> workerInformation) {
        JSONObject response = null;
        WorkerOreation opreation = new WorkerOreation(new CloudSqlQueryBuilder(new CloudSqlEnumsFormatter()), new WorkerOpreationHelper());
        try {
            response = opreation.insertWorker(CloudSQLTableEnum.WORKER_TABLE, workerInformation);
        } catch (SQLException ex) {
            Logger.getLogger(WorkersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public JSONObject getuserLogin(String emailorphone, String password) {
        JSONObject response = null;
        WorkerOreation opreation = new WorkerOreation(new CloudSqlQueryBuilder(new CloudSqlEnumsFormatter()), new WorkerOpreationHelper());
        response = opreation.userLoginWithCredentials(emailorphone,password);
        return null;
    }

}
