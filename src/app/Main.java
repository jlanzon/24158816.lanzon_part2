package app;

import model.store.DataRepository;
import view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataRepository repo = new DataRepository();
            repo.loadAll();

            MainFrame frame = new MainFrame(repo);
            frame.setVisible(true);
        });
    }
}
