/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CloudSql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @see Connect from Cloud
 * @author shubham
 */
public class CloudSqlConnector {

    private final String CLOUD_SQL_CONNECTION_NAME = "prod-project-239707:us-east1:prodprojectinstance";
    private final String DB_USER = "prodproject";
    private final String DB_PASS = "affrim@123";
    private final String DB_NAME = "prodprojectdb";

    public Connection createPoolConnrection() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);
        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
        config.addDataSourceProperty("useSSL", "false");
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(10000); // 10 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        DataSource pool = new HikariDataSource(config);
        return pool.getConnection();
    }
}
