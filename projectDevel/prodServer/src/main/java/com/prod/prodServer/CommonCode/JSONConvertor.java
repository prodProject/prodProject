/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CommonCode;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class JSONConvertor {

    public static JSONArray getResultSetAsJsonArray(ResultSet resultSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        }
        return jsonArray;
    }

    public static JSONObject getResultSetAsJsonObject(ResultSet resultSet) throws SQLException {
        JSONObject JsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        do {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.put(obj);
            }
        } while (resultSet.next());
        JsonObject.put("response", jsonArray);
        return JsonObject;
    }

    public static JSONObject convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
        JSONObject JsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(); 
   
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                // if value in DB is null, then we set it to default value
                if (columnValue == null){
                    columnValue = "null";
                }
                if (obj.has(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.put(obj);
        }
         JsonObject.put("response", jsonArray);
        return JsonObject;
    }

    public static int converBooleanIntoInt(boolean bool){
        if (bool) return 1;
        else return 0;
    }

    public static int convertBooleanStringIntoInt(String bool){
        if (bool.equals("false")) return 0;
        else if (bool.equals("true")) return 1;
        else {
            throw new IllegalArgumentException("wrong value is passed to the method. Value is "+bool);
        }
    }

    public static double getDoubleOutOfString(String value, String format, Locale locale){
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat(format,otherSymbols);
        String formattedValue = f.format(Double.parseDouble(value));
        double number = Double.parseDouble(formattedValue);
        return  Math.round(number * 100.0) / 100.0;
    }

}
