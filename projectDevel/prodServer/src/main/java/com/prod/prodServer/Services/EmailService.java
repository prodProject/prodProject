/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Services;

import com.prod.prodServer.Email.EmailSender;
import com.prod.prodServer.Enums.EmailTypeEnum;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class EmailService {

    public JSONObject chooseAndSendMail(String email, EmailTypeEnum emailType, String token) {

        EmailSender sender = new EmailSender();
        switch (emailType) {
            case UNKNOWN:
                return null;
            case SIMPLE_MAIL:
                return sender.sendSimpleMail(email);
            case EMAIL_ATTACHMENT:
                return sender.sendEmailWithAttachment();
            case VERIFICATION_MAIL:
                return sender.sendVerificationMail(email, token);
        }

        return null;
    }

    public JSONObject chooseAndSendMail(String email, EmailTypeEnum emailTypeEnum, Map<String, String> map) {
        EmailSender sender = new EmailSender();
        switch (emailTypeEnum) {
            case UNKNOWN:
                return null;
            case SIMPLE_MAIL:
                return sender.sendSimpleMail(email);
            case EMAIL_ATTACHMENT:
                return sender.sendEmailWithAttachment();
            case VERIFICATION_MAIL:
                return sender.sendVerificationMail(email, map);
        }

        return null;
    }

}
