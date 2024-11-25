import ui.MainUI;

import javax.swing.*;

public class  Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainUI mainUI = new MainUI();
                mainUI.setVisible(true);
                mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
