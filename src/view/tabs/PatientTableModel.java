package view.tabs;

import model.domain.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PatientTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "Patient ID", "NHS Number", "First Name", "Last Name", "DOB", "Gender", 
        "Phone", "Email", "Address", "Postcode", "GP Surgery" 
    };
    private List<Patient> patients;

    public PatientTableModel(List<Patient> patients) {
        this.patients = patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        fireTableDataChanged(); 
    }

    @Override
    public int getRowCount() {
        return patients == null ? 0 : patients.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient p = patients.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getPatientId();
            case 1 -> p.getNhsNumber();
            case 2 -> p.getFirstName();
            case 3 -> p.getLastName();
            case 4 -> p.getDateOfBirth();
            case 5 -> p.getGender();
            case 6 -> p.getPhoneNumber();
            case 7 -> p.getEmail();
            case 8 -> p.getAddress();
            case 9 -> p.getPostcode();
            case 10 -> p.getGpSurgeryID();
            default -> "";
        };
    }
}
