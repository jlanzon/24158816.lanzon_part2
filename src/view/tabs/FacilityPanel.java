package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class FacilityPanel extends JPanel {

    private final controller.MainController controller;
    private final FacilityTableModel model;

    public FacilityPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new FacilityTableModel(controller.getFacilities());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setFacilities(controller.getFacilities());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
