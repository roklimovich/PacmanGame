package pl.pja.edu.s27619.windows;

import pl.pja.edu.s27619.manager.HighScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends JFrame {

    public MainMenuWindow() {
        super("Main menu");
        JPanel titlePanel = new JPanel();
        JLabel textField = new JLabel();

        JPanel buttonPanel = new JPanel();

        textField.setBackground(new Color(0, 191, 255));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Pacman game");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(textField);
        add(titlePanel, BorderLayout.NORTH);

        JButton button1 = new JButton("New game");
        button1.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 56));
        button1.setSize(150, 150);
        button1.setBackground(new Color(0, 191, 255));

        JButton button2 = new JButton("High scores");
        button2.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 56));
        button2.setSize(150, 150);
        button2.setBackground(new Color(0, 191, 255));

        JButton button3 = new JButton("Exit");
        button3.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 56));
        button3.setSize(100, 100);
        button3.setBackground(new Color(0, 191, 255));


        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(button1, BorderLayout.CENTER);
        buttonPanel.add(button2, BorderLayout.LINE_END);
        buttonPanel.add(button3, BorderLayout.PAGE_END);

        button1.addActionListener(e -> {
            String rows = JOptionPane.showInputDialog(button1,
                    "Enter the size of your game board. \n Rows: ");
            String columns = JOptionPane.showInputDialog(button1, "Columns: ");
            if ((rows == null || rows.isEmpty()) && (columns == null || columns.isEmpty())) {
                JOptionPane.showMessageDialog(button1,
                        "Incorrect size of board. Please enter values from 10 to 30",
                        "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                int rowsToInteger = Integer.parseInt(rows);
                int columnsToInteger = Integer.parseInt(columns);
                if ((rowsToInteger >= 10 && rowsToInteger <= 30) &&
                        (columnsToInteger >= 10 && columnsToInteger <= 30)) {
                    new GameWindow(rowsToInteger, columnsToInteger);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(button1, "Incorrect size of board.",
                            "WARNING", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        button2.addActionListener(e -> new HighScoresWindow(new HighScoreManager()));

        button3.addActionListener(e -> dispose());

        add(buttonPanel);
        setSize(800, 700);
//        setBackground(new Color(105, 105, 105));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
