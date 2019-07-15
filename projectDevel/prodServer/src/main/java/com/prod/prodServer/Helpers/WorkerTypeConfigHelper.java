/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Helpers;

import com.prod.prodServer.CommonCode.Strings;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class WorkerTypeConfigHelper {

    public JSONObject getFormattedResponse(JSONObject result) {
        JSONArray array = result.getJSONArray("response");
        for (int i = 0; i < array.length(); i++) {
            List<String> list = Strings.getStringToListOfCategory(array.getJSONObject(i).getString("category"));
            JSONArray arr = new JSONArray(list);
            array.getJSONObject(i).remove("category");
            array.getJSONObject(i).put("category", arr);
        }
        return result;
    }

}
