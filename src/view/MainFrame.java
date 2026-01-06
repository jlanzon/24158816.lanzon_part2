package view;

import model.store.DataRepository;
import view.tabs.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(controller.MainController controller) {
        setTitle("24158816.Lanzon Part 2");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", new PatientPanel(controller));
        tabs.addTab("Clinicians", new ClinicianPanel(controller));
        tabs.addTab("Staff", new StaffPanel(controller));
        tabs.addTab("Facilities", new FacilityPanel(controller));
        tabs.addTab("Appointments", new AppointmentPanel(controller));
        tabs.addTab("Prescriptions", new PrescriptionPanel(controller));
        tabs.addTab("Referrals", new ReferralPanel(controller));

        add(tabs, BorderLayout.CENTER);
    }
}
