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

    public PrescriptionDialog(JFrame parent, MainController controller) {
        super(parent, "Add Prescription", true);
        this.controller = controller;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(12, 2, 5, 5));

        formPanel.add(new JLabel("Prescription ID:"));
        prescriptionIdField = new JTextField();
        formPanel.add(prescriptionIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Clinician ID:"));
        clinicianIdField = new JTextField();
        formPanel.add(clinicianIdField);

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Medication Name:"));
        medicationNameField = new JTextField();
        formPanel.add(medicationNameField);

        formPanel.add(new JLabel("Dosage:"));
        dosageField = new JTextField();
        formPanel.add(dosageField);

        formPanel.add(new JLabel("Frequency:"));
        frequencyField = new JTextField();
        formPanel.add(frequencyField);

        formPanel.add(new JLabel("Duration (Days):"));
        durationDaysField = new JTextField();
        formPanel.add(durationDaysField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Instructions:"));
        instructionsField = new JTextField();
        formPanel.add(instructionsField);

        formPanel.add(new JLabel("Pharmacy Name:"));
        pharmacyNameField = new JTextField();
        formPanel.add(pharmacyNameField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
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

        setSize(400, 600);
        setLocationRelativeTo(parent);
    }

    private void onOK() {
        try {
            String prescriptionID = prescriptionIdField.getText();
            String patientID = patientIdField.getText();
            String clinicianID = clinicianIdField.getText();
            String appointmentID = appointmentIdField.getText();
            LocalDate prescriptionDate = LocalDate.now();
            String medicationName = medicationNameField.getText();
            String dosage = dosageField.getText();
            String frequency = frequencyField.getText();
            int durationDays = Integer.parseInt(durationDaysField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String instructions = instructionsField.getText();
            String pharmacyName = pharmacyNameField.getText();
            String status = statusField.getText();
            LocalDate issueDate = LocalDate.now();
            LocalDate collectionDate = null; // Can be updated later

            result = new Prescription(prescriptionID, patientID, clinicianID, appointmentID, prescriptionDate, medicationName, dosage, frequency, durationDays, quantity, instructions, pharmacyName, status, issueDate, collectionDate);
            controller.addPrescription(result);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Prescription getResult() {
        return result;
    }
}
