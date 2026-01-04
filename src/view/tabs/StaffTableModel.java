package view.tabs;

import model.domain.Staff;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StaffTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "First Name", "Last Name", "Role", "Department", "Facility ID", "Phone", "Email" 
    };
    private List<Staff> staffList;

    public StaffTableModel(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return staffList == null ? 0 : staffList.size();
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
        Staff s = staffList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> s.getStaffID();
            case 1 -> s.getFirstName();
            case 2 -> s.getLastName();
            case 3 -> s.getRole();
            case 4 -> s.getDepartment();
            case 5 -> s.getFacilityID();
            case 6 -> s.getPhoneNumber();
            case 7 -> s.getEmail();
            default -> "";
        };
    }
}
