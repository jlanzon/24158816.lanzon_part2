package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class ReferralPanel extends JPanel {

    private final controller.MainController controller;
    private final ReferralTableModel model;

    public ReferralPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new ReferralTableModel(controller.getReferrals());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setReferrals(controller.getReferrals());
        });

        JButton export = new JButton("Export");
        export.addActionListener(e -> {
            controller.exportReferrals();
            JOptionPane.showMessageDialog(this, "Exported to output/referrals_export.txt");
        });

        JButton addButton = new JButton("Add Referral");
        addButton.addActionListener(e -> {
            view.dialogs.ReferralDialog dialog = new view.dialogs.ReferralDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller);
            dialog.setVisible(true);
            if (dialog.getResult() != null) {
                model.setReferrals(controller.getReferrals());
            }
        });

        JButton editButton = new JButton("Edit Referral");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.domain.Referral selected = controller.getReferrals().get(selectedRow);
                view.dialogs.ReferralDialog dialog = new view.dialogs.ReferralDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller, selected);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    model.setReferrals(controller.getReferrals());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Referral");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this referral?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.domain.Referral selected = controller.getReferrals().get(selectedRow);
                    controller.deleteReferral(selected.getReferralID());
                    model.setReferrals(controller.getReferrals());
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
