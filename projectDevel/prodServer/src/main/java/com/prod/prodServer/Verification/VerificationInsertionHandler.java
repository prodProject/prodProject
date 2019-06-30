/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodServer.Verification;

import com.prod.prodServer.CloudSql.CloudSqlQueryExecutor;
import com.prod.prodServer.CommonCode.SecureTokenGenerator;
import com.prod.prodServer.DatabaseSchema.WorkersTableSchema;
import com.prod.prodServer.Enums.CloudSQLTableEnum;
import com.prod.prodServer.Enums.EmailTypeEnum;
import com.prod.prodServer.Enums.LifeTimeEnum;
import com.prod.prodServer.Enums.VerificationModeEnum;
import com.prod.prodServer.Formatters.LiferTimeEnumFormatter;
import com.prod.prodServer.Helpers.VerificationHelper;
import com.prod.prodServer.Services.EmailService;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author shubham
 */
public class VerificationInsertionHandler {

    private VerifiactionQueryBuilder m_queryBuilder;
    private final EmailService m_emailService;
    private LiferTimeEnumFormatter m_lifetimeEnumFormatter;
    private VerificationHelper m_verificationHelper;

    public VerificationInsertionHandler(VerifiactionQueryBuilder queryBuilder, EmailService emailService, LiferTimeEnumFormatter lifetimeEnumFormatter, VerificationHelper verificationHelper) {
        m_queryBuilder = queryBuilder;
        m_emailService = emailService;
        m_lifetimeEnumFormatter = lifetimeEnumFormatter;
        m_verificationHelper = verificationHelper;
    }

    public void insertVerificationData(String uid, String email, VerificationModeEnum verificationModeEnum) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", uid);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("lifeTime", LifeTimeEnum.ACTIVE.name());
        if (verificationModeEnum == VerificationModeEnum.THROUGH_OTP) {
            map.put("otp", SecureTokenGenerator.genreateNewOtp());
        } else {
            map.put("otp", "0");
        }
        if (verificationModeEnum == VerificationModeEnum.THROUGH_MAIL) {
            map.put("token", SecureTokenGenerator.nextToken());
        } else {
            map.put("token", "0");
        }
        String query = m_queryBuilder.insertQuery(CloudSQLTableEnum.OTP_EMAIL_VERIFICATION, map);
        boolean result = CloudSqlQueryExecutor.insertIntoTable(query);
        if (result) {
            JSONObject jsonobj = m_emailService.chooseAndSendMail(email, EmailTypeEnum.VERIFICATION_MAIL, map);
            System.err.println(jsonobj.toString());
        } else {
            //
        }
    }

    public JSONObject getUserVerify(String uid, String token) throws Exception {
        String query = m_queryBuilder.getVerificationQuery(CloudSQLTableEnum.OTP_EMAIL_VERIFICATION, uid, token);
        System.err.println(query);
        JSONObject result = CloudSqlQueryExecutor.selectFromTable(query);
        System.out.println(result.toString());
        if (result != null) {
            JSONArray jsonArray = result.getJSONArray("response");
            for (int i = 0; i < jsonArray.toList().size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println(obj.getString("uid"));
                return getQueryOnTheBasisOfLifeTimeEnum(obj.getString("uid"), VerificationModeEnum.THROUGH_MAIL);

            }

        } else {
            return m_verificationHelper.returnUpdateJsonFailed();
        }
        return m_verificationHelper.returnUpdateJsonFailed();
    }

    private JSONObject getQueryOnTheBasisOfLifeTimeEnum(String uid, VerificationModeEnum verificationModeEnum) throws Exception {
        String lifetimeenum = WorkersTableSchema.getWorkersSchema().get(21);
        String query = m_queryBuilder.getUSerInfo(CloudSQLTableEnum.WORKER_TABLE, uid);
        System.err.println(query);
        JSONObject result = CloudSqlQueryExecutor.selectFromTable(query);
        if (result != null) {
            JSONArray jsonArray = result.getJSONArray("response");
            for (int i = 0; i < jsonArray.toList().size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println(obj.toString());
                System.out.println(obj.getString("worker_lifetime"));
                LifeTimeEnum value = m_lifetimeEnumFormatter.formatData(obj.getString("worker_lifetime"));
                Map<String, String> data = m_verificationHelper.getUpdatedValues(value, verificationModeEnum);
                String quString = m_queryBuilder.userInfo(CloudSQLTableEnum.WORKER_TABLE, uid, data);
                System.out.println(quString);
                boolean qures = CloudSqlQueryExecutor.updateIntoTable(quString);
                if (qures) {
                    return m_verificationHelper.returnUpdateJsonSucess();
                } else {
                    return m_verificationHelper.returnUpdateJsonFailed();
                }

            }
        }
        return m_verificationHelper.returnUpdateJsonFailed();
    }

}
