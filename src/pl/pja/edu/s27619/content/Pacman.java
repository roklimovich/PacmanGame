package pl.pja.edu.s27619.content;

import pl.pja.edu.s27619.board.GameBoardInterface;

import java.awt.*;

public class Pacman extends MovebleObject implements Runnable {


    private GameBoardInterface gameBoardInterface;
    private Direction direction;
    private int score;
    private int currentSpeed;
    private int health;


    public Pacman(int startRow, int startColumn, GameBoardInterface gameBoardInterface) {
        super(startRow, startColumn);
        this.gameBoardInterface = gameBoardInterface;
        direction = Direction.LEFT;
        currentSpeed = 750;
        score = 0;
        health = 0;

    }

    /**
     * This method we use to draw Pacman
     *
     * @param g - param of Graphics
     */
    public synchronized void draw(Graphics g) {
        int pacmanSize = Math.min(25, 25) - 3;
        int circleX = (25 - pacmanSize) / 2;
        int circleY = (25 - pacmanSize) / 2;

        if (isBonusApplied()) {
            g.setColor(new Color(0, 0, 255));
        } else {
            g.setColor(new Color(255, 255, 0));
        }

        //redraw pacman if he changed the direction
        if (direction == Direction.RIGHT) {
            g.fillArc(circleX, circleY, pacmanSize, pacmanSize, 30, 300);
        } else if (direction == Direction.DOWN) {
            g.fillArc(circleX, circleY, pacmanSize, pacmanSize, 300, 300);
        } else if (direction == Direction.UP) {
            g.fillArc(circleX, circleY, pacmanSize, pacmanSize, 120, 300);
        } else if (direction == Direction.LEFT) {
            g.fillArc(circleX, circleY, pacmanSize, pacmanSize, 210, 300);
        }
    }


    /**
     * It is our thread method which move pacman from the cells
     */
    @Override
    public void run() {

        while (!Thread.interrupted()) {
            gameBoardInterface.move(this, direction);
            try {
                Thread.sleep(currentSpeed);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Pacman thread interrupted");
    }

    //change direction if was pressed button LEFT on keyboard
    public void moveLeft() {
        direction = Direction.LEFT;
    }

    //change direction if was pressed button UP on keyboard
    public void moveUp() {
        direction = Direction.UP;
    }

    //change direction if was pressed button DOWN on keyboard
    public void moveDown() {
        direction = Direction.DOWN;
    }

    //change direction if was pressed button RIGHT on keyboard
    public void moveRight() {
        direction = Direction.RIGHT;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
