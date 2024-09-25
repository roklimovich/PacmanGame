package pl.pja.edu.s27619.windows;

import pl.pja.edu.s27619.manager.HighScoreManager;

import javax.swing.*;

public class HighScoresWindow extends JFrame {

    public HighScoresWindow(HighScoreManager highScoreManager) {
        super("High Scores");

        //get the list from our file
        JList<String> highScoreList = new JList<>(highScoreManager.getHighScoreListModel());
        JScrollPane scrollHIghScore = new JScrollPane(highScoreList);

        //add list to scroll pane
        this.getContentPane().add(scrollHIghScore);

        //add scroll pane to our JFrame
        add(scrollHIghScore);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
