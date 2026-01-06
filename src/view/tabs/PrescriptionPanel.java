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

        actions.add(reload);
        actions.add(export);
        add(actions, BorderLayout.NORTH);
    }
}
