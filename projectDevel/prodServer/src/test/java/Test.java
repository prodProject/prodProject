
import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.CommonCode.GenerateShortUUID;
import com.prod.prodServer.DatabaseSchema.WorkersTableSchema;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        ArrayList<String> array = WorkersTableSchema.getWorkersSchema();
        Map<String,String> map = new HashMap<String, String>();
        for (String data : array) {
            map.put(data, "DUMMY");
        }
        
        CloudSqlQueryBuilder builder = new CloudSqlQueryBuilder(new CloudSqlEnumsFormatter());
        String query = builder.insertQuery(CloudSQLTableEnum.WORKER_TABLE, map);
        System.out.println(""+ query);
    }
}
