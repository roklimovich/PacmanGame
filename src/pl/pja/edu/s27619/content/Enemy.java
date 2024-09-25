package pl.pja.edu.s27619.content;

import pl.pja.edu.s27619.board.GameBoardInterface;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Enemy extends MovebleObject implements Runnable {
    private GameBoardInterface gameBoardInterface;

    final private int SPEED = 600;

    public Enemy(int currentRow, int currentColumn, GameBoardInterface gameBoardInterface) {
        super(currentRow, currentColumn);
        setGameBoardInterface(gameBoardInterface);
    }

    /**
     * This method uses to draw enemies
     *
     * @param g - param of Graphics
     */
    @Override
    public void draw(Graphics g) {

        int enemySize = Math.min(25, 25) - 3;

        int enemyX = (25 - enemySize) / 2;
        int enemyY = (25 - enemySize) / 2;

        if (isBonusApplied()) {
            g.setColor(new Color(128, 128, 128));
        } else {
            g.setColor(new Color(123, 104, 238));
        }

        g.fillRoundRect(enemyX, enemyY, 23, 20, 10, 10);

        g.setColor(new Color(255, 255, 255));
        g.fillOval(2, 2, 7, 7);
        g.fillOval(13, 2, 7, 7);

        g.setColor(new Color(0, 0, 0));
        g.fillOval(2, 3, 4, 4);
        g.fillOval(12, 3, 4, 4);
    }

    /**
     * Method sets interface to enemy
     *
     * @param gameBoardInterface
     */
    public void setGameBoardInterface(GameBoardInterface gameBoardInterface) {
        this.gameBoardInterface = gameBoardInterface;
    }

    /**
     * It is our thread method which move enemy from the cells
     */

    @Override
    public void run() {
        while (!Thread.interrupted()) {

            Direction randomDirection = getRandomDirection();
            gameBoardInterface.move(this, randomDirection);

            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Enemy thread stopped");
    }

    /**
     * This method generate random direction of enemy
     *
     * @return - direction index
     */
    public Direction getRandomDirection() {
        Random random = new Random();
        int directionIndex = random.nextInt(Direction.values().length);
        return Direction.values()[directionIndex];
    }
}
