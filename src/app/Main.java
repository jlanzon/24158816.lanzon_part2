package app;

import controller.MainController;
import model.store.DataRepository;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DataRepository repository = new DataRepository();
                repository.loadAll();
                MainController controller = new MainController(repository);
                MainFrame frame = new MainFrame(controller);
                
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error starting application: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
