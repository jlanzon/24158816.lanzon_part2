package model.domain;

import java.time.LocalDate;

public class Staff {
    private final String staffID;
    private final String firstName;
    private final String lastName;
    private final String role;
    private final String department;
    private final String facilityID;
    private final String phoneNumber;
    private final String email;
    private final String employmentStatus;
    private final LocalDate startDate;
    private final String lineManager;
    private final String accessLevel;

    public Staff(String staffID, String firstName, String lastName, String role, 
                 String department, String facilityID, String phoneNumber, String email, 
                 String employmentStatus, LocalDate startDate, String lineManager, String accessLevel) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department = department;
        this.facilityID = facilityID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }

    public String getStaffID() { return staffID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role; }
    public String getDepartment() { return department; }
    public String getFacilityID() { return facilityID; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getEmploymentStatus() { return employmentStatus; }
    public LocalDate getStartDate() { return startDate; }
    public String getLineManager() { return lineManager; }
    public String getAccessLevel() { return accessLevel; }

    // Domain methods
    public void registerPatient() {
        // TODO: Implement registerPatient
    }

    public void manageAppointments() {
        // TODO: Implement manageAppointments
    }

    public void processArrival() {
        // TODO: Implement processArrival
    }

    public void updatePatientInfo() {
        // TODO: Implement updatePatientInfo
    }
}
