package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class AppointmentPanel extends JPanel {

    private final DataRepository repo;
    private final AppointmentTableModel model;

    public AppointmentPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new AppointmentTableModel(repo.getAppointments());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setAppointments(repo.getAppointments());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
