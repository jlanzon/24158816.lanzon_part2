package controller;

import model.domain.*;
import model.service.OutputFileManager;
import model.service.ReferralManager;
import model.store.CsvPaths;
import model.store.CsvWriter;
import model.store.DataRepository;
import java.util.List;

public class MainController {
    DataRepository repo;
    ReferralManager refManager;
    OutputFileManager outManager;
    CsvWriter writer;

    public MainController(DataRepository r) {
        this.repo = r;
        this.refManager = ReferralManager.getInstance();
        this.outManager = new OutputFileManager();
        this.writer = new CsvWriter();
    }

    public void loadAllData() {
        repo.loadAll();
    }

    public List<Patient> getPatients() {
        return repo.getPatients();
    }

    public List<Clinician> getClinicians() {
        return repo.getClinicians();
    }

    public List<Staff> getStaff() {
        return repo.getStaffList();
    }

    public List<Facility> getFacilities() {
        return repo.getFacilities();
    }

    public List<Appointment> getAppointments() {
        return repo.getAppointments();
    }

    public List<Prescription> getPrescriptions() {
        return repo.getPrescriptions();
    }

    public List<Referral> getReferrals() {
        return repo.getReferrals();
    }

    public void addPatient(Patient p) {
        repo.getPatients().add(p);
        try {
            writer.appendPatientToCsv(p, CsvPaths.PATIENTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPrescription(Prescription p) {
        repo.getPrescriptions().add(p);
        outManager.saveNewPrescription(p);
    }

    public void addReferral(Referral r) {
        repo.getReferrals().add(r);
        outManager.saveNewReferral(r);
    }

    public void exportReferrals() {
        outManager.exportAllReferrals(repo.getReferrals());
    }

    public void exportPrescriptions() {
        outManager.exportAllPrescriptions(repo.getPrescriptions());
    }
}
