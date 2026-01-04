package model.domain;

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

    public Clinician(String clinicianID, String firstName, String lastName, String role, 
                     String qualification, String specialty, String workplace, 
                     String phoneNumber, String email) {
        this.clinicianID = clinicianID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.qualification = qualification;
        this.specialty = specialty;
        this.workplace = workplace;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
