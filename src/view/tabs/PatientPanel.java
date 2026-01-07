package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private final controller.MainController controller;
    private final PatientTableModel model;

    public PatientPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new PatientTableModel(controller.getPatients());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setPatients(controller.getPatients());
        });

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> {
            view.dialogs.PatientDialog dialog = new view.dialogs.PatientDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller);
            dialog.setVisible(true);
            if (dialog.getResult() != null) {
                model.setPatients(controller.getPatients());
            }
        });

        JButton editButton = new JButton("Edit Patient");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.domain.Patient selectedPatient = controller.getPatients().get(selectedRow);
                view.dialogs.PatientDialog dialog = new view.dialogs.PatientDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller, selectedPatient);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    model.setPatients(controller.getPatients());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getPatients().remove(selectedRow);
                    model.setPatients(controller.getPatients());
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
