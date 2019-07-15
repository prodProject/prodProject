/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CloudSql;

import com.prod.prodServer.CommonCode.JSONConvertor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class CloudSqlQueryExecutor {

    private static Connection connection;

    public static void ConnectCloudDatabase() throws SQLException {
        CloudSqlConnector connector = new CloudSqlConnector();
        connection = connector.createPoolConnrection();
    }

    public static void closeCloudConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    public static void createTable(String query) throws SQLException {
        if (connection == null) {
            ConnectCloudDatabase();
        }
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        closeCloudConnection();
    }

    public static boolean insertIntoTable(String query) throws SQLException {
        if (connection == null) {
            ConnectCloudDatabase();
        }
        Statement stmt = connection.createStatement();
        int result = stmt.executeUpdate(query);
        closeCloudConnection();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateIntoTable(String query) throws SQLException {
        if (connection == null) {
            ConnectCloudDatabase();
        }
        Statement stmt = connection.createStatement();
        int result = stmt.executeUpdate(query);
        closeCloudConnection();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static JSONObject selectFromTable(String query) throws SQLException, Exception {
        if (connection == null) {
            ConnectCloudDatabase();
        }
        Statement stmt = connection.createStatement();
        ResultSet response = stmt.executeQuery(query);
        System.out.println(response);
        if (response.next() == false) {
            System.out.println("number of rows are empty");
        } else {
            System.out.println("number of rows" + response.getMetaData().getColumnCount());
        }
        JSONObject resp  = JSONConvertor.convertResultSetIntoJSON(response);
        closeCloudConnection();
        return resp;
    }

}
