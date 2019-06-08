/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Helpers;

import com.prod.prodServer.CommonCode.GenerateShortUUID;
import com.prod.prodServer.Enums.LifeTimeEnum;
import com.prod.prodServer.Enums.ResponseEnum;
import java.util.Map;
import javax.inject.Singleton;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
@Singleton
public class WorkerOpreationHelper {

    public Map<String, String> addWorkerConfigWhileCreated(Map<String, String> workerInformation) {
        workerInformation.replace("worker_uid", workerInformation.get("worker_uid"), GenerateShortUUID.next());
        workerInformation.replace("worker_timestamp", workerInformation.get("worker_timestamp"), String.valueOf(System.currentTimeMillis()));
        workerInformation.replace("worker_lifeTime", workerInformation.get("worker_lifeTime"), LifeTimeEnum.NOT_VERIFIED.name());
        return workerInformation;
    }

    public Map<String, String> addWorkerConfigWhileUpdate(Map<String, String> workerInformation) {
        workerInformation.replace("worker_uid", workerInformation.get("worker_uid"), GenerateShortUUID.next());
        workerInformation.replace("worker_timestamp", workerInformation.get("worker_timestamp"), String.valueOf(System.currentTimeMillis()));
        workerInformation.replace("worker_lifeTime", workerInformation.get("worker_lifeTime"), LifeTimeEnum.NOT_VERIFIED.name());
        return workerInformation;
    }

    public JSONObject returnInsertJsonSucess() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 1);
        obj.put("code", 200);
        obj.put("message", "User Created");
        obj.put("status", ResponseEnum.SUCCESS.name());
        return obj;
    }

    public JSONObject returnUpdateJsonSucess() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 1);
        obj.put("code", 200);
        obj.put("message", "User Updated");
        obj.put("status", ResponseEnum.SUCCESS.name());
        return obj;
    }

    public JSONObject returnUpdateJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 500);
        obj.put("message", "User Updation Failed");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }

    public JSONObject returnInsertJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 500);
        obj.put("message", "User Creation Failed");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }

    public JSONObject returnJsonFailed() {
        JSONObject obj = new JSONObject();
        obj.put("status_code", 0);
        obj.put("code", 404);
        obj.put("message", "User Not Exists Or Invalid Rrequest");
        obj.put("status", ResponseEnum.FAILED.name());
        return obj;
    }
}
