package model.service;

import model.domain.Prescription;
import model.domain.Referral;
import model.store.CsvPaths;
import model.store.CsvWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OutputFileManager {
    CsvWriter writer;

    public OutputFileManager() {
        writer = new CsvWriter();
        checkFolder();
    }

    public void checkFolder() {
        try {
            if (!Files.exists(CsvPaths.OUTPUT_DIR)) {
                Files.createDirectories(CsvPaths.OUTPUT_DIR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveNewReferral(Referral r) {
        ReferralManager.getInstance().addReferral(r);
        String email = ReferralManager.getInstance().generateReferralEmail(r);
        
        Path path = CsvPaths.OUTPUT_DIR.resolve("referrals_output.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile(), true));
            bw.write(email);
            bw.newLine();
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            writer.appendReferralToCsv(r, CsvPaths.REFERRALS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveNewPrescription(Prescription p) {
        String text = makePrescriptionText(p);
        Path path = CsvPaths.OUTPUT_DIR.resolve("prescriptions_output.txt");
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path.toFile(), true));
            bw.write(text);
            bw.newLine();
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            writer.appendPrescriptionToCsv(p, CsvPaths.PRESCRIPTIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makePrescriptionText(Prescription p) {
        String s = "";
        s += "========================================\n";
        s += "PRESCRIPTION\n";
        s += "========================================\n";
        s += "Prescription ID: " + p.getPrescriptionID() + "\n";
        s += "Date: " + p.getPrescriptionDate() + "\n\n";
        s += "Patient ID: " + p.getPatientID() + "\n";
        s += "Clinician ID: " + p.getClinicianID() + "\n\n";
        s += "Medication: " + p.getMedicationName() + "\n";
        s += "Dosage: " + p.getDosage() + "\n";
        s += "Frequency: " + p.getFrequency() + "\n";
        s += "Duration: " + p.getDurationDays() + " days\n";
        s += "Quantity: " + p.getQuantity() + "\n";
        s += "Instructions: " + p.getInstructions() + "\n\n";
        s += "Status: " + p.getStatus() + "\n";
        s += "========================================";
        return s;
    }
}
