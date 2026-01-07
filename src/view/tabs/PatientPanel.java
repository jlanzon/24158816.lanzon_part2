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

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getPatients().remove(selectedRow); // Ideally should be by ID, but list index works for simple view
                    model.setPatients(controller.getPatients());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
            }
        });

        actions.add(reload);
        actions.add(addButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
