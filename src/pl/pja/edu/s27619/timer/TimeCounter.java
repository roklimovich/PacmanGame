package pl.pja.edu.s27619.timer;

import javax.swing.*;
import java.awt.*;

public class TimeCounter extends Thread{
    private JLabel timeCounter;
    private int seconds;

    public TimeCounter(JLabel timeCounter) {
        this.timeCounter = timeCounter;
        seconds = 0;
    }

    /**
     * Thread for our timer
     */
    @Override
    public void run() {
        while (true) {

            String time = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60 , seconds % 60);
            timeCounter.setBackground(new Color(0, 191 , 255));
            timeCounter.setForeground(new Color(0, 0, 0));
            timeCounter.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
            timeCounter.setText("Time: " + time);
            seconds++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Timer successfully interrupted");
                break;
            }
        }
    }

}
