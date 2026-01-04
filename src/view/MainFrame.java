package view;

import model.store.DataRepository;
import view.tabs.PatientPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(DataRepository repo) {
        setTitle("24158816.Lanzon Part 2");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", new PatientPanel(repo));
        tabs.addTab("Clinicians", placeholder("TODO later"));
        tabs.addTab("Appointments",placeholder("TODO later"));
        tabs.addTab("Prescriptions", placeholder("TODO later"));
        tabs.addTab("Referrals", placeholder("TODO later"));

        add(tabs, BorderLayout.CENTER);
    }

    private JPanel placeholder(String text) {
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(text, SwingConstants.CENTER), BorderLayout.CENTER);
        return p;
    }
}
