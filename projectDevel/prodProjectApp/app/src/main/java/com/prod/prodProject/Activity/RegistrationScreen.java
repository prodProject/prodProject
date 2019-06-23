package com.prod.prodProject.Activity;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.prod.prodProject.CommonClasses.JSONParserConvertor;
import com.prod.prodProject.Enums.UserGenderTypeEnum;
import com.prod.prodProject.HTTPCLient.OptimusHTTP;
import com.prod.prodProject.Helpers.ServerUrlManager;
import com.prod.prodProject.Helpers.ValidationHelper;
import com.prod.prodProject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationScreen extends AppCompatActivity {

    private ValidationHelper m_helper;

    private Button register;
    private EditText FName;
    private EditText LName;
    private EditText Email;
    private EditText MobNo;
    private EditText Pass;
    private EditText CPass;
    private RadioGroup Gender;
    private UserGenderTypeEnum GenderEnum;
    private String fname, lname, email, mobno, pass, cpass;

    private ArrayMap<String, Object> m_params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        initilization();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesBeforeRegistratin();
                if (m_helper.validate(fname, lname, email, mobno, pass, cpass, GenderEnum)) {
                    addParameterForServerReq();
                    OptimusHTTP caller = new OptimusHTTP(getApplicationContext());
                    caller.setMethod(OptimusHTTP.METHOD_POST);
                    caller.makeRequest(ServerUrlManager.getRegistrationURL(), m_params, new OptimusHTTP.ResponseListener() {
                        @Override
                        public void onFailure(String msg) {
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(String msg) {
                            Log.e("resp", msg);
                            JSONObject result = JSONParserConvertor.makeStringToJsonObject(msg);
                            try {
                                if (result.getInt("status_code") == 1) {
                                    Toast.makeText(getApplicationContext(), String.valueOf(result.getString("message")), Toast.LENGTH_LONG).show();
                                    String Phonenumber = "+91" + mobno;
                                    Intent intent = new Intent(getApplicationContext(), MobileVerification.class);
                                    intent.putExtra("phoneNo", Phonenumber);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid details", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void addParameterForServerReq() {
        m_params.put("worker_fname", fname);
        m_params.put("worker_lastname", lname);
        m_params.put("worker_email", email);
        m_params.put("worker_contactno", mobno);
        m_params.put("worker_password", pass);
        m_params.put("worker_gender", GenderEnum.name());
    }

    private void initilization() {
        register = (Button) findViewById(R.id.wreg);
        FName = (EditText) findViewById(R.id.wfName);
        LName = (EditText) findViewById(R.id.wlName);
        Email = (EditText) findViewById(R.id.wemail);
        MobNo = (EditText) findViewById(R.id.wmob);
        Pass = (EditText) findViewById(R.id.wpass);
        CPass = (EditText) findViewById(R.id.wcpass);
        Gender = (RadioGroup) findViewById(R.id.gender);
        m_params = new ArrayMap<String, Object>();
        m_helper = new ValidationHelper();
    }

    private void getValuesBeforeRegistratin() {
        fname = FName.getText().toString().trim().toLowerCase();
        lname = LName.getText().toString().trim().toLowerCase();
        email = Email.getText().toString().trim().toLowerCase();
        mobno = MobNo.getText().toString().trim().toLowerCase();
        pass = Pass.getText().toString().trim().toLowerCase();
        cpass = CPass.getText().toString().trim().toLowerCase();
        int id = Gender.getCheckedRadioButtonId();
        switch (id) {
            case R.id.male:
                GenderEnum = UserGenderTypeEnum.MALE;
                break;
            case R.id.female:
                GenderEnum = UserGenderTypeEnum.FEMALE;
                break;
            default:
                GenderEnum = UserGenderTypeEnum.UNKNOWN;
                break;
        }

    }


}
