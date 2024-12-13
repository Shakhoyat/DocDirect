package com.example.dox.docdirect;

public class AppointmentClass {
    private String appointmentDate;
    private String appointmentTime;
    private String doctorName;
    private String speciality;
    private Double taka;
    private String place;

    public AppointmentClass(String appointmentDate, String appointmentTime, String doctorName, String speciality, Double taka, String place) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.taka = taka;
        this.place = place;
    }

    // Getters and Setters
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Double getTaka() {
        return taka;
    }

    public void setTaka(Double taka) {
        this.taka = taka;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
