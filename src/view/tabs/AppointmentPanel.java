package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class AppointmentPanel extends JPanel {

    private final controller.MainController controller;
    private final AppointmentTableModel model;

    public AppointmentPanel(controller.MainController controller) {
        this.controller = controller;
        this.model = new AppointmentTableModel(controller.getAppointments());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setAppointments(controller.getAppointments());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
