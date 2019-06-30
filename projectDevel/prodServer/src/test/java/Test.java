
import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.DatabaseSchema.WorkersTableSchema;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
import com.prod.prodServer.Formatters.LiferTimeEnumFormatter;
import com.prod.prodServer.Helpers.WorkerOpreationHelper;
import com.prod.prodServer.Worksers.WorkerOreation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shubham
 */
public class Test {

    public static void main(String[] args) throws IOException, SQLException {
        LiferTimeEnumFormatter format = new LiferTimeEnumFormatter();
        System.out.println("Test.main()"+format.formatData("ACTIVE").name());
        
            }
    
}
