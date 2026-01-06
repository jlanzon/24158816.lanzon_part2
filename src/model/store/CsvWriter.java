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
        // patientID,nhsNumber,firstName,lastName,dateOfBirth,gender,phoneNumber,email,address,postcode,emergencyContactName,emergencyContactPhone,registrationDate,gpSurgeryID,noRead
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
                "false"; // noRead default
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void appendPrescriptionToCsv(Prescription p, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        // id,appointment_id,patient_first_name,patient_last_name,clinician_id,issued_date,medication_name,dosage,instructions,quantity,repeat_authorised,status
        String line = p.getPrescriptionID() + "," +
                p.getAppointmentID() + "," +
                "Unknown" + "," + // patient_first_name
                "Unknown" + "," + // patient_last_name
                p.getClinicianID() + "," +
                p.getIssueDate() + "," +
                p.getMedicationName() + "," +
                p.getDosage() + "," +
                "\"" + p.getInstructions() + "\"" + "," +
                p.getQuantity() + "," +
                "false" + "," + // repeat_authorised
                p.getStatus();
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void appendReferralToCsv(Referral r, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        // id,appointment_id,patient_first_name,patient_last_name,referring_clinician_id,from_facility_id,to_service,priority,reason,referral_date,status
        String line = r.getReferralID() + "," +
                r.getAppointmentID() + "," +
                "Unknown" + "," + // patient_first_name
                "Unknown" + "," + // patient_last_name
                r.getReferringClinicianID() + "," +
                r.getReferringFacilityID() + "," +
                "Unknown Service" + "," + // to_service
                r.getUrgencyLevel() + "," +
                "\"" + r.getReferralReason() + "\"" + "," +
                r.getReferralDate() + "," +
                r.getStatus();
        writer.write(line);
        writer.newLine();
        writer.close();
    }
}
