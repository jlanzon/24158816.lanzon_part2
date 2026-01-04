package view.tabs;

import model.domain.Facility;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FacilityTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "Name", "Type", "Address", "Phone", "Manager", "Capacity" 
    };
    private List<Facility> facilities;

    public FacilityTableModel(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return facilities == null ? 0 : facilities.size();
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
        Facility f = facilities.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> f.getFacilityID();
            case 1 -> f.getFacilityName();
            case 2 -> f.getFacilityType();
            case 3 -> f.getAddress();
            case 4 -> f.getPhoneNumber();
            case 5 -> f.getManagerName();
            case 6 -> f.getCapacity();
            default -> "";
        };
    }
}
