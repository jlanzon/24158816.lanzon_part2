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
    private java.util.function.Consumer<String> statusListener;

    public MainController(DataRepository r) {
        this.repo = r;
        this.refManager = ReferralManager.getInstance();
        this.outManager = new OutputFileManager();
        this.writer = new CsvWriter();
    }

    public void setStatusListener(java.util.function.Consumer<String> listener) {
        this.statusListener = listener;
    }

    private void updateStatus(String message) {
        if (statusListener != null) {
            statusListener.accept(message);
        }
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
            updateStatus("Patient added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error adding patient: " + e.getMessage());
        }
    }

    public void updatePatient(Patient updated) {
        List<Patient> patients = repo.getPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getPatientId().equals(updated.getPatientId())) {
                patients.set(i, updated);
                break;
            }
        }
        try {
            writer.saveAllPatients(patients, CsvPaths.PATIENTS);
            updateStatus("Patient updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error updating patient: " + e.getMessage());
        }
    }

    public void addPrescription(Prescription p) {
        repo.getPrescriptions().add(p);
        outManager.saveNewPrescription(p);
        updateStatus("Prescription added and saved.");
    }

    public void addReferral(Referral r) {
        repo.getReferrals().add(r);
        outManager.saveNewReferral(r);
        updateStatus("Referral added and saved.");
    }

    public void updatePrescription(Prescription updated) {
        List<Prescription> list = repo.getPrescriptions();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrescriptionID().equals(updated.getPrescriptionID())) {
                list.set(i, updated);
                break;
            }
        }
        try {
            writer.saveAllPrescriptions(list, CsvPaths.PRESCRIPTIONS);
            updateStatus("Prescription updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error updating prescription: " + e.getMessage());
        }
    }

    public void updateReferral(Referral updated) {
        List<Referral> list = repo.getReferrals();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getReferralID().equals(updated.getReferralID())) {
                list.set(i, updated);
                break;
            }
        }
        try {
            writer.saveAllReferrals(list, CsvPaths.REFERRALS);
            updateStatus("Referral updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error updating referral: " + e.getMessage());
        }
    }

    public void exportReferrals() {
        outManager.exportAllReferrals(repo.getReferrals());
        updateStatus("Referrals exported.");
    }

    public void exportPrescriptions() {
        outManager.exportAllPrescriptions(repo.getPrescriptions());
        updateStatus("Prescriptions exported.");
    }

    public void addAppointment(Appointment a) {
        repo.getAppointments().add(a);
        try {
            writer.appendAppointmentToCsv(a, CsvPaths.APPOINTMENTS);
            updateStatus("Appointment added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error adding appointment: " + e.getMessage());
        }
    }

    public void updateAppointment(Appointment updated) {
        List<Appointment> list = repo.getAppointments();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAppointmentID().equals(updated.getAppointmentID())) {
                list.set(i, updated);
                break;
            }
        }
        try {
            writer.saveAllAppointments(list, CsvPaths.APPOINTMENTS);
            updateStatus("Appointment updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error updating appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(String id) {
        List<Appointment> list = repo.getAppointments();
        list.removeIf(a -> a.getAppointmentID().equals(id));
        try {
            writer.saveAllAppointments(list, CsvPaths.APPOINTMENTS);
            updateStatus("Appointment deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error deleting appointment: " + e.getMessage());
        }
    }

    public void addClinician(Clinician c) {
        repo.getClinicians().add(c);
        try {
            writer.appendClinicianToCsv(c, CsvPaths.CLINICIANS);
            updateStatus("Clinician added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error adding clinician: " + e.getMessage());
        }
    }

    public void updateClinician(Clinician updated) {
        List<Clinician> list = repo.getClinicians();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClinicianID().equals(updated.getClinicianID())) {
                list.set(i, updated);
                break;
            }
        }
        try {
            writer.saveAllClinicians(list, CsvPaths.CLINICIANS);
            updateStatus("Clinician updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error updating clinician: " + e.getMessage());
        }
    }

    public void deleteClinician(String id) {
        List<Clinician> list = repo.getClinicians();
        list.removeIf(c -> c.getClinicianID().equals(id));
        try {
            writer.saveAllClinicians(list, CsvPaths.CLINICIANS);
            updateStatus("Clinician deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            updateStatus("Error deleting clinician: " + e.getMessage());
        }
    }
}
