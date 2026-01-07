package view.dialogs;

import controller.MainController;
import model.domain.Clinician;

import javax.swing.*;
import java.awt.*;

public class ClinicianDialog extends JDialog {
    private JTextField clinicianIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField roleField;
    private JTextField qualificationField;
    private JTextField specialtyField;
    private JTextField workplaceField;
    private JTextField phoneField;
    private JTextField emailField;

    private Clinician result = null;
    private final MainController controller;
    private Clinician existingClinician = null;

    public ClinicianDialog(JFrame parent, MainController controller) {
        this(parent, controller, null);
    }

    public ClinicianDialog(JFrame parent, MainController controller, Clinician existingClinician) {
        super(parent, existingClinician == null ? "Add Clinician" : "Edit Clinician", true);
        this.controller = controller;
        this.existingClinician = existingClinician;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 5, 5));

        formPanel.add(new JLabel("Clinician ID:"));
        clinicianIdField = new JTextField();
        if (existingClinician != null) {
            clinicianIdField.setText(existingClinician.getClinicianID());
            clinicianIdField.setEditable(false);
        }
        formPanel.add(clinicianIdField);

        formPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        if (existingClinician != null) firstNameField.setText(existingClinician.getFirstName());
        formPanel.add(firstNameField);

        formPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        if (existingClinician != null) lastNameField.setText(existingClinician.getLastName());
        formPanel.add(lastNameField);

        formPanel.add(new JLabel("Role:"));
        roleField = new JTextField();
        if (existingClinician != null) roleField.setText(existingClinician.getRole());
        formPanel.add(roleField);

        formPanel.add(new JLabel("Qualification:"));
        qualificationField = new JTextField();
        if (existingClinician != null) qualificationField.setText(existingClinician.getQualification());
        formPanel.add(qualificationField);

        formPanel.add(new JLabel("Specialty:"));
        specialtyField = new JTextField();
        if (existingClinician != null) specialtyField.setText(existingClinician.getSpecialty());
        formPanel.add(specialtyField);

        formPanel.add(new JLabel("Workplace:"));
        workplaceField = new JTextField();
        if (existingClinician != null) workplaceField.setText(existingClinician.getWorkplace());
        formPanel.add(workplaceField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        if (existingClinician != null) phoneField.setText(existingClinician.getPhoneNumber());
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        if (existingClinician != null) emailField.setText(existingClinician.getEmail());
        formPanel.add(emailField);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOK());
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 500);
        setLocationRelativeTo(parent);
    }

    private void onOK() {
        try {
            String clinicianID = clinicianIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String role = roleField.getText();
            String qualification = qualificationField.getText();
            String specialty = specialtyField.getText();
            String workplace = workplaceField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();



            if (!validateFields()) {
                return;
            }

            result = new Clinician(clinicianID, firstName, lastName, role, qualification, specialty, workplace, phone, email);
            
            if (existingClinician != null) {
                controller.updateClinician(result);
            } else {
                controller.addClinician(result);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (clinicianIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Clinician ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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
        return true;
    }

    public Clinician getResult() {
        return result;
    }
}
