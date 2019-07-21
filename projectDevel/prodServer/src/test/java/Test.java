
import com.prod.prodServer.DatabaseSchema.WorkerTypeSchema;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.SQLQuery.SqlMaker;
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
        Map<String, String> map = new HashMap<String, String>();
        map.put(WorkerTypeSchema.getworkerUid(), "a");
        map.put(WorkerTypeSchema.getworkerLat(), "a");
        map.put(WorkerTypeSchema.getworkerCategory(), "a");
        map.put(WorkerTypeSchema.getworkerType(), "a");
        map.put(WorkerTypeSchema.getworkerLong(), "a");
        System.out.println(SqlMaker.builder().INSERTINTO(CloudSQLTableEnum.WORKER_TYPE,map).build());

    }

}
