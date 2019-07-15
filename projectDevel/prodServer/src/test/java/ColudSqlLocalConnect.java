
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shubham
 */
public class ColudSqlLocalConnect {


    public static void main(String[] args) throws SQLNonTransientConnectionException,
            IOException, SQLException, ClassNotFoundException {

        String instanceConnectionName = "prod-project-239707:us-east1:prodprojectinstance";
        String databaseName = "prodprojectdb";
        String IP_of_instance = "34.73.182.98";
        String username = "prodproject";
        String password = "affrim@123";
        String jdbcUrl = String.format(
                "jdbc:mysql://%s/%s?cloudSqlInstance=%s"
                + "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                IP_of_instance,
                databaseName,
                instanceConnectionName);

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(String.format("jdbc:mysql://%s/%s", IP_of_instance,databaseName));
//        config.setUsername(username);
//        config.setPassword(password);
//        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
//        config.addDataSourceProperty("cloudSqlInstance", instanceConnectionName);
//        config.addDataSourceProperty("useSSL", "false");
//        config.setMaximumPoolSize(5);
//        config.setMinimumIdle(5);
//        config.setConnectionTimeout(10000); // 10 seconds
//        config.setIdleTimeout(600000); // 10 minutes
//        config.setMaxLifetime(1800000); // 30 minutes
//        DataSource pool = new HikariDataSource(config);
//        Connection con = pool.getConnection();
//
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
