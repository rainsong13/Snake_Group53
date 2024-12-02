import menu.MainUI;

import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        SwingUtilities.invokeLater(() -> {
            MainUI mainUI = new MainUI();
            mainUI.setVisible(true);
            mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
