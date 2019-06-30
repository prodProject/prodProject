/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Helpers;

import com.prod.prodServer.DatabaseSchema.WorkersTableSchema;
import com.prod.prodServer.Enums.LifeTimeEnum;
import com.prod.prodServer.Enums.ResponseEnum;
import com.prod.prodServer.Enums.VerificationModeEnum;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class VerificationHelper {

    public Map<String, String> getUpdatedValues(LifeTimeEnum value, VerificationModeEnum modeEnum) {
        Map<String, String> map = new HashMap<String, String>();
        if (value == LifeTimeEnum.VEFFIED_VIA_OTP || value == LifeTimeEnum.VERFIED_VIA_EMAIL) {
            map.put(WorkersTableSchema.getWorkersSchema().get(21), LifeTimeEnum.VERIFIED.name());
        } else if (value == LifeTimeEnum.NOT_VERIFIED) {
            if (modeEnum == VerificationModeEnum.THROUGH_MAIL) {
                map.put(WorkersTableSchema.getWorkersSchema().get(21), LifeTimeEnum.VERFIED_VIA_EMAIL.name());
            } else if (modeEnum == VerificationModeEnum.THROUGH_OTP) {
                map.put(WorkersTableSchema.getWorkersSchema().get(21), LifeTimeEnum.VEFFIED_VIA_OTP.name());
            }
        }

        return map;
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

}
