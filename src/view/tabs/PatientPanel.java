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

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
