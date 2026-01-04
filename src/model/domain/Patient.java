package model.domain;

public class Patient {
    private final String patientId;  
    private final String nhsNumber;
    private final String firstName;
    private final String lastName;
    private final boolean noRead;

    public Patient(String patientId, String nhsNumber, String firstName, String lastName, boolean noRead) {
        this.patientId = patientId;
        this.nhsNumber = nhsNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.noRead = noRead;
    }

    public String getPatientId() { return patientId; }
    public String getNhsNumber() { return nhsNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public boolean isNoRead() { return noRead; }
}
