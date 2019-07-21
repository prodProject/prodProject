/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.CommonCode;

import com.prod.prodServer.Enums.ResponseEnum;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class JSONResponses {

    public static JSONObject returnInsertJsonSucess() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 1);
        obj.put("code", 200);
        obj.put("message", "User Created");
        obj.put("status", ResponseEnum.SUCCESS.name());
        return obj;
    }

    public static JSONObject returnUpdateJsonSucess() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 1);
        obj.put("code", 200);
        obj.put("message", "User Updated");
        obj.put("status", ResponseEnum.SUCCESS.name());
        return obj;
    }

    public static JSONObject returnUpdateJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 500);
        obj.put("message", "User Updation Failed");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }

    public static JSONObject returnInsertJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 500);
        obj.put("message", "User Creation Failed");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }

    public static JSONObject returnJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 404);
        obj.put("message", "User Not Exists Or Invalid Rrequest");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }

    public static JSONObject returnJsonSuccess() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 1);
        obj.put("code", 200);
        obj.put("message", "success");
        obj.put("status", ResponseEnum.SUCCESS.name());
        return obj;
    }
}
