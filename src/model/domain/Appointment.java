package model.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private final String appointmentID;
    private final String patientID;
    private final String clinicianID;
    private final String facilityID;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;
    private final int durationMinutes;
    private final String appointmentType;
    private final String status;
    private final String reasonForVisit;
    private final String notes;
    private final LocalDate createdDate;
    private final LocalDate lastModified;

    public Appointment(String appointmentID, String patientID, String clinicianID, String facilityID, 
                       LocalDate appointmentDate, LocalTime appointmentTime, int durationMinutes, 
                       String appointmentType, String status, String reasonForVisit, String notes, 
                       LocalDate createdDate, LocalDate lastModified) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.facilityID = facilityID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public String getAppointmentID() { return appointmentID; }
    public String getPatientID() { return patientID; }
    public String getClinicianID() { return clinicianID; }
    public String getFacilityID() { return facilityID; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getAppointmentTime() { return appointmentTime; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getAppointmentType() { return appointmentType; }
    public String getStatus() { return status; }
    public String getReasonForVisit() { return reasonForVisit; }
    public String getNotes() { return notes; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getLastModified() { return lastModified; }

    // Domain methods
    public void schedule() {
        // TODO: Implement schedule
    }

    public void modify() {
        // TODO: Implement modify
    }

    public void cancel() {
        // TODO: Implement cancel
    }

    public void checkStatus() {
        // TODO: Implement checkStatus
    }

    public void addNotes() {
        // TODO: Implement addNotes
    }
}
