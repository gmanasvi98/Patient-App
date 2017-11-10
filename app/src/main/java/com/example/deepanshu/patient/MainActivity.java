package com.example.deepanshu.patient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deepanshu.patient.Adapters.PatientAdapter;
import com.example.deepanshu.patient.Models.PatientModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PatientAdapter patientAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.patient_recyclerView);
        LinearLayoutManager layoutManagerCast = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerCast);
        CommonFetchTask commonFetchTask = new CommonFetchTask();
        commonFetchTask.execute("http://192.168.43.125:3000/api/patients/all");
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main_refresh:
                CommonFetchTask commonFetchTask = new CommonFetchTask();
                commonFetchTask.execute("http://192.168.43.125:3000/api/patients/all");
                return true;
            case R.id.main_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class CommonFetchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonData = null;
            try {
                //setting the urlConnection
                Log.v("hello","from back ground thread");
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream stream = urlConnection.getInputStream();
                if (stream == null){
                    jsonData = null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(stream));
                String inputLine;
                while ((inputLine = reader.readLine()) != null){
                    stringBuffer.append(inputLine + "\n");
                }
                if (stringBuffer.length() == 0){
                    jsonData = null;
                }
                jsonData = stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jsonData) {
            ArrayList<PatientModel> patientModelArrayList = new ArrayList<>();
            super.onPostExecute(jsonData);
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray resultArray = jsonObject.getJSONArray("results");
                for (int i=0; i < resultArray.length(); i++){
                    JSONObject patient = (JSONObject) resultArray.get(i);
                    Log.i("reults",patient.toString());
                    PatientModel patientModel = new PatientModel();
                    patientModel.setPatientId(patient.get("patient_id").toString());
                    patientModel.setPatientName(patient.get("patient_name").toString());
                    patientModel.setPatientGender(patient.get("patient_gender").toString());
                    patientModel.setPatientLocation(patient.get("patient_location").toString());
                    patientModel.setPatientPhoneNumber(patient.get("patient_phone_no").toString());
                    patientModel.setPatientPulseRate(patient.get("patient_heartbeat").toString());
                    patientModel.setPatientRoomtemp(patient.get("patient_room_temp").toString());
                    patientModel.setPatientStatus(patient.get("patient_status").toString());
                    patientModel.setPatientTemp(patient.get("patient_temp").toString());
                    patientModelArrayList.add(patientModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            patientAdapter = new PatientAdapter(getApplicationContext(),patientModelArrayList);
            recyclerView.setAdapter(patientAdapter);
        }

        @Override
        protected void onCancelled(String arrayList) {
            super.onCancelled(arrayList);
        }
    }
}
