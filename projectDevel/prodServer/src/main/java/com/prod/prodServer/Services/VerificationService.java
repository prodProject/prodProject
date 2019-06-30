/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Services;

import com.prod.prodServer.Enums.VerificationModeEnum;
import com.prod.prodServer.Formatters.LiferTimeEnumFormatter;
import com.prod.prodServer.Helpers.VerificationHelper;
import com.prod.prodServer.Interfaces.IVerificationService;
import com.prod.prodServer.Verification.VerifiactionQueryBuilder;
import com.prod.prodServer.Verification.VerificationInsertionHandler;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class VerificationService implements IVerificationService {

    @Override
    public JSONObject getGetService(String info) {
        VerificationInsertionHandler handler = new VerificationInsertionHandler(new VerifiactionQueryBuilder(), new EmailService(), new LiferTimeEnumFormatter(), new VerificationHelper());
        String[] userData = info.split("@");
        try {
            if (userData.length < 2) {
                return null;
            }
           return handler.getUserVerify(userData[0], userData[1]);
        } catch (Exception ex) {
            Logger.getLogger(VerificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public JSONObject getPostService(String uid, String email, VerificationModeEnum verificationModeEnum) {
        VerificationInsertionHandler handler = new VerificationInsertionHandler(new VerifiactionQueryBuilder(), new EmailService(), new LiferTimeEnumFormatter(), new VerificationHelper());
        try {
            handler.insertVerificationData(uid, email, verificationModeEnum);
        } catch (SQLException ex) {
            Logger.getLogger(VerificationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
