package view;

import controller.MainController;
import view.tabs.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(MainController controller) {
        super("24158816.Lanzon Part 2 - Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);        

        JTabbedPane tabbedPane = new JTabbedPane();

        
        
        tabbedPane.addTab("Patients", new PatientPanel(controller));
        tabbedPane.addTab("Clinicians", new ClinicianPanel(controller));
        tabbedPane.addTab("Appointments", new AppointmentPanel(controller));
        tabbedPane.addTab("Prescriptions", new PrescriptionPanel(controller));
        tabbedPane.addTab("Referrals", new ReferralPanel(controller));
        
        add(tabbedPane, BorderLayout.CENTER);
    }
}
