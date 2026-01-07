package view.tabs;



import javax.swing.*;
import java.awt.*;

public class AppointmentPanel extends JPanel {

    private final AppointmentTableModel model;

    public AppointmentPanel(controller.MainController controller) {
        this.model = new AppointmentTableModel(controller.getAppointments());

        setLayout(new BorderLayout());

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton reload = new JButton("Reload");
        reload.addActionListener(e -> {
            controller.loadAllData();
            model.setAppointments(controller.getAppointments());
        });

        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(e -> {
            view.dialogs.AppointmentDialog dialog = new view.dialogs.AppointmentDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller);
            dialog.setVisible(true);
            if (dialog.getResult() != null) {
                model.setAppointments(controller.getAppointments());
            }
        });

        JButton editButton = new JButton("Edit Appointment");
        editButton.setEnabled(false);
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.domain.Appointment selected = controller.getAppointments().get(selectedRow);
                view.dialogs.AppointmentDialog dialog = new view.dialogs.AppointmentDialog((JFrame) SwingUtilities.getWindowAncestor(this), controller, selected);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    model.setAppointments(controller.getAppointments());
                }
            }
        });

        JButton deleteButton = new JButton("Delete Appointment");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this appointment?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteAppointment(controller.getAppointments().get(selectedRow).getAppointmentID());
                    model.setAppointments(controller.getAppointments());
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = table.getSelectedRow() >= 0;
            editButton.setEnabled(rowSelected);
            deleteButton.setEnabled(rowSelected);
        });

        actions.add(reload);
        actions.add(addButton);
        actions.add(editButton);
        actions.add(deleteButton);
        add(actions, BorderLayout.NORTH);
    }
}
