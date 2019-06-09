package com.prod.prodProject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prod.prodProject.CommonClasses.ApiCallerService;
import com.prod.prodProject.CommonClasses.JSONParserConvertor;
import com.prod.prodProject.HTTPCLient.OptimusHTTP;
import com.prod.prodProject.Helpers.ServerUrlManager;
import com.prod.prodProject.R;

import org.json.JSONException;

public class SplashScreen extends AppCompatActivity {

    private android.support.v4.util.ArrayMap<String, Object> m_params = new android.support.v4.util.ArrayMap<String, Object>();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        btn = (Button) findViewById(R.id.button);
        m_params.put("worker_fname", "shubhsma");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* ApiCallerService.getInstance().post(ServerUrlManager.getRegistrationURL(),m_params, new ApiCallerService.RestClientListener() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("resp",response);
                    }

                    @Override
                    public void onError(String error) {

                    }
                });*/
                OptimusHTTP client = new OptimusHTTP(getApplicationContext());
                client.setMethod(OptimusHTTP.METHOD_POST);
                //client.setContentType(OptimusHTTP.CONTENT_TYPE_JSON);
                client.makeRequest(ServerUrlManager.getRegistrationURL(), m_params, new OptimusHTTP.ResponseListener() {
                    @Override
                    public void onFailure(String s) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        try {
                            Log.e("tag",JSONParserConvertor.makeStringToJsonObject(s).get("message").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });



    }


}
