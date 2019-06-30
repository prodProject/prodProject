/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Interfaces;

import com.prod.prodServer.Enums.VerificationModeEnum;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public interface IVerificationService {
    
    public JSONObject getGetService(String info);
    public JSONObject getPostService(String uid, String email, VerificationModeEnum verificationModeEnum);
    
}
