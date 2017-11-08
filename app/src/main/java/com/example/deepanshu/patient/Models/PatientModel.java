package com.example.deepanshu.patient.Models;

/**
 * Created by Deepanshu on 11/8/2017.
 */

public class PatientModel {
    private String patientName;
    private String patientId;
    private String patientGender;
    private String patientPulseRate;
    private String patientTemp;
    private String patientStatus;
    private String patientLocation;
    private String patientRoomtemp;
    private String patientPhoneNumber;

    //Setter Function
    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public void setPatientPulseRate(String patientPulseRate) {
        this.patientPulseRate = patientPulseRate;
    }

    public void setPatientRoomtemp(String patientRoomtemp) {
        this.patientRoomtemp = patientRoomtemp;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public void setPatientTemp(String patientTemp) {
        this.patientTemp = patientTemp;
    }

    //Getter Function
    public String getPatientGender() {
        return patientGender;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public String getPatientPulseRate() {
        return patientPulseRate;
    }

    public String getPatientRoomtemp() {
        return patientRoomtemp;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public String getPatientTemp() {
        return patientTemp;
    }
}
