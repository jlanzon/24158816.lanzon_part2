package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class PrescriptionPanel extends JPanel {

    private final DataRepository repo;
    private final PrescriptionTableModel model;

    public PrescriptionPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new PrescriptionTableModel(repo.getPrescriptions());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setPrescriptions(repo.getPrescriptions());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
