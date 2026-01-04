package model.store;

import java.nio.file.Path;

public final class CsvPaths {
    private CsvPaths() {}

    public static final Path DATA_DIR = Path.of("data");
    public static final Path OUTPUT_DIR = Path.of("output");

    public static final Path PATIENTS = DATA_DIR.resolve("patients.csv");
    public static final Path CLINICIANS = DATA_DIR.resolve("clinicians.csv");
    public static final Path FACILITIES = DATA_DIR.resolve("facilities.csv");
    public static final Path APPOINTMENTS = DATA_DIR.resolve("appointments.csv");
    public static final Path PRESCRIPTIONS = DATA_DIR.resolve("prescriptions.csv");
    public static final Path REFERRALS = DATA_DIR.resolve("referrals.csv");
    public static final Path STAFF = DATA_DIR.resolve("staff.csv");
}
