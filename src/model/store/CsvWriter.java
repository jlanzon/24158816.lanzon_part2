package model.store;

import model.domain.Patient;
import model.domain.Prescription;
import model.domain.Referral;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CsvWriter {

    public void appendPatientToCsv(Patient p, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        String line = p.getPatientId() + "," +
                p.getNhsNumber() + "," +
                p.getFirstName() + "," +
                p.getLastName() + "," +
                p.getDateOfBirth() + "," +
                p.getGender() + "," +
                p.getPhoneNumber() + "," +
                p.getEmail() + "," +
                p.getAddress() + "," +
                p.getPostcode() + "," +
                p.getEmergencyContactName() + "," +
                p.getEmergencyContactPhone() + "," +
                p.getRegistrationDate() + "," +
                p.getGpSurgeryID() + "," +
                "false"; // noRead default false. Can change to hide the data I am testing an manually check 
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void appendPrescriptionToCsv(Prescription p, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        String line = p.getPrescriptionID() + "," +
                p.getAppointmentID() + "," +
                p.getPatientID() + "," + 
                "Unknown" + "," + 
                p.getClinicianID() + "," +
                p.getIssueDate() + "," +
                p.getMedicationName() + "," +
                p.getDosage() + "," +
                "\"" + p.getInstructions() + "\"" + "," +
                p.getQuantity() + "," +
                "false" + "," + 
                p.getStatus();
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void appendReferralToCsv(Referral r, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        String line = r.getReferralID() + "," +
                r.getAppointmentID() + "," +
                r.getPatientID() + "," + // patient_first_name (Hack: Store ID here)
                "Unknown" + "," + 
                r.getReferringClinicianID() + "," +
                r.getReferringFacilityID() + "," +
                "Unknown Service" + "," + 
                r.getUrgencyLevel() + "," +
                "\"" + r.getReferralReason() + "\"" + "," +
                r.getReferralDate() + "," +
                r.getStatus();
        writer.write(line);
        writer.newLine();
        writer.close();
    }
}
