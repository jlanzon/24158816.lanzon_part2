package view.tabs;

import model.store.DataRepository;

import javax.swing.*;
import java.awt.*;

public class ReferralPanel extends JPanel {

    private final DataRepository repo;
    private final ReferralTableModel model;

    public ReferralPanel(DataRepository repo) {
        this.repo = repo;
        this.model = new ReferralTableModel(repo.getReferrals());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            repo.loadAll();
            model.setReferrals(repo.getReferrals());
        });

        actions.add(reload);
        add(actions, BorderLayout.NORTH);
    }
}
