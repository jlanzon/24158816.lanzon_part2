package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class ClinicianPanel extends JPanel {

    private final DataRepository repo;
    private final ClinicianTableModel model;

    public ClinicianPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new ClinicianTableModel(repo.getClinicians());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setClinicians(repo.getClinicians());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
