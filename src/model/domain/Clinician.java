package model.domain;

import java.time.LocalDate;

public class Clinician {
    private final String clinicianID;
    private final String firstName;
    private final String lastName;
    private final String role;
    private final String qualification;
    private final String specialty;
    private final String workplace;
    private final String phoneNumber;
    private final String email;
    private final String title;
    private final String gmcNumber;
    private final String workplaceType;
    private final String employmentStatus;
    private final LocalDate startDate;

    public Clinician(String clinicianID, String firstName, String lastName, String role, 
                     String qualification, String specialty, String workplace, 
                     String phoneNumber, String email, String title, String gmcNumber,
                     String workplaceType, String employmentStatus, LocalDate startDate) {
        this.clinicianID = clinicianID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.qualification = qualification;
        this.specialty = specialty;
        this.workplace = workplace;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.gmcNumber = gmcNumber;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    public String getClinicianID() { return clinicianID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role; }
    public String getQualification() { return qualification; }
    public String getSpecialty() { return specialty; }
    public String getWorkplace() { return workplace; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getTitle() { return title; }
    public String getGmcNumber() { return gmcNumber; }
    public String getWorkplaceType() { return workplaceType; }
    public String getEmploymentStatus() { return employmentStatus; }
    public LocalDate getStartDate() { return startDate; }

    // Domain methods
    public void viewPatientRecord() {
        // TODO: Implement viewPatientRecord
    }

    public void recordVitalSigns() {
        // TODO: Implement recordVitalSigns
    }

    public void writePrescription() {
        // TODO: Implement writePrescription
    }

    public void createReferral() {
        // TODO: Implement createReferral
    }

    public void updateAppointmentStatus() {
        // TODO: Implement updateAppointmentStatus
    }
}
