package view.tabs;

import model.domain.Clinician;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClinicianTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "First Name", "Last Name", "Role", "Specialty", "Workplace", "Phone", "Email" 
    };
    private List<Clinician> clinicians;

    public ClinicianTableModel(List<Clinician> clinicians) {
        this.clinicians = clinicians;
    }

    public void setClinicians(List<Clinician> clinicians) {
        this.clinicians = clinicians;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clinicians == null ? 0 : clinicians.size();
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
        Clinician c = clinicians.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getClinicianID();
            case 1 -> c.getFirstName();
            case 2 -> c.getLastName();
            case 3 -> c.getRole();
            case 4 -> c.getSpecialty();
            case 5 -> c.getWorkplace();
            case 6 -> c.getPhoneNumber();
            case 7 -> c.getEmail();
            default -> "";
        };
    }
}
