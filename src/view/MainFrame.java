package view;

import model.store.DataRepository;
import view.tabs.*;

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
        tabs.addTab("Clinicians", new ClinicianPanel(repo));
        tabs.addTab("Staff", new StaffPanel(repo));
        tabs.addTab("Facilities", new FacilityPanel(repo));
        tabs.addTab("Appointments", new AppointmentPanel(repo));
        tabs.addTab("Prescriptions", new PrescriptionPanel(repo));
        tabs.addTab("Referrals", new ReferralPanel(repo));

        add(tabs, BorderLayout.CENTER);
    }
}
