package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class StaffPanel extends JPanel {

    private final DataRepository repo;
    private final StaffTableModel model;

    public StaffPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new StaffTableModel(repo.getStaffList());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setStaffList(repo.getStaffList());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
