package model.domain;

import java.time.LocalDate;

public class Prescription {
    private final String prescriptionID;
    private final String patientID;
    private final String clinicianID;
    private final String appointmentID;
    private final LocalDate prescriptionDate;
    private final String medicationName;
    private final String dosage;
    private final String frequency;
    private final int durationDays;
    private final int quantity;
    private final String instructions;
    private final String pharmacyName;
    private final String status;
    private final LocalDate issueDate;
    private final LocalDate collectionDate;

    public Prescription(String prescriptionID, String patientID, String clinicianID, String appointmentID, 
                        LocalDate prescriptionDate, String medicationName, String dosage, String frequency, 
                        int durationDays, int quantity, String instructions, String pharmacyName, 
                        String status, LocalDate issueDate, LocalDate collectionDate) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.clinicianID = clinicianID;
        this.appointmentID = appointmentID;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    public String getPrescriptionID() { return prescriptionID; }
    public String getPatientID() { return patientID; }
    public String getClinicianID() { return clinicianID; }
    public String getAppointmentID() { return appointmentID; }
    public LocalDate getPrescriptionDate() { return prescriptionDate; }
    public String getMedicationName() { return medicationName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }
    public int getQuantity() { return quantity; }
    public String getInstructions() { return instructions; }
    public String getPharmacyName() { return pharmacyName; }
    public String getStatus() { return status; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getCollectionDate() { return collectionDate; }

    // Domain methods
    public void dispense() {
        // TODO: Implement dispense
    }

    public void markCollected() {
        // TODO: Implement markCollected
    }

    public void viewDetails() {
        // TODO: Implement viewDetails
    }

    public void updateStatus() {
        // TODO: Implement updateStatus
    }
}
