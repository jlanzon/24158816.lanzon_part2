package model.domain;

import java.time.LocalDate;

public class Patient {
    private final String patientId;
    private final String nhsNumber;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final String postcode;
    private final String emergencyContactName;
    private final String emergencyContactPhone;
    private final LocalDate registrationDate;
    private final String gpSurgeryID;

    public Patient(String patientId, String nhsNumber, String firstName, String lastName, 
                   LocalDate dateOfBirth, String gender, String phoneNumber, String email, 
                   String address, String postcode, String emergencyContactName, 
                   String emergencyContactPhone, LocalDate registrationDate, String gpSurgeryID) {
        this.patientId = patientId;
        this.nhsNumber = nhsNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryID = gpSurgeryID;
    }

    public String getPatientId() { return patientId; }
    public String getNhsNumber() { return nhsNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public String getGpSurgeryID() { return gpSurgeryID; }

    // Domain methods
    public void createAppointment() {
        // TODO: Implement createAppointment
    }

    public void modifyAppointment() {
        // TODO: Implement modifyAppointment
    }

    public void cancelAppointment() {
        // TODO: Implement cancelAppointment
    }

    public void viewPrescriptions() {
        // TODO: Implement viewPrescriptions
    }

    public void viewMedicalRecord() {
        // TODO: Implement viewMedicalRecord
    }
}
