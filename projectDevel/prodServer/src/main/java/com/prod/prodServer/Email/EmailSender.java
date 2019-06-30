/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Email;

import com.prod.prodServer.CommonCode.JSONConvertor;
import com.prod.prodServer.Enums.ResponseEnum;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class EmailSender {

    private String emailAddress = "noreply@prod-project-239707.appspotmail.com";
    private final EmailHelper m_helper;

    public EmailSender() {
        m_helper = new EmailHelper();
    }

    public JSONObject sendSimpleMail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JSONObject sendEmailWithAttachment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JSONObject sendVerificationMail(String email, String token) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailAddress, ""));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email, ""));
            msg.setSubject("Your Verification Email Please Verify");
            msg.setText(m_helper.getVerificationEmail(token));
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
            return JSONConvertor.returnJsonFailed(e.getMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return JSONConvertor.returnJsonFailed(e.getMessage());
        }
        return JSONConvertor.returnJsonSuccess();

    }

    public JSONObject sendVerificationMail(String email, Map<String, String> map) {
         Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailAddress, ""));
            msg.setContent(m_helper.getVerificationEmail(map), "text/html");
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email, ""));
            msg.setSubject("Your Verification Email Please Verify");
            //msg.setText();
            Transport.send(msg);
            
        } catch (AddressException e) {
            e.printStackTrace();
            return JSONConvertor.returnJsonFailed(e.getMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return JSONConvertor.returnJsonFailed(e.getMessage());
        }
        return JSONConvertor.returnJsonSuccess();
    }

}
