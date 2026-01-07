package view.tabs;



import javax.swing.*;
import java.awt.*;

public class ClinicianPanel extends JPanel {

    private final ClinicianTableModel model;

    public ClinicianPanel(controller.MainController controller) {
        this.model = new ClinicianTableModel(controller.getClinicians());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setClinicians(controller.getClinicians());
        });

        JButton addButton = new JButton("Add Clinician");
        addButton.addActionListener(e -> {
            view.dialogs.ClinicianDialog dialog = new view.dialogs.ClinicianDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller);
            dialog.setVisible(true);
            if (dialog.getResult() != null) {
                model.setClinicians(controller.getClinicians());
            }
        });

        JButton editButton = new JButton("Edit Clinician");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.domain.Clinician selected = controller.getClinicians().get(selectedRow);
                view.dialogs.ClinicianDialog dialog = new view.dialogs.ClinicianDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller, selected);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    model.setClinicians(controller.getClinicians());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Clinician");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this clinician?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteClinician(controller.getClinicians().get(selectedRow).getClinicianID());
                    model.setClinicians(controller.getClinicians());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            editButton.setEnabled(rowSelected);
            deleteButton.setEnabled(rowSelected);
        });

        actions.add(reload);
        actions.add(addButton);
        actions.add(editButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
