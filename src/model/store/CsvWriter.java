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
        writer.write("patientId,nhsNumber,firstName,lastName,dateOfBirth,gender,phoneNumber,email,address,postcode,emergencyContactName,emergencyContactPhone,registrationDate,gpSurgeryID,noRead");
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
        writer.write("prescriptionID,appointmentID,patientID,patientFirstName,clinicianID,issueDate,medicationName,dosage,instructions,quantity,isDispensed,status,prescriptionDate,frequency,durationDays,pharmacyName,collectionDate");
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
                    p.getStatus() + "," +
                    p.getPrescriptionDate() + "," +
                    p.getFrequency() + "," +
                    p.getDurationDays() + "," +
                    p.getPharmacyName() + "," +
                    p.getCollectionDate();
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
        writer.write("referralID,appointmentID,patientID,patientFirstName,referringClinicianID,referringFacilityID,serviceRequested,urgencyLevel,referralReason,referralDate,status,referredToClinicianID,referredToFacilityID,clinicalSummary,requestedInvestigations,notes,createdDate,lastUpdated");
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
                    r.getStatus() + "," +
                    r.getReferredToClinicianID() + "," +
                    r.getReferredToFacilityID() + "," +
                    "\"" + r.getClinicalSummary() + "\"" + "," +
                    "\"" + r.getRequestedInvestigations() + "\"" + "," +
                    "\"" + r.getNotes() + "\"" + "," +
                    r.getCreatedDate() + "," +
                    r.getLastUpdated();
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
        writer.write("appointmentID,patientID,clinicianID,facilityID,appointmentDate,appointmentTime,durationMinutes,appointmentType,status,reasonForVisit,notes,createdDate,lastModified");
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
                c.getEmail() + "," +
                c.getTitle() + "," +
                c.getGmcNumber() + "," +
                c.getWorkplaceType() + "," +
                c.getEmploymentStatus() + "," +
                c.getStartDate();
        writer.write(line);
        writer.newLine();
        writer.close();
    }

    public void saveAllClinicians(java.util.List<model.domain.Clinician> clinicians, Path path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), false));
        writer.write("clinicianID,firstName,lastName,role,qualification,specialty,workplace,phoneNumber,email,title,gmcNumber,workplaceType,employmentStatus,startDate");
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
                    c.getEmail() + "," +
                    c.getTitle() + "," +
                    c.getGmcNumber() + "," +
                    c.getWorkplaceType() + "," +
                    c.getEmploymentStatus() + "," +
                    c.getStartDate();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
