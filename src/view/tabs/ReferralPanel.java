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

        JButton deleteButton = new JButton("Delete Referral");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this referral?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.getReferrals().remove(selectedRow);
                    model.setReferrals(controller.getReferrals());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a referral to delete.");
            }
        });

        actions.add(reload);
        actions.add(export);
        actions.add(addButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
