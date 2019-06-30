/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Email;

import java.util.Map;

/**
 *
 * @author shubham
 */
public class EmailHelper {

    private String BASE_URL = "https://prod-project-239707.appspot.com/";

    public String getVerificationEmail(String token) {
        String content = "Dear User,<br>This is your Verification Email.<br>Click <a href=" + BASE_URL + "verificationMain?" + token + ">here</a> to Verify<br><br>LInk will be Expired Within 15 minutes";
        return content;
    }

    String getVerificationEmail(Map<String, String> map) {
        String content = "Dear User,<br>This is your Verification Email.<br>Click <a href=" + BASE_URL + "verificationMain?" + map.get("uid") + "@" + map.get("token") + ">here</a> to Verify<br><br>LInk will be Expired Within 15 minutes";
        return content;
    }

}
