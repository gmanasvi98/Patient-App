package com.example.deepanshu.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PatientDetailActivity extends AppCompatActivity {
    private TextView patientId, patientName, patientGender, patientLocation, patientPhoneNumber,
             patientPulseRate, patientRommTemp, patientStatus, patientTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        Bundle mBundle = this.getIntent().getExtras();


        patientId = (TextView) findViewById(R.id.patient_detail_patientId);
        patientName = (TextView) findViewById(R.id.patient_detail_patientName);
        patientGender = (TextView) findViewById(R.id.patient_detail_patientGender);
        patientLocation = (TextView) findViewById(R.id.patient_detail_patientLocation);
        patientPhoneNumber = (TextView) findViewById(R.id.patient_detail_patientPhoneNumber);
        patientPulseRate = (TextView) findViewById(R.id.patient_detail_patientPulseRate);
        patientRommTemp = (TextView) findViewById(R.id.patient_detail_patientRoomtemp);
        patientStatus = (TextView) findViewById(R.id.patient_detail_patientStatus);
        patientTemp = (TextView) findViewById(R.id.patient_detail_patientTemp);

        patientId.setText(mBundle.getString("id"));
        patientName.setText(mBundle.getString("name"));
        patientGender.setText(mBundle.getString("gender"));
        patientLocation.setText(mBundle.getString("loc"));
        patientPhoneNumber.setText(mBundle.getString("phn"));
        patientPulseRate.setText(mBundle.getString("rate"));
        patientRommTemp.setText(mBundle.getString("rtemp"));
        patientStatus.setText(mBundle.getString("status"));
        patientTemp.setText(mBundle.getString("temp"));

    }
}
