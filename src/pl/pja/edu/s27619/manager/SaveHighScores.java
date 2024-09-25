package pl.pja.edu.s27619.manager;

import java.io.Serializable;

public class SaveHighScores implements Serializable {
    private String name;
    private int score;

    public SaveHighScores(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return "Name: " + name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Name: " + name + " " + "Score: " + score;
    }
}
