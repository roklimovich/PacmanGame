package pl.pja.edu.s27619;

import pl.pja.edu.s27619.windows.GameWindow;
import pl.pja.edu.s27619.windows.MainMenuWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenuWindow();
            }
        });
    }

}