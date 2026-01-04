package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private final DataRepository repo;
    private final PatientTableModel model;

    public PatientPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new PatientTableModel(repo.getPatients());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setPatients(repo.getPatients());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
