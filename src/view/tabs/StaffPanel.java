package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class StaffPanel extends JPanel {

    private final controller.MainController controller;
    private final StaffTableModel model;

    public StaffPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new StaffTableModel(controller.getStaff());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setStaffList(controller.getStaff());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
