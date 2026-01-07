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
    private Referral existingReferral = null;

    public ReferralDialog(JFrame parent, MainController controller) {
        this(parent, controller, null);
    }

    public ReferralDialog(JFrame parent, MainController controller, Referral existingReferral) {
        super(parent, existingReferral == null ? "Add Referral" : "Edit Referral", true);
        this.controller = controller;
        this.existingReferral = existingReferral;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));

        formPanel.add(new JLabel("Referral ID:"));
        referralIdField = new JTextField();
        if (existingReferral != null) {
            referralIdField.setText(existingReferral.getReferralID());
            referralIdField.setEditable(false);
        }
        formPanel.add(referralIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        if (existingReferral != null) patientIdField.setText(existingReferral.getPatientID());
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Referring Clinician ID:"));
        referringClinicianIdField = new JTextField();
        if (existingReferral != null) referringClinicianIdField.setText(existingReferral.getReferringClinicianID());
        formPanel.add(referringClinicianIdField);

        formPanel.add(new JLabel("Referred To Clinician ID:"));
        referredToClinicianIdField = new JTextField();
        if (existingReferral != null) referredToClinicianIdField.setText(existingReferral.getReferredToClinicianID());
        formPanel.add(referredToClinicianIdField);

        formPanel.add(new JLabel("Referring Facility ID:"));
        referringFacilityIdField = new JTextField();
        if (existingReferral != null) referringFacilityIdField.setText(existingReferral.getReferringFacilityID());
        formPanel.add(referringFacilityIdField);

        formPanel.add(new JLabel("Referred To Facility ID:"));
        referredToFacilityIdField = new JTextField();
        if (existingReferral != null) referredToFacilityIdField.setText(existingReferral.getReferredToFacilityID());
        formPanel.add(referredToFacilityIdField);

        formPanel.add(new JLabel("Urgency Level:"));
        urgencyLevelField = new JTextField();
        if (existingReferral != null) urgencyLevelField.setText(existingReferral.getUrgencyLevel());
        formPanel.add(urgencyLevelField);

        formPanel.add(new JLabel("Referral Reason:"));
        referralReasonField = new JTextField();
        if (existingReferral != null) referralReasonField.setText(existingReferral.getReferralReason());
        formPanel.add(referralReasonField);

        formPanel.add(new JLabel("Clinical Summary:"));
        clinicalSummaryField = new JTextField();
        if (existingReferral != null) clinicalSummaryField.setText(existingReferral.getClinicalSummary());
        formPanel.add(clinicalSummaryField);

        formPanel.add(new JLabel("Requested Investigations:"));
        requestedInvestigationsField = new JTextField();
        if (existingReferral != null) requestedInvestigationsField.setText(existingReferral.getRequestedInvestigations());
        formPanel.add(requestedInvestigationsField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        if (existingReferral != null) statusField.setText(existingReferral.getStatus());
        formPanel.add(statusField);

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        if (existingReferral != null) appointmentIdField.setText(existingReferral.getAppointmentID());
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Notes:"));
        notesField = new JTextField();
        if (existingReferral != null) notesField.setText(existingReferral.getNotes());
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
            LocalDate referralDate = existingReferral != null ? existingReferral.getReferralDate() : LocalDate.now();
            String urgencyLevel = urgencyLevelField.getText();
            String referralReason = referralReasonField.getText();
            String clinicalSummary = clinicalSummaryField.getText();
            String requestedInvestigations = requestedInvestigationsField.getText();
            String status = statusField.getText();
            String appointmentID = appointmentIdField.getText();
            String notes = notesField.getText();
            LocalDate createdDate = existingReferral != null ? existingReferral.getCreatedDate() : LocalDate.now();
            LocalDate lastUpdated = LocalDate.now();

            if (!validateFields()) {
                return;
            }

            result = new Referral(referralID, patientID, referringClinicianID, referredToClinicianID, referringFacilityID, referredToFacilityID, referralDate, urgencyLevel, referralReason, clinicalSummary, requestedInvestigations, status, appointmentID, notes, createdDate, lastUpdated);
            
            if (existingReferral != null) {
                controller.updateReferral(result);
            } else {
                controller.addReferral(result);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (referralIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Referral ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (patientIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Referral getResult() {
        return result;
    }
}
