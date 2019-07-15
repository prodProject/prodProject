
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.prod.prodServer.CloudSql.CloudSqlQueryBuilder;
import com.prod.prodServer.EncodersAndDecoders.Base64EncoderDecoder;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Formatters.CloudSqlEnumsFormatter;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
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
public class CSVReaderTest {

    public static void main(String[] args) {
        readDataFromCustomSeperator("/home/shubham/Videos/workersheet.csv");
    }

    public static void readDataFromCustomSeperator(String file) {

        try {
            FileReader filereader = new FileReader(file);
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();
            List<String[]> allData = csvReader.readAll();

            // Print Data. 
//            for (String[] row : allData) {
//                for (String cell : row) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
//            }
            int counterIndex = 0;

            for (int i = 2; i < allData.size(); i++) {
                String[] csvrow = allData.get(i);
                getInsertSqlQuery(csvrow);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getInsertSqlQuery(String[] csvrow) {
        CloudSqlQueryBuilder builder = new CloudSqlQueryBuilder(new CloudSqlEnumsFormatter());
        int counter = 2;
        String category = "";
        Map<String, String> map = new HashMap<String, String>();
        map.put("wid", Base64EncoderDecoder.convert(Integer.parseInt(csvrow[0])));
        map.put("worker_type", csvrow[1]);
        while (true) {
            if (csvrow[counter].toLowerCase().equals("others")) {
                if (category.equals("")) {
                    category = category + csvrow[counter].toLowerCase().trim();
                } else {
                    category = category + "," + csvrow[counter].toLowerCase().trim();
                }
                break;
            } else {
                if (category.equals("")) {
                    category = category + csvrow[counter].toLowerCase().trim();
                    counter++;
                } else {
                    category = category + "," + csvrow[counter].toLowerCase().trim();
                    counter++;
                }

            }
        }
        map.put("category", category);
        String query = builder.insertQueryWorKerConfig(CloudSQLTableEnum.WORKER_TYPE_CONFIG, map);
        System.out.println(query);
    }
}
