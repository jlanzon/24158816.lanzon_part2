package view.dialogs;

import controller.MainController;
import model.domain.Patient;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientDialog extends JDialog {
    private JTextField patientIdField;
    private JTextField nhsNumberField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField genderField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField postcodeField;
    private JTextField emergencyNameField;
    private JTextField emergencyPhoneField;
    private JTextField gpSurgeryIdField;

    private Patient result = null;
    private final MainController controller;
    private Patient existingPatient = null;

    public PatientDialog(JFrame parent, MainController controller) {
        this(parent, controller, null);
    }

    public PatientDialog(JFrame parent, MainController controller, Patient existingPatient) {
        super(parent, existingPatient == null ? "Add Patient" : "Edit Patient", true);
        this.controller = controller;
        this.existingPatient = existingPatient;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        if (existingPatient != null) {
            patientIdField.setText(existingPatient.getPatientId());
            patientIdField.setEditable(false);
        }
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("NHS Number:"));
        nhsNumberField = new JTextField();
        if (existingPatient != null) nhsNumberField.setText(existingPatient.getNhsNumber());
        formPanel.add(nhsNumberField);

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        if (existingPatient != null) firstNameField.setText(existingPatient.getFirstName());
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        if (existingPatient != null) lastNameField.setText(existingPatient.getLastName());
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        if (existingPatient != null) dobField.setText(existingPatient.getDateOfBirth().toString());
        formPanel.add(dobField);

        formPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        if (existingPatient != null) genderField.setText(existingPatient.getGender());
        formPanel.add(genderField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        if (existingPatient != null) phoneField.setText(existingPatient.getPhoneNumber());
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        if (existingPatient != null) emailField.setText(existingPatient.getEmail());
        formPanel.add(emailField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        if (existingPatient != null) addressField.setText(existingPatient.getAddress());
        formPanel.add(addressField);

        formPanel.add(new JLabel("Postcode:"));
        postcodeField = new JTextField();
        if (existingPatient != null) postcodeField.setText(existingPatient.getPostcode());
        formPanel.add(postcodeField);

        formPanel.add(new JLabel("Emergency Contact Name:"));
        emergencyNameField = new JTextField();
        if (existingPatient != null) emergencyNameField.setText(existingPatient.getEmergencyContactName());
        formPanel.add(emergencyNameField);

        formPanel.add(new JLabel("Emergency Contact Phone:"));
        emergencyPhoneField = new JTextField();
        if (existingPatient != null) emergencyPhoneField.setText(existingPatient.getEmergencyContactPhone());
        formPanel.add(emergencyPhoneField);

        formPanel.add(new JLabel("GP Surgery ID:"));
        gpSurgeryIdField = new JTextField();
        if (existingPatient != null) gpSurgeryIdField.setText(existingPatient.getGpSurgeryID());
        formPanel.add(gpSurgeryIdField);

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
            String patientId = patientIdField.getText();
            String nhsNumber = nhsNumberField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            LocalDate dob = LocalDate.parse(dobField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            String gender = genderField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String postcode = postcodeField.getText();
            String emergencyName = emergencyNameField.getText();
            String emergencyPhone = emergencyPhoneField.getText();
            String gpSurgeryId = gpSurgeryIdField.getText();
            LocalDate registrationDate = existingPatient != null ? existingPatient.getRegistrationDate() : LocalDate.now();

            if (!validateFields()) {
                return;
            }

            result = new Patient(patientId, nhsNumber, firstName, lastName, dob, gender, phone, email, address, postcode, emergencyName, emergencyPhone, registrationDate, gpSurgeryId);
            
            if (existingPatient != null) {
                controller.updatePatient(result);
            } else {
                controller.addPatient(result);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (patientIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (firstNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (lastNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalDate.parse(dobField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Date of Birth must be in YYYY-MM-DD format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Patient getResult() {
        return result;
    }
}
