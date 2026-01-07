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

    public PatientDialog(JFrame parent, MainController controller) {
        super(parent, "Add Patient", true);
        this.controller = controller;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("NHS Number:"));
        nhsNumberField = new JTextField();
        formPanel.add(nhsNumberField);

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        dobField = new JTextField();
        formPanel.add(dobField);

        formPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        formPanel.add(genderField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Postcode:"));
        postcodeField = new JTextField();
        formPanel.add(postcodeField);

        formPanel.add(new JLabel("Emergency Contact Name:"));
        emergencyNameField = new JTextField();
        formPanel.add(emergencyNameField);

        formPanel.add(new JLabel("Emergency Contact Phone:"));
        emergencyPhoneField = new JTextField();
        formPanel.add(emergencyPhoneField);

        formPanel.add(new JLabel("GP Surgery ID:"));
        gpSurgeryIdField = new JTextField();
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

        setSize(400, 600);
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
            LocalDate registrationDate = LocalDate.now();

            result = new Patient(patientId, nhsNumber, firstName, lastName, dob, gender, phone, email, address, postcode, emergencyName, emergencyPhone, registrationDate, gpSurgeryId);
            controller.addPatient(result);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Patient getResult() {
        return result;
    }
}
