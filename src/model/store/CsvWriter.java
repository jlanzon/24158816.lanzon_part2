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

    public void saveAllPatients(java.util.List<Patient> patients, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false)); // false to overwrite
        writer.write("patient_id,nhs_number,first_name,last_name,dob,gender,phone_number,email,address,postcode,emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id,no_read");
        writer.newLine();
        
        for (Patient p : patients) {
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
                    "false";
            writer.write(line);
            writer.newLine();
        }
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

    public void saveAllPrescriptions(java.util.List<Prescription> prescriptions, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
        writer.write("prescription_id,appointment_id,patient_id,patient_first_name,clinician_id,issue_date,medication_name,dosage,instructions,quantity,is_dispensed,status");
        writer.newLine();

        for (Prescription p : prescriptions) {
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
        }
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

    public void saveAllReferrals(java.util.List<Referral> referrals, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
        writer.write("referral_id,appointment_id,patient_id,patient_first_name,referring_clinician_id,referring_facility_id,service_requested,urgency_level,referral_reason,referral_date,status");
        writer.newLine();

        for (Referral r : referrals) {
            String line = r.getReferralID() + "," +
                    r.getAppointmentID() + "," +
                    r.getPatientID() + "," + 
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
        }
        writer.close();
    }

    public void appendAppointmentToCsv(model.domain.Appointment a, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        String line = a.getAppointmentID() + "," +
                a.getPatientID() + "," +
                a.getClinicianID() + "," +
                a.getFacilityID() + "," +
                a.getAppointmentDate() + "," +
                a.getAppointmentTime() + "," +
                a.getDurationMinutes() + "," +
                a.getAppointmentType() + "," +
                a.getStatus() + "," +
                "\"" + a.getReasonForVisit() + "\"" + "," +
                "\"" + a.getNotes() + "\"" + "," +
                a.getCreatedDate() + "," +
                a.getLastModified();
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void saveAllAppointments(java.util.List<model.domain.Appointment> appointments, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
        writer.write("appointment_id,patient_id,clinician_id,facility_id,appointment_date,appointment_time,duration_minutes,appointment_type,status,reason_for_visit,notes,created_date,last_modified");
        writer.newLine();

        for (model.domain.Appointment a : appointments) {
            String line = a.getAppointmentID() + "," +
                    a.getPatientID() + "," +
                    a.getClinicianID() + "," +
                    a.getFacilityID() + "," +
                    a.getAppointmentDate() + "," +
                    a.getAppointmentTime() + "," +
                    a.getDurationMinutes() + "," +
                    a.getAppointmentType() + "," +
                    a.getStatus() + "," +
                    "\"" + a.getReasonForVisit() + "\"" + "," +
                    "\"" + a.getNotes() + "\"" + "," +
                    a.getCreatedDate() + "," +
                    a.getLastModified();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    public void appendClinicianToCsv(model.domain.Clinician c, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
        String line = c.getClinicianID() + "," +
                c.getFirstName() + "," +
                c.getLastName() + "," +
                c.getRole() + "," +
                c.getQualification() + "," +
                c.getSpecialty() + "," +
                c.getWorkplace() + "," +
                c.getPhoneNumber() + "," +
                c.getEmail();
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void saveAllClinicians(java.util.List<model.domain.Clinician> clinicians, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
        writer.write("clinician_id,first_name,last_name,role,qualification,specialty,workplace,phone_number,email");
        writer.newLine();

        for (model.domain.Clinician c : clinicians) {
            String line = c.getClinicianID() + "," +
                    c.getFirstName() + "," +
                    c.getLastName() + "," +
                    c.getRole() + "," +
                    c.getQualification() + "," +
                    c.getSpecialty() + "," +
                    c.getWorkplace() + "," +
                    c.getPhoneNumber() + "," +
                    c.getEmail();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
