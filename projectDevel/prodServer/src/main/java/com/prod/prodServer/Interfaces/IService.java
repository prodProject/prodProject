/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Interfaces;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public interface IService {
    
   
    public JSONObject getService(Map<String, String> info);
    
}
