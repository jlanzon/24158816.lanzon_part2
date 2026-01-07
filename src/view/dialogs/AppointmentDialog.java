package view.dialogs;

import controller.MainController;
import model.domain.Appointment;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDialog extends JDialog {
    private JTextField appointmentIdField;
    private JTextField patientIdField;
    private JTextField clinicianIdField;
    private JTextField facilityIdField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField durationField;
    private JTextField typeField;
    private JTextField statusField;
    private JTextField reasonField;
    private JTextField notesField;

    private Appointment result = null;
    private final MainController controller;
    private Appointment existingAppointment = null;

    public AppointmentDialog(JFrame parent, MainController controller) {
        this(parent, controller, null);
    }

    public AppointmentDialog(JFrame parent, MainController controller, Appointment existingAppointment) {
        super(parent, existingAppointment == null ? "Add Appointment" : "Edit Appointment", true);
        this.controller = controller;
        this.existingAppointment = existingAppointment;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));

        formPanel.add(new JLabel("Appointment ID:"));
        appointmentIdField = new JTextField();
        if (existingAppointment != null) {
            appointmentIdField.setText(existingAppointment.getAppointmentID());
            appointmentIdField.setEditable(false);
        }
        formPanel.add(appointmentIdField);

        formPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        if (existingAppointment != null) patientIdField.setText(existingAppointment.getPatientID());
        formPanel.add(patientIdField);

        formPanel.add(new JLabel("Clinician ID:"));
        clinicianIdField = new JTextField();
        if (existingAppointment != null) clinicianIdField.setText(existingAppointment.getClinicianID());
        formPanel.add(clinicianIdField);

        formPanel.add(new JLabel("Facility ID:"));
        facilityIdField = new JTextField();
        if (existingAppointment != null) facilityIdField.setText(existingAppointment.getFacilityID());
        formPanel.add(facilityIdField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        if (existingAppointment != null) dateField.setText(existingAppointment.getAppointmentDate().toString());
        formPanel.add(dateField);

        formPanel.add(new JLabel("Time (HH:MM):"));
        timeField = new JTextField();
        if (existingAppointment != null) timeField.setText(existingAppointment.getAppointmentTime().toString());
        formPanel.add(timeField);

        formPanel.add(new JLabel("Duration (Minutes):"));
        durationField = new JTextField("30");
        if (existingAppointment != null) durationField.setText(String.valueOf(existingAppointment.getDurationMinutes()));
        formPanel.add(durationField);

        formPanel.add(new JLabel("Type:"));
        typeField = new JTextField();
        if (existingAppointment != null) typeField.setText(existingAppointment.getAppointmentType());
        formPanel.add(typeField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField();
        if (existingAppointment != null) statusField.setText(existingAppointment.getStatus());
        formPanel.add(statusField);

        formPanel.add(new JLabel("Reason:"));
        reasonField = new JTextField();
        if (existingAppointment != null) reasonField.setText(existingAppointment.getReasonForVisit());
        formPanel.add(reasonField);

        formPanel.add(new JLabel("Notes:"));
        notesField = new JTextField();
        if (existingAppointment != null) notesField.setText(existingAppointment.getNotes());
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
            String appointmentID = appointmentIdField.getText();
            String patientID = patientIdField.getText();
            String clinicianID = clinicianIdField.getText();
            String facilityID = facilityIdField.getText();
            LocalDate date = LocalDate.parse(dateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalTime time = LocalTime.parse(timeField.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
            int duration = Integer.parseInt(durationField.getText());
            String type = typeField.getText();
            String status = statusField.getText();
            String reason = reasonField.getText();
            String notes = notesField.getText();
            LocalDate createdDate = existingAppointment != null ? existingAppointment.getCreatedDate() : LocalDate.now();
            LocalDate lastModified = LocalDate.now();

            if (!validateFields()) {
                return;
            }

            result = new Appointment(appointmentID, patientID, clinicianID, facilityID, date, time, duration, type, status, reason, notes, createdDate, lastModified);
            
            if (existingAppointment != null) {
                controller.updateAppointment(result);
            } else {
                controller.addAppointment(result);
            }
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (appointmentIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Appointment ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (patientIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID is required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalDate.parse(dateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Date must be in YYYY-MM-DD format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalTime.parse(timeField.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Time must be in HH:MM format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(durationField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Duration must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Appointment getResult() {
        return result;
    }
}
