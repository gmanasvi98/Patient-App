package com.example.deepanshu.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private EditText patientName, patientId, patientGender;
    private TextView requestMsg;
    private Button submitButton;
    private String urlAddress = "http://192.168.1.113:3001/api/patient/new";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        patientName = (EditText) findViewById(R.id.patient_name_editText);
        patientId = (EditText) findViewById(R.id.patient_id_editText);
        patientGender = (EditText) findViewById(R.id.patient_gender_editText);
        submitButton = (Button) findViewById(R.id.patient_register_button);
        requestMsg = (TextView) findViewById(R.id.patient_register_msg);

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
                    jsonParam.put("driver_gender", patientGender.getText().toString());

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    if(conn.getResponseCode() == 200){
                        requestMsg.setText("Patient Added SuccessFully");
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
