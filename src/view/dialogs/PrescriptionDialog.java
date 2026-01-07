package view.dialogs;

import controller.MainController;
import model.domain.Prescription;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class PrescriptionDialog extends JDialog {
    private JTextField prescriptionIdField;
    private JTextField patientIdField;
    private JTextField clinicianIdField;
    private JTextField appointmentIdField;
    private JTextField medicationNameField;
    private JTextField dosageField;
    private JTextField frequencyField;
    private JTextField durationDaysField;
    private JTextField quantityField;
    private JTextField instructionsField;
    private JTextField pharmacyNameField;
    private JTextField statusField;

    private Prescription result = null;
    private final MainController controller;
    private Prescription existingPrescription = null;

    public PrescriptionDialog(JFrame parent, MainController controller) {
        this(parent, controller, null);
    }

    public PrescriptionDialog(JFrame parent, MainController controller, Prescription existingPrescription) {
        super(parent, existingPrescription == null ? "Add Prescription" : "Edit Prescription", true);
        this.controller = controller;
        this.existingPrescription = existingPrescription;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));

        formPanel.add(new JLabel("Prescription ID:"));
        prescriptionIdField = new JTextField();
        if (existingPrescription != null) {
            prescriptionIdField.setText(existingPrescription.getPrescriptionID());
            prescriptionIdField.setEditable(false);
        }
        formPanel.add(prescriptionIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        if (existingPrescription != null) patientIdField.setText(existingPrescription.getPatientID());
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Clinician ID:"));
        clinicianIdField = new JTextField();
        if (existingPrescription != null) clinicianIdField.setText(existingPrescription.getClinicianID());
        formPanel.add(clinicianIdField);

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        if (existingPrescription != null) appointmentIdField.setText(existingPrescription.getAppointmentID());
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Medication Name:"));
        medicationNameField = new JTextField();
        if (existingPrescription != null) medicationNameField.setText(existingPrescription.getMedicationName());
        formPanel.add(medicationNameField);

        formPanel.add(new JLabel("Dosage:"));
        dosageField = new JTextField();
        if (existingPrescription != null) dosageField.setText(existingPrescription.getDosage());
        formPanel.add(dosageField);

        formPanel.add(new JLabel("Frequency:"));
        frequencyField = new JTextField();
        if (existingPrescription != null) frequencyField.setText(existingPrescription.getFrequency());
        formPanel.add(frequencyField);

        formPanel.add(new JLabel("Duration (Days):"));
        durationDaysField = new JTextField();
        if (existingPrescription != null) durationDaysField.setText(String.valueOf(existingPrescription.getDurationDays()));
        formPanel.add(durationDaysField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        if (existingPrescription != null) quantityField.setText(String.valueOf(existingPrescription.getQuantity()));
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Instructions:"));
        instructionsField = new JTextField();
        if (existingPrescription != null) instructionsField.setText(existingPrescription.getInstructions());
        formPanel.add(instructionsField);

        formPanel.add(new JLabel("Pharmacy Name:"));
        pharmacyNameField = new JTextField();
        if (existingPrescription != null) pharmacyNameField.setText(existingPrescription.getPharmacyName());
        formPanel.add(pharmacyNameField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        if (existingPrescription != null) statusField.setText(existingPrescription.getStatus());
        formPanel.add(statusField);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOK());
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 600);
        setLocationRelativeTo(parent);
    }

    private void onOK() {
        try {
            String prescriptionID = prescriptionIdField.getText();
            String patientID = patientIdField.getText();
            String clinicianID = clinicianIdField.getText();
            String appointmentID = appointmentIdField.getText();
            LocalDate prescriptionDate = existingPrescription != null ? existingPrescription.getPrescriptionDate() : LocalDate.now();
            String medicationName = medicationNameField.getText();
            String dosage = dosageField.getText();
            String frequency = frequencyField.getText();
            int durationDays = Integer.parseInt(durationDaysField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String instructions = instructionsField.getText();
            String pharmacyName = pharmacyNameField.getText();
            String status = statusField.getText();
            LocalDate issueDate = existingPrescription != null ? existingPrescription.getIssueDate() : LocalDate.now();
            LocalDate collectionDate = existingPrescription != null ? existingPrescription.getCollectionDate() : null;

            if (!validateFields()) {
                return;
            }

            result = new Prescription(prescriptionID, patientID, clinicianID, appointmentID, prescriptionDate, medicationName, dosage, frequency, durationDays, quantity, instructions, pharmacyName, status, issueDate, collectionDate);
            
            if (existingPrescription != null) {
                controller.updatePrescription(result);
            } else {
                controller.addPrescription(result);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (prescriptionIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Prescription ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (patientIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(durationDaysField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duration must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Prescription getResult() {
        return result;
    }
}
