package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class PrescriptionPanel extends JPanel {

    private final controller.MainController controller;
    private final PrescriptionTableModel model;

    public PrescriptionPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new PrescriptionTableModel(controller.getPrescriptions());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setPrescriptions(controller.getPrescriptions());
        });

        JButton export = new JButton("Export");
        export.addActionListener(e -> {
            controller.exportPrescriptions();
            JOptionPane.showMessageDialog(this, "Exported to output/prescriptions_export.txt");
        });

        JButton addButton = new JButton("Add Prescription");
        addButton.addActionListener(e -> {
            view.dialogs.PrescriptionDialog dialog = new view.dialogs.PrescriptionDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller);
            dialog.setVisible(true);
            if (dialog.getResult() != null) {
                model.setPrescriptions(controller.getPrescriptions());
            }
        });

        JButton deleteButton = new JButton("Delete Prescription");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this prescription?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getPrescriptions().remove(selectedRow);
                    model.setPrescriptions(controller.getPrescriptions());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a prescription to delete.");
            }
        });

        actions.add(reload);
        actions.add(export);
        actions.add(addButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
