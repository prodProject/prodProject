
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;

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

    private static GoogleCredential apiCredentials = null;
    private static ArrayList<String> scopeList = Lists.newArrayList();

    public static void main(String[] args) throws SQLNonTransientConnectionException,
            IOException, SQLException, ClassNotFoundException {
        scopeList.add("https://www.googleapis.com/auth/cloud-platform");
        Connection con = DriverManager.getConnection(
                                        constructJdbcUrl("prodprojectdb", "prod-project-239707:us-east1:prodprojectinstance"),
                                        "prodproject",
                                        "affrim@123");
        

    }

    private static String constructJdbcUrl(String database, String connectionName) {
        return String.format(
                "jdbc:mysql://google/%s?socketFactory=com.google.cloud.sql.mysql.SocketFactory"
                + "&cloudSqlInstance=%s",
                database,
                connectionName);

    }

}
