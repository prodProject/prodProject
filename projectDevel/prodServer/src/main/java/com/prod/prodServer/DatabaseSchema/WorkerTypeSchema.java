/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.DatabaseSchema;

import com.prod.prodServer.CommonCode.Lists;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shubham
 */
public class WorkerTypeSchema {

    public static String getworkerUid() {
        return "worker_uid";
    }

    public static String getworkerType() {
        return "worker_type";
    }

    public static String getworkerCategory() {
        return "worker_category";
    }

    public static String getworkerLat() {
        return "worker_lat";
    }

    public static String getworkerLong() {
        return "worker_long";
    }

    public static List<String> getSchema() {
        List<String> schema = Lists.newArrayList();
        schema.add("worker_uid");
        schema.add("worker_type");
        schema.add("worker_category");
        schema.add("worker_lat");
        schema.add("worker_long");
        return schema;
    }

}
