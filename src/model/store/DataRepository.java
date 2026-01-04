package model.store;

import model.domain.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataRepository {

    private final List<Patient> patients = new ArrayList<>();

    public void loadAll() {
        patients.clear();

        try {
            ensureFolders();
            loadPatients();
            System.out.println("Loaded patients: " + patients.size());
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
            String patientId = CsvUtil.get(r, "patientId", "PatientId", "patient_id", "Patient ID", "id", "ID");
            String nhs = CsvUtil.get(r, "nhsNumber", "NHSNumber", "nhs_number", "NHS Number", "NHS");
            String first = CsvUtil.get(r, "firstName", "FirstName", "first_name", "First Name", "Forename");
            String last = CsvUtil.get(r, "lastName", "LastName", "last_name", "Last Name", "Surname");

            String noReadStr = CsvUtil.get(r, "noRead", "no_read", "NoRead");
            boolean noRead = false;
            if (noReadStr != null && !noReadStr.isBlank()) {
                String v = noReadStr.trim();
                noRead = v.equalsIgnoreCase("true") || v.equals("1") || v.equalsIgnoreCase("yes");
            }

            if (patientId.isBlank()) patientId = nhs;
            if (!noRead) {
                patients.add(new Patient(patientId, nhs, first, last, noRead));
            }
        }
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
