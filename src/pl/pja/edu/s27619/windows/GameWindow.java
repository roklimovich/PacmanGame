package pl.pja.edu.s27619.windows;

import pl.pja.edu.s27619.board.GameBoard;
import pl.pja.edu.s27619.content.Pacman;
import pl.pja.edu.s27619.listener.Listener;
import pl.pja.edu.s27619.timer.TimeCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame {
    private boolean ctrlPressed;
    private boolean shiftPressed;
    private boolean qPressed;
    private int rows;
    private int columns;

    public GameWindow(int rows, int columns) {
        super("Game window");
        this.rows = rows;
        this.columns = columns;
        setLayout(new BorderLayout());
        setSize(1200, 1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //title of window
        JPanel titlePanel = new JPanel();
        JLabel textField = new JLabel();

        textField.setBackground(new Color(0, 191, 255));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Pacman game");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(textField);
        add(titlePanel, BorderLayout.NORTH);


        //GUI for game board
        JPanel tablePanel = new JPanel();
        GameBoard gameBoard = new GameBoard(rows, columns);
        tablePanel.add(gameBoard.getTable());
        tablePanel.setBackground(new Color(0, 191, 255));
        add(tablePanel, BorderLayout.LINE_START);

        //Score and Health panel
        JPanel scoreAndHealthPanel = new JPanel();
        JLabel scoreAndHealthLabel = new JLabel();
        scoreAndHealthLabel.setBackground(new Color(0, 191, 255));
        scoreAndHealthLabel.setForeground(new Color(0, 0, 0));
        scoreAndHealthLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        scoreAndHealthLabel.setText("<html> Score: " + gameBoard.getTotalScore() + "<br> Health: " +
                gameBoard.getHealth() + "</html>");

        //add listener to update information of current score and health
        Listener listener = new Listener() {
            @Override
            public void updateInformation(int newScore, int newHealth) {
                scoreAndHealthLabel.setText("<html> Score: " + newScore + "<br> Health: " +
                        newHealth + "</html>");
            }
        };
        gameBoard.addListener(listener);

        scoreAndHealthPanel.add(scoreAndHealthLabel);
        scoreAndHealthPanel.setBackground(new Color(0, 191, 255));
        add(scoreAndHealthPanel, BorderLayout.CENTER);


        //time panel
        JPanel timePanel = new JPanel();
        JLabel timeField = new JLabel();
        TimeCounter timeCounter = new TimeCounter(timeField);
        timeCounter.start();

        timePanel.setBackground(new Color(0, 191, 255));
        timePanel.add(timeField);
        add(timePanel, BorderLayout.PAGE_END);

        //add key listener to handle keystrokes
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyEvent = e.getKeyCode();

                switch (keyEvent) {
                    case KeyEvent.VK_UP:
                        gameBoard.moveUP();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameBoard.moveDOWN();
                        break;
                    case KeyEvent.VK_LEFT:
                        gameBoard.moveLEFT();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameBoard.moveRIGHT();
                        break;
                    case KeyEvent.VK_CONTROL:
                        ctrlPressed = true;
                        break;
                    case KeyEvent.VK_SHIFT:
                        shiftPressed = true;
                        break;
                    case KeyEvent.VK_Q:
                        qPressed = true;
                        break;
                }
                //if compound keyboard shortcut is pressed we interrupt our program and back to main menu
                if (ctrlPressed && shiftPressed && qPressed) {
                    gameBoard.stopThreads();
                    timeCounter.interrupt();
                    dispose();
                    new MainMenuWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
