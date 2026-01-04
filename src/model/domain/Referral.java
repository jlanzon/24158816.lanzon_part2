package model.domain;

import java.time.LocalDate;

public class Referral {
    private final String referralID;
    private final String patientID;
    private final String referringClinicianID;
    private final String referredToClinicianID;
    private final String referringFacilityID;
    private final String referredToFacilityID;
    private final LocalDate referralDate;
    private final String urgencyLevel;
    private final String referralReason;
    private final String clinicalSummary;
    private final String requestedInvestigations;
    private final String status;
    private final String appointmentID;
    private final String notes;
    private final LocalDate createdDate;
    private final LocalDate lastUpdated;

    public Referral(String referralID, String patientID, String referringClinicianID, 
                    String referredToClinicianID, String referringFacilityID, String referredToFacilityID, 
                    LocalDate referralDate, String urgencyLevel, String referralReason, 
                    String clinicalSummary, String requestedInvestigations, String status, 
                    String appointmentID, String notes, LocalDate createdDate, LocalDate lastUpdated) {
        this.referralID = referralID;
        this.patientID = patientID;
        this.referringClinicianID = referringClinicianID;
        this.referredToClinicianID = referredToClinicianID;
        this.referringFacilityID = referringFacilityID;
        this.referredToFacilityID = referredToFacilityID;
        this.referralDate = referralDate;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigations = requestedInvestigations;
        this.status = status;
        this.appointmentID = appointmentID;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public String getReferralID() { return referralID; }
    public String getPatientID() { return patientID; }
    public String getReferringClinicianID() { return referringClinicianID; }
    public String getReferredToClinicianID() { return referredToClinicianID; }
    public String getReferringFacilityID() { return referringFacilityID; }
    public String getReferredToFacilityID() { return referredToFacilityID; }
    public LocalDate getReferralDate() { return referralDate; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public String getReferralReason() { return referralReason; }
    public String getClinicalSummary() { return clinicalSummary; }
    public String getRequestedInvestigations() { return requestedInvestigations; }
    public String getStatus() { return status; }
    public String getAppointmentID() { return appointmentID; }
    public String getNotes() { return notes; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getLastUpdated() { return lastUpdated; }

    // Domain methods
    public void sendReferral() {
        // TODO: Implement sendReferral
    }

    public void updateStatus() {
        // TODO: Implement updateStatus
    }

    public void addInvestigation() {
        // TODO: Implement addInvestigation
    }

    public void viewSummary() {
        // TODO: Implement viewSummary
    }
}
