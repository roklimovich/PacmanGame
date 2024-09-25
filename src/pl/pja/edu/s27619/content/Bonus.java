package pl.pja.edu.s27619.content;

import java.awt.*;

public class Bonus extends BasicClass {

    /**
     * This method we use to draw bonus
     *
     * @param g - param of Graphics
     */
    @Override
    public void draw(Graphics g) {

        int circleSize = 10;

        int circleX = (25 - circleSize) / 2;
        int circleY = (25 - circleSize) / 2;

        g.setColor(new Color(255, 0, 0));
        g.fillOval(circleX, circleY, circleSize, circleSize);
    }
}
