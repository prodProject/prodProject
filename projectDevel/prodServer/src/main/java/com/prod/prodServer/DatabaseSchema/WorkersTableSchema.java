/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.DatabaseSchema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shubham
 */
public class WorkersTableSchema {

    public static ArrayList<String> getWorkersSchema() {
        ArrayList<String> schema = new ArrayList<>();
        schema.add("worker_uid");
        schema.add("worker_fname");
        schema.add("worker_lastname");
        schema.add("worker_qualification");
        schema.add("worker_contactno");
        schema.add("worker_email");
        schema.add("worker_password");
        schema.add("worker_IdProof");
        schema.add("worker_shopName");
        schema.add("worker_shopAddress");
        schema.add("worker_gender");
        schema.add("worker_dob");
        schema.add("worker_profession");
        schema.add("worker_martialStaus");
        schema.add("worker_expeirence");
        schema.add("worker_currentLat");
        schema.add("worker_currentLong");
        schema.add("worker_residenceAddress");
        schema.add("worker_photo");
        schema.add("worker_Descrtiption");
        schema.add("worker_timestamp");
        schema.add("worker_lifetime");
        return schema;
    }
    
    public static ArrayList<String> getOtpAndEmailVerificationSchema(){
        ArrayList<String> schema = new ArrayList<>();
        schema.add("uid");
        schema.add("otp");
        schema.add("token");
        schema.add("timestamp");
        schema.add("lifeTime");
        return schema;
    }
    
    public static ArrayList<String> getWorkerTypeConfigSchema(){
        ArrayList<String> schema = new ArrayList<>();
        schema.add("wid");
        schema.add("worker_type");
        schema.add("category");
        return schema;
    }

}
