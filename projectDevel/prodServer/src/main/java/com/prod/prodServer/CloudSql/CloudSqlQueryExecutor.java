/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CloudSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        connection=null;
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

    public static ResultSet selectFromTable(String query) throws SQLException {
        if (connection == null) {
            ConnectCloudDatabase();
        }
        Statement stmt = connection.createStatement();
        ResultSet response = stmt.executeQuery(query);
        closeCloudConnection();
        return response;
    }

}
