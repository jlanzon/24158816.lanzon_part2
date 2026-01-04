package model.store;

import model.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataRepository {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Clinician> clinicians = new ArrayList<>();
    private final List<Staff> staffList = new ArrayList<>();
    private final List<Facility> facilities = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final List<Referral> referrals = new ArrayList<>();

    public void loadAll() {
        patients.clear();
        clinicians.clear();
        staffList.clear();
        facilities.clear();
        appointments.clear();
        prescriptions.clear();
        referrals.clear();

        try {
            ensureFolders();
            loadPatients();
            loadClinicians();
            loadStaff();
            loadFacilities();
            loadAppointments();
            loadPrescriptions();
            loadReferrals();
            
            System.out.println("Loaded patients: " + patients.size());
            System.out.println("Loaded clinicians: " + clinicians.size());
            System.out.println("Loaded staff: " + staffList.size());
            System.out.println("Loaded facilities: " + facilities.size());
            System.out.println("Loaded appointments: " + appointments.size());
            System.out.println("Loaded prescriptions: " + prescriptions.size());
            System.out.println("Loaded referrals: " + referrals.size());
            
        } catch (IOException e) {
            System.err.println("Load failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ensureFolders() throws IOException {
        if (!Files.exists(CsvPaths.OUTPUT_DIR)) {
            Files.createDirectories(CsvPaths.OUTPUT_DIR);
        }
    }

    private void loadPatients() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.PATIENTS);
        for (Map<String, String> r : rows) {
            String patientId = CsvUtil.get(r, "patientId", "PatientId", "id");
            String nhs = CsvUtil.get(r, "nhsNumber", "NHSNumber", "nhs");
            String first = CsvUtil.get(r, "firstName", "FirstName", "first_name");
            String last = CsvUtil.get(r, "lastName", "LastName", "last_name");
            LocalDate dob = parseDate(CsvUtil.get(r, "dateOfBirth", "dob", "DateOfBirth"));
            String gender = CsvUtil.get(r, "gender", "Gender");
            String phone = CsvUtil.get(r, "phoneNumber", "phone", "Phone");
            String email = CsvUtil.get(r, "email", "Email");
            String address = CsvUtil.get(r, "address", "Address");
            String postcode = CsvUtil.get(r, "postcode", "Postcode");
            String emergName = CsvUtil.get(r, "emergencyContactName", "emergency_contact_name");
            String emergPhone = CsvUtil.get(r, "emergencyContactPhone", "emergency_contact_phone");
            LocalDate regDate = parseDate(CsvUtil.get(r, "registrationDate", "registration_date"));
            String gpId = CsvUtil.get(r, "gpSurgeryID", "gp_surgery_id");

            if (patientId.isBlank()) patientId = nhs; 
            if (!patientId.isBlank()) {
                patients.add(new Patient(patientId, nhs, first, last, dob, gender, phone, email, 
                                         address, postcode, emergName, emergPhone, regDate, gpId));
            }
        }
    }

    private void loadClinicians() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.CLINICIANS);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "clinicianID", "id");
            String first = CsvUtil.get(r, "firstName", "first_name");
            String last = CsvUtil.get(r, "lastName", "last_name");
            String role = CsvUtil.get(r, "role");
            String qual = CsvUtil.get(r, "qualification");
            String spec = CsvUtil.get(r, "specialty");
            String work = CsvUtil.get(r, "workplace");
            String phone = CsvUtil.get(r, "phoneNumber", "phone");
            String email = CsvUtil.get(r, "email");

            if (!id.isBlank()) {
                clinicians.add(new Clinician(id, first, last, role, qual, spec, work, phone, email));
            }
        }
    }

    private void loadStaff() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.STAFF);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "staffID", "id");
            String first = CsvUtil.get(r, "firstName", "first_name");
            String last = CsvUtil.get(r, "lastName", "last_name");
            String role = CsvUtil.get(r, "role");
            String dept = CsvUtil.get(r, "department");
            String facId = CsvUtil.get(r, "facilityID", "facility_id");
            String phone = CsvUtil.get(r, "phoneNumber", "phone");
            String email = CsvUtil.get(r, "email");
            String status = CsvUtil.get(r, "employmentStatus", "status");
            LocalDate start = parseDate(CsvUtil.get(r, "startDate", "start_date"));
            String manager = CsvUtil.get(r, "lineManager", "line_manager");
            String access = CsvUtil.get(r, "accessLevel", "access_level");

            if (!id.isBlank()) {
                staffList.add(new Staff(id, first, last, role, dept, facId, phone, email, status, start, manager, access));
            }
        }
    }

    private void loadFacilities() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.FACILITIES);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "facilityID", "id");
            String name = CsvUtil.get(r, "facilityName", "name");
            String type = CsvUtil.get(r, "facilityType", "type");
            String addr = CsvUtil.get(r, "address");
            String post = CsvUtil.get(r, "postcode");
            String phone = CsvUtil.get(r, "phoneNumber", "phone");
            String email = CsvUtil.get(r, "email");
            String hours = CsvUtil.get(r, "openingHours", "hours");
            String manager = CsvUtil.get(r, "managerName", "manager");
            int cap = parseInt(CsvUtil.get(r, "capacity"));
            String specs = CsvUtil.get(r, "specialitiesOffered", "specialities");

            if (!id.isBlank()) {
                facilities.add(new Facility(id, name, type, addr, post, phone, email, hours, manager, cap, specs));
            }
        }
    }

    private void loadAppointments() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.APPOINTMENTS);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "appointmentID", "id");
            String patId = CsvUtil.get(r, "patientID", "patient_id");
            String clinId = CsvUtil.get(r, "clinicianID", "clinician_id");
            String facId = CsvUtil.get(r, "facilityID", "facility_id");
            LocalDate date = parseDate(CsvUtil.get(r, "appointmentDate", "date"));
            LocalTime time = parseTime(CsvUtil.get(r, "appointmentTime", "time"));
            int dur = parseInt(CsvUtil.get(r, "durationMinutes", "duration"));
            String type = CsvUtil.get(r, "appointmentType", "type");
            String status = CsvUtil.get(r, "status");
            String reason = CsvUtil.get(r, "reasonForVisit", "reason");
            String notes = CsvUtil.get(r, "notes");
            LocalDate created = parseDate(CsvUtil.get(r, "createdDate", "created"));
            LocalDate modified = parseDate(CsvUtil.get(r, "lastModified", "modified"));

            if (!id.isBlank()) {
                appointments.add(new Appointment(id, patId, clinId, facId, date, time, dur, type, status, reason, notes, created, modified));
            }
        }
    }

    private void loadPrescriptions() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.PRESCRIPTIONS);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "prescriptionID", "id");
            String patId = CsvUtil.get(r, "patientID", "patient_id");
            String clinId = CsvUtil.get(r, "clinicianID", "clinician_id");
            String appId = CsvUtil.get(r, "appointmentID", "appointment_id");
            LocalDate date = parseDate(CsvUtil.get(r, "prescriptionDate", "date"));
            String med = CsvUtil.get(r, "medicationName", "medication");
            String dos = CsvUtil.get(r, "dosage");
            String freq = CsvUtil.get(r, "frequency");
            int dur = parseInt(CsvUtil.get(r, "durationDays", "duration"));
            int qty = parseInt(CsvUtil.get(r, "quantity"));
            String instr = CsvUtil.get(r, "instructions");
            String pharm = CsvUtil.get(r, "pharmacyName", "pharmacy");
            String status = CsvUtil.get(r, "status");
            LocalDate issue = parseDate(CsvUtil.get(r, "issueDate", "issue_date"));
            LocalDate coll = parseDate(CsvUtil.get(r, "collectionDate", "collection_date"));

            if (!id.isBlank()) {
                prescriptions.add(new Prescription(id, patId, clinId, appId, date, med, dos, freq, dur, qty, instr, pharm, status, issue, coll));
            }
        }
    }

    private void loadReferrals() throws IOException {
        List<Map<String, String>> rows = CsvReader.readAllAsMaps(CsvPaths.REFERRALS);
        for (Map<String, String> r : rows) {
            String id = CsvUtil.get(r, "referralID", "id");
            String patId = CsvUtil.get(r, "patientID", "patient_id");
            String refClinId = CsvUtil.get(r, "referringClinicianID", "referring_clinician_id");
            String toClinId = CsvUtil.get(r, "referredToClinicianID", "referred_to_clinician_id");
            String refFacId = CsvUtil.get(r, "referringFacilityID", "referring_facility_id");
            String toFacId = CsvUtil.get(r, "referredToFacilityID", "referred_to_facility_id");
            LocalDate date = parseDate(CsvUtil.get(r, "referralDate", "date"));
            String urgency = CsvUtil.get(r, "urgencyLevel", "urgency");
            String reason = CsvUtil.get(r, "referralReason", "reason");
            String summary = CsvUtil.get(r, "clinicalSummary", "summary");
            String inv = CsvUtil.get(r, "requestedInvestigations", "investigations");
            String status = CsvUtil.get(r, "status");
            String appId = CsvUtil.get(r, "appointmentID", "appointment_id");
            String notes = CsvUtil.get(r, "notes");
            LocalDate created = parseDate(CsvUtil.get(r, "createdDate", "created"));
            LocalDate updated = parseDate(CsvUtil.get(r, "lastUpdated", "updated"));

            if (!id.isBlank()) {
                referrals.add(new Referral(id, patId, refClinId, toClinId, refFacId, toFacId, date, urgency, reason, summary, inv, status, appId, notes, created, updated));
            }
        }
    }

    private LocalDate parseDate(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return LocalDate.parse(s.trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalTime parseTime(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return LocalTime.parse(s.trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private int parseInt(String s) {
        if (s == null || s.isBlank()) return 0;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public List<Patient> getPatients() { return patients; }
    public List<Clinician> getClinicians() { return clinicians; }
    public List<Staff> getStaffList() { return staffList; }
    public List<Facility> getFacilities() { return facilities; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public List<Referral> getReferrals() { return referrals; }
}
