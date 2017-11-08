package com.example.deepanshu.patient.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        public patientViewHolder(View itemView) {
            super(itemView);
            patientNo = (TextView) itemView.findViewById(R.id.patient_item_id);
            patientName = (TextView) itemView.findViewById(R.id.patient_item_name);
            patientGender = (TextView) itemView.findViewById(R.id.patient_item_gender);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(patientContext, PatientDetailActivity.class);
                    Bundle mBundle = new Bundle();
                    //mBundle.putString("type","celebs");
                    //mBundle.putString("id",castModel.getId());
                    intent.putExtras(mBundle);
                    patientContext.startActivity(intent);
                }
            });
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
        PatientModel patientModel = patientArrayList.get(position);

        holder.patientNo.setText(patientModel.getPatientId());
        holder.patientName.setText(patientModel.getPatientName());
        holder.patientGender.setText(patientModel.getPatientGender());
    }

    @Override
    public int getItemCount() {
        return patientArrayList.size();
    }
}
