package test;

import controller.MainController;
import model.domain.Patient;
import model.domain.Prescription;
import model.domain.Referral;
import model.store.DataRepository;

import java.time.LocalDate;

public class PersistenceTest {
    public static void main(String[] args) {
        System.out.println("Testing Persistence and Controller...");

        DataRepository repo = new DataRepository();
        MainController controller = new MainController(repo);
        
        Patient p = new Patient(
            "P9001", "9998887776", "Test", "Patient", 
            LocalDate.of(1990, 5, 20), "Female", "07700900999", "test.patient@example.com",
            "42 Wallaby Way", "SY2 3AB", "Emergency Contact", "07700900998", 
            LocalDate.now(), "F001"
        );
        controller.addPatient(p);
        System.out.println("Added Patient. Repo size: " + controller.getPatients().size());

        Prescription rx = new Prescription(
            "P9001", "P9001", "C001", "A0001",
            LocalDate.now(), "Amoxicillin", "500mg", "3x daily",
            7, 21, "Take with food", "St George Pharmacy",
            "Issued", LocalDate.now(), null
        );
        controller.addPrescription(rx);
        System.out.println("Added Prescription. Repo size: " + controller.getPrescriptions().size());

        Referral ref = new Referral(
            "R9001", "P9001", "C001", "C005",
            "F001", "F002", LocalDate.now(), "Routine",
            "Cardiology Consultation", "Patient reports palpitations", "ECG", "Sent",
            "A0001", "Referral for specialist opinion", LocalDate.now(), LocalDate.now()
        );
        controller.addReferral(ref);
        System.out.println("Added Referral. Repo size: " + controller.getReferrals().size());

        model.domain.Clinician c = new model.domain.Clinician(
            "C9001", "Dr", "Who", "Doctor", "PhD", "Time Travel", "TARDIS", "07700900990", "doctor@who.com",
            "Dr", "1234567", "Hospital", "Full Time", LocalDate.now()
        );
        controller.addClinician(c);
        System.out.println("Added Clinician. Repo size: " + controller.getClinicians().size());

        System.out.println("Check 'output' directory and 'data' CSV files for new records.");
    }
}
