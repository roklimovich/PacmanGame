package pl.pja.edu.s27619.content;

import java.awt.*;

public class Point extends BasicClass{

    /**
     * This method draw point on our game board
     * @param g - param of Graphics
     */
    @Override
    public void draw(Graphics g) {

        int dotSize = 5;

        int dotX = (25 - 5) / 2;
        int dotY = (25 - 5) / 2;

        g.setColor(new Color(255, 255, 255));
        g.fillOval(dotX, dotY, dotSize, dotSize);
    }
}
