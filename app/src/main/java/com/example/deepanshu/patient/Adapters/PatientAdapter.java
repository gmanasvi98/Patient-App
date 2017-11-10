package com.example.deepanshu.patient.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deepanshu.patient.Models.PatientModel;
import com.example.deepanshu.patient.PatientDetailActivity;
import com.example.deepanshu.patient.R;

import java.util.ArrayList;

/**
 * Created by Deepanshu on 11/8/2017.
 */

public  class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.patientViewHolder> {
    public ArrayList<PatientModel> patientArrayList;
    public Context patientContext;

    class patientViewHolder extends RecyclerView.ViewHolder {
        TextView patientNo;
        TextView patientName;
        TextView patientGender;
        ConstraintLayout constraintLayout;

        public patientViewHolder(View itemView) {
            super(itemView);
            patientNo = (TextView) itemView.findViewById(R.id.patient_item_id);
            patientName = (TextView) itemView.findViewById(R.id.patient_item_name);
            patientGender = (TextView) itemView.findViewById(R.id.patient_item_gender);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.patient_recyclerView);
        }
    }

    public PatientAdapter(Context context, ArrayList<PatientModel> arrayList) {
        this.patientArrayList = arrayList;
        this.patientContext = context;
    }

    @Override
    public patientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_recycler, parent, false);
        return new patientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final patientViewHolder holder, int position) {
        final PatientModel patientModel = patientArrayList.get(position);

        holder.patientNo.setText(patientModel.getPatientId());
        holder.patientName.setText(patientModel.getPatientName());
        holder.patientGender.setText(patientModel.getPatientGender());
        if (patientModel.getPatientStatus().equals("help")){
            holder.constraintLayout.setBackgroundColor(patientContext.getResources().getColor(R.color.colorDanger));
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(patientContext, PatientDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle mBundle = new Bundle();
                //mBundle.putString("type","celebs");
                mBundle.putString("id",patientModel.getPatientId());
                mBundle.putString("gender",patientModel.getPatientGender());
                mBundle.putString("loc",patientModel.getPatientLocation());
                mBundle.putString("name",patientModel.getPatientName());
                mBundle.putString("phn",patientModel.getPatientPhoneNumber());
                mBundle.putString("rate",patientModel.getPatientPulseRate());
                mBundle.putString("rtemp",patientModel.getPatientRoomtemp());
                mBundle.putString("status",patientModel.getPatientStatus());
                mBundle.putString("temp",patientModel.getPatientTemp());
                intent.putExtras(mBundle);
                patientContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientArrayList.size();
    }
}
