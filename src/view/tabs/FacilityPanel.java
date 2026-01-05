package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class FacilityPanel extends JPanel {

    private final DataRepository repo;
    private final FacilityTableModel model;

    public FacilityPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new FacilityTableModel(repo.getFacilities());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setFacilities(repo.getFacilities());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
