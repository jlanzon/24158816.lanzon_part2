package view.dialogs;

import controller.MainController;
import model.domain.Referral;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ReferralDialog extends JDialog {
    private JTextField referralIdField;
    private JTextField patientIdField;
    private JTextField referringClinicianIdField;
    private JTextField referredToClinicianIdField;
    private JTextField referringFacilityIdField;
    private JTextField referredToFacilityIdField;
    private JTextField urgencyLevelField;
    private JTextField referralReasonField;
    private JTextField clinicalSummaryField;
    private JTextField requestedInvestigationsField;
    private JTextField statusField;
    private JTextField appointmentIdField;
    private JTextField notesField;

    private Referral result = null;
    private final MainController controller;

    public ReferralDialog(JFrame parent, MainController controller) {
        super(parent, "Add Referral", true);
        this.controller = controller;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));

        formPanel.add(new JLabel("Referral ID:"));
        referralIdField = new JTextField();
        formPanel.add(referralIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Referring Clinician ID:"));
        referringClinicianIdField = new JTextField();
        formPanel.add(referringClinicianIdField);

        formPanel.add(new JLabel("Referred To Clinician ID:"));
        referredToClinicianIdField = new JTextField();
        formPanel.add(referredToClinicianIdField);

        formPanel.add(new JLabel("Referring Facility ID:"));
        referringFacilityIdField = new JTextField();
        formPanel.add(referringFacilityIdField);

        formPanel.add(new JLabel("Referred To Facility ID:"));
        referredToFacilityIdField = new JTextField();
        formPanel.add(referredToFacilityIdField);

        formPanel.add(new JLabel("Urgency Level:"));
        urgencyLevelField = new JTextField();
        formPanel.add(urgencyLevelField);

        formPanel.add(new JLabel("Referral Reason:"));
        referralReasonField = new JTextField();
        formPanel.add(referralReasonField);

        formPanel.add(new JLabel("Clinical Summary:"));
        clinicalSummaryField = new JTextField();
        formPanel.add(clinicalSummaryField);

        formPanel.add(new JLabel("Requested Investigations:"));
        requestedInvestigationsField = new JTextField();
        formPanel.add(requestedInvestigationsField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        formPanel.add(statusField);

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Notes:"));
        notesField = new JTextField();
        formPanel.add(notesField);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOK());
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 600);
        setLocationRelativeTo(parent);
    }

    private void onOK() {
        try {
            String referralID = referralIdField.getText();
            String patientID = patientIdField.getText();
            String referringClinicianID = referringClinicianIdField.getText();
            String referredToClinicianID = referredToClinicianIdField.getText();
            String referringFacilityID = referringFacilityIdField.getText();
            String referredToFacilityID = referredToFacilityIdField.getText();
            LocalDate referralDate = LocalDate.now();
            String urgencyLevel = urgencyLevelField.getText();
            String referralReason = referralReasonField.getText();
            String clinicalSummary = clinicalSummaryField.getText();
            String requestedInvestigations = requestedInvestigationsField.getText();
            String status = statusField.getText();
            String appointmentID = appointmentIdField.getText();
            String notes = notesField.getText();
            LocalDate createdDate = LocalDate.now();
            LocalDate lastUpdated = LocalDate.now();

            result = new Referral(referralID, patientID, referringClinicianID, referredToClinicianID, referringFacilityID, referredToFacilityID, referralDate, urgencyLevel, referralReason, clinicalSummary, requestedInvestigations, status, appointmentID, notes, createdDate, lastUpdated);
            controller.addReferral(result);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Referral getResult() {
        return result;
    }
}
