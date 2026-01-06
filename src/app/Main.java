package app;

import controller.MainController;
import model.store.DataRepository;
import view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataRepository repo = new DataRepository();
            MainController controller = new MainController(repo);
            controller.loadAllData();

            MainFrame frame = new MainFrame(controller);
            frame.setVisible(true);
        });
    }
}
