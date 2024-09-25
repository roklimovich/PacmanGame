package pl.pja.edu.s27619.manager;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreManager {
    final private String HIGHSCORESFILE = "HighScores.txt";
    private ArrayList<SaveHighScores> highScores;


    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores(HIGHSCORESFILE);

    }

    /**
     * Add high scores to our list of high scores
     *
     * @param name  - name of winner
     * @param score - current score
     */
    public void addHighScore(String name, int score) {
        SaveHighScores saveHighScores = new SaveHighScores(name, score);
        highScores.add(saveHighScores);
        writeHighScores(HIGHSCORESFILE);
    }

    /**
     * Write high scores to our file
     */
    public void writeHighScores(String fileName) {
        try {
            FileOutputStream writeScoresToFile = new FileOutputStream(HIGHSCORESFILE, false);
            ObjectOutputStream writeScores = new ObjectOutputStream(writeScoresToFile);
            writeScores.writeObject(highScores);
            writeScores.close();
            writeScoresToFile.close();
            System.out.println("scores saved");
        } catch (IOException e) {
            System.out.println("scores are not saved");
        }
    }

    /**
     * This method we use to load information from our file
     */
    public void loadHighScores(String fileName) {
        try {
            FileInputStream loadScoreToFile = new FileInputStream(fileName);
            ObjectInputStream loadScores = new ObjectInputStream(loadScoreToFile);
            highScores = (ArrayList) loadScores.readObject();
            loadScores.close();
            loadScoreToFile.close();
            System.out.println("scores loaded");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("scores are not loaded");
        }
    }

    /**
     * Method to make list model for our functionality
     */
    public DefaultListModel<String> getHighScoreListModel() {
        DefaultListModel<String> listOfHighScores = new DefaultListModel<>();
        for (SaveHighScores highScores : highScores) {
            listOfHighScores.addElement(highScores.getName() + " " + highScores.getScore());
        }
        return listOfHighScores;
    }
}
