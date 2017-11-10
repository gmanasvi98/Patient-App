package com.example.deepanshu.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private EditText patientName, patientId, patientGender, patientPhone;
    private TextView requestMsg;
    private Button submitButton;
    private android.support.v7.widget.Toolbar toolbar;
    private String urlAddress = "http://192.168.43.125:3000/api/patient/new";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        patientName = (EditText) findViewById(R.id.patient_name_editText);
        patientId = (EditText) findViewById(R.id.patient_id_editText);
        patientGender = (EditText) findViewById(R.id.patient_gender_editText);
        submitButton = (Button) findViewById(R.id.patient_register_button);
        requestMsg = (TextView) findViewById(R.id.patient_register_msg);
        patientPhone = (EditText) findViewById(R.id.patient_phone_editText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost();
            }
        });
    }

    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAddress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("patient_name", patientName.getText().toString());
                    jsonParam.put("patient_id", patientId.getText().toString());
                    jsonParam.put("patient_gender", patientGender.getText().toString());
                    jsonParam.put("patient_phone_no", patientPhone.getText().toString());

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();
                    Log.e("info",conn.getResponseCode()+"");
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        requestMsg.setText("Patient Added SuccessFully");
                        Log.e("info","Patient Added SuccessFully");
                    }
                    else{
                        requestMsg.setText("Unable to add Patient");
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
