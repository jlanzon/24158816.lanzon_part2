package view.tabs;

import model.domain.Prescription;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PrescriptionTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "Patient ID", "Medication", "Dosage", "Frequency", "Status", "Date" 
    };
    private List<Prescription> prescriptions;

    public PrescriptionTableModel(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return prescriptions == null ? 0 : prescriptions.size();
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
        Prescription p = prescriptions.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getPrescriptionID();
            case 1 -> p.getPatientID();
            case 2 -> p.getMedicationName();
            case 3 -> p.getDosage();
            case 4 -> p.getFrequency();
            case 5 -> p.getStatus();
            case 6 -> p.getPrescriptionDate();
            default -> "";
        };
    }
}
