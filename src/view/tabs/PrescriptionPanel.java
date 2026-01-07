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
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        JButton editButton = new JButton("Edit Prescription");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.domain.Prescription selected = controller.getPrescriptions().get(selectedRow);
                view.dialogs.PrescriptionDialog dialog = new view.dialogs.PrescriptionDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller, selected);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    model.setPrescriptions(controller.getPrescriptions());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Prescription");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this prescription?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.domain.Prescription selected = controller.getPrescriptions().get(selectedRow);
                    controller.deletePrescription(selected.getPrescriptionID());
                    model.setPrescriptions(controller.getPrescriptions());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            editButton.setEnabled(rowSelected);
            deleteButton.setEnabled(rowSelected);
        });

        actions.add(reload);
        actions.add(export);
        actions.add(addButton);
        actions.add(editButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
