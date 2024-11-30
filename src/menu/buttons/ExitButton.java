package menu.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends Button {

    public ExitButton() {
        super("/menu/image/exit_button.png", 100, 100);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}