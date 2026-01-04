package view.tabs;

import model.domain.Referral;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ReferralTableModel extends AbstractTableModel {

    private final String[] columns = { 
        "ID", "Patient ID", "From Clinician", "To Clinician", "Date", "Urgency", "Status" 
    };
    private List<Referral> referrals;

    public ReferralTableModel(List<Referral> referrals) {
        this.referrals = referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return referrals == null ? 0 : referrals.size();
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
        Referral r = referrals.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> r.getReferralID();
            case 1 -> r.getPatientID();
            case 2 -> r.getReferringClinicianID();
            case 3 -> r.getReferredToClinicianID();
            case 4 -> r.getReferralDate();
            case 5 -> r.getUrgencyLevel();
            case 6 -> r.getStatus();
            default -> "";
        };
    }
}
