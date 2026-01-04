package view.tabs;

import model.domain.Appointment;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AppointmentTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "Patient ID", "Clinician ID", "Date", "Time", "Type", "Status", "Reason" 
    };
    private List<Appointment> appointments;

    public AppointmentTableModel(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return appointments == null ? 0 : appointments.size();
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
        Appointment a = appointments.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> a.getAppointmentID();
            case 1 -> a.getPatientID();
            case 2 -> a.getClinicianID();
            case 3 -> a.getAppointmentDate();
            case 4 -> a.getAppointmentTime();
            case 5 -> a.getAppointmentType();
            case 6 -> a.getStatus();
            case 7 -> a.getReasonForVisit();
            default -> "";
        };
    }
}
