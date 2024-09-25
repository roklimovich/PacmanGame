package pl.pja.edu.s27619.board;

import pl.pja.edu.s27619.cell.Cell;
import pl.pja.edu.s27619.cell.Pass;
import pl.pja.edu.s27619.cell.Wall;
import pl.pja.edu.s27619.content.*;
import pl.pja.edu.s27619.content.Point;
import pl.pja.edu.s27619.listener.Listener;
import pl.pja.edu.s27619.manager.HighScoreManager;
import pl.pja.edu.s27619.windows.HighScoresWindow;
import pl.pja.edu.s27619.windows.MainMenuWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameBoard implements GameBoardInterface {
    private HighScoreManager highScoreManager;
    private List<Listener> listeners = new ArrayList<>();
    private List<Enemy> enemies;
    private List<Thread> enemiesThreads;
    private Pacman pacman;
    private int health;
    private int newSpeed;
    private int score;
    private JTable table;
    private Thread threadPacman;
    private int totalScore;

    public GameBoard(int rows, int columns) {
        highScoreManager = new HighScoreManager();
        enemies = new LinkedList<>();
        enemiesThreads = new LinkedList<>();
        score = 0;
        health = 3;
        totalScore = 0;
        table = new JTable();
        pacman = new Pacman(1, 1, this);

        threadPacman = new Thread(pacman);
        table.setFocusable(false);

        table.setModel(new GameBoardTableModel(pacman, enemies, rows, columns));
        table.setDefaultRenderer(Object.class, new GameBoardRender());
        table.requestFocusInWindow();


        //delete all borders
        table.setIntercellSpacing(new Dimension(0,0));
        table.getTableHeader().setVisible(false);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(25);
        }

        for (int i = 0; i < table.getRowCount(); i++) {
            table.setRowHeight(25);
        }
        table.setPreferredScrollableViewportSize(new Dimension(400, 400));
        for (Enemy enemy : enemies) {
            enemy.setGameBoardInterface(this);
            Thread thread = new Thread(enemy);
            enemiesThreads.add(thread);
        }
        startThreads();
    }

    /**
     * This method move the object using his direction
     *
     * @param movebleObject - what kind of object it is: pacman or enemy
     * @param direction     - direction of object
     * @return
     */
    @Override
    public boolean move(MovebleObject movebleObject, Direction direction) {
        switch (direction) {
            case UP:
                return moveObject(movebleObject, movebleObject.getCurrentRow(), movebleObject.getCurrentColumn(),
                        movebleObject.getCurrentRow() - 1, movebleObject.getCurrentColumn(),
                        false);
            case DOWN:
                return moveObject(movebleObject, movebleObject.getCurrentRow(), movebleObject.getCurrentColumn(),
                        movebleObject.getCurrentRow() + 1, movebleObject.getCurrentColumn(),
                        false);
            case LEFT:
                return moveObject(movebleObject, movebleObject.getCurrentRow(), movebleObject.getCurrentColumn(),
                        movebleObject.getCurrentRow(), movebleObject.getCurrentColumn() - 1,
                        false);
            case RIGHT:
                return moveObject(movebleObject, movebleObject.getCurrentRow(), movebleObject.getCurrentColumn(),
                        movebleObject.getCurrentRow(), movebleObject.getCurrentColumn() + 1,
                        false);
        }
        return false;
    }

    /**
     * Method implement movement of object
     *
     * @param movebleObject  - can be pacman or enemy
     * @param rowIndex       - variable which contains information about "old" row
     * @param columnIndex    - variable which contains information about "old" column
     * @param newRowIndex    - variable which contains information about "new" row
     * @param newColumnIndex - variable which contains information about "new" column
     * @param isRespawn      - boolean variable which contains information about respawn
     * @return
     */
    private synchronized boolean moveObject(MovebleObject movebleObject, int rowIndex, int columnIndex, int newRowIndex,
                                            int newColumnIndex, boolean isRespawn) {
        /*
         * Create our tunnel, if new column index less the zero, we assign to new column the last elem of all columns
         * If not, we assign zero to new column
         */
        if (newColumnIndex < 0) {
            newColumnIndex = table.getColumnCount() - 1;
        } else if (newColumnIndex >= table.getColumnCount()) {
            newColumnIndex = 0;
        }


        Cell newCell = (Cell) table.getValueAt(newRowIndex, newColumnIndex); //get information about new cell
        Cell oldCell = (Cell) table.getValueAt(rowIndex, columnIndex); //get information about old cell

        //if isRespawn not equals true we are doing this
        if (!isRespawn) {
            //check what kind of content in our cell, if false, we return false
            if (newCell instanceof Wall) {
                return false;
            } else {
                //if in one cell could be one and more object, we iterate list of content which can be in the cell
                List<BasicClass> contentList = ((Pass) newCell).getContentOfPass();

                //iterate all elements from list
                for (BasicClass content : contentList) {
                    //check if content kind of point, we check if moveble object pacman or enemy
                    if (content instanceof Point) {
                        if (movebleObject instanceof Pacman) {
                            //if moveble object is pacman, we add +1 to our current score
                            score++;
                            totalScore++;

                            //update information about current score on our game window
                            for (Listener listener : listeners) {
                                listener.updateInformation(totalScore, health);
                            }

                            /*
                             * Also we should check if our current score equals to maxScore or not, if yes,
                             * stop pacman and enemy threads and call method game over to save records
                             */
                            if (score >= ((GameBoardTableModel) table.getModel()).getMaxScore()) {
                                gameOver();
                                stopThreads();
                            }
                        }
                    } else if ((content instanceof Enemy && movebleObject instanceof Pacman) ||
                            (content instanceof Pacman && movebleObject instanceof Enemy)) {
                        /*
                         * Check if health of pacman equals zero, we stop threads and show message to user, and back to
                         * main menu
                         */
                        if (pacman.isBonusApplied()) {
                            totalScore += 10;
                            respawn();
                            for (Listener listener : listeners) {
                                listener.updateInformation(totalScore, health);
                            }
                        } else {
                            if (health == 1) {
                                JOptionPane.showMessageDialog(table, "GAME OVER!", "WARNING!",
                                        JOptionPane.ERROR_MESSAGE);
                                new MainMenuWindow();
                                stopThreads();
                            }
                            //if not, we just remove one health from max health of pacman
                            health--;
                            //update information about health on our game window
                            for (Listener listener : listeners) {
                                listener.updateInformation(totalScore, health);
                            }
                            //call this method to respawn enemies and pacman
                            respawn();
                        }
                        return false;

                    } else if (content instanceof Bonus) {
                        if (movebleObject instanceof Pacman) {
                            //call method eat bonus which increase speed of pacman for 5 seconds
                            eatBonus();
                        }
                    }

                }
            }
        }

        //update information about new content in new cell
        ((Pass) newCell).setTopContent(movebleObject);

        //and set necessary coordinates to this content
        movebleObject.setCurrentRow(newRowIndex);
        movebleObject.setCurrentColumn(newColumnIndex);

        if (movebleObject instanceof Pacman) {
            //if moveble object pacman we clear all content from old cell
            ((Pass) oldCell).getContentOfPass().clear();
        } else if (movebleObject instanceof Enemy) {
            //if moveble object is enemy, we just remove this object, because enemy shouldn't eat points or bonuses
            ((Pass) oldCell).getContentOfPass().remove(movebleObject);

        }
        //repaint table
        table.repaint();

        return true;
    }


    /**
     * This method respawn all moveble objects
     */
    public void respawn() {
        if (!pacman.isBonusApplied()) {
            moveObject(pacman, pacman.getCurrentRow(), pacman.getCurrentColumn(), pacman.getStartRow(),
                    pacman.getStartColumn(), true);
            for (Enemy enemy : enemies) {
                moveObject(enemy, enemy.getCurrentRow(), enemy.getCurrentColumn(), enemy.getStartRow(),
                        enemy.getStartColumn(), true);
            }
        } else {
            for (Enemy enemy : enemies) {
                moveObject(enemy, enemy.getCurrentRow(), enemy.getCurrentColumn(), enemy.getStartRow(),
                        enemy.getStartColumn(), true);
            }
        }

    }

    /**
     * This method implements our bonus for pacman
     */
    public void eatBonus() {
        //we increase our speed of pacman
        newSpeed = (int) (pacman.getCurrentSpeed() / 1.5);
        pacman.setCurrentSpeed(newSpeed);

        pacman.setBonusApplied(true);
        for (Enemy enemy : enemies) {
            enemy.setBonusApplied(true);
        }

        //create new thread and make sleep for 5 sec, then we set "old" speed to our pacman
        Thread threadBonus = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    pacman.setCurrentSpeed(750);
                    pacman.setBonusApplied(false);
                    for (Enemy enemy : enemies) {
                        enemy.setBonusApplied(false);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //start our thread
        threadBonus.start();
    }

    /**
     * Method shows to user necessary message and add his high score to list of all scores
     */
    public void gameOver() {
        String name = JOptionPane.showInputDialog("You win! Enter the name to save records: ");

        highScoreManager.addHighScore(name, totalScore);
        new HighScoresWindow(highScoreManager);

    }

    /**
     * Method stop all threads in the game
     */
    public void stopThreads() {
        for (Thread thread : enemiesThreads) {
            thread.interrupt();
        }
        threadPacman.interrupt();
    }

    /**
     * Method start all threads in the game
     */
    public void startThreads() {
        for (Thread thread : enemiesThreads) {
            thread.start();
        }
        threadPacman.start();
    }

    public void moveUP() {
        pacman.moveUp();
    }

    public void moveRIGHT() {
        pacman.moveRight();
    }

    public void moveLEFT() {
        pacman.moveLeft();
    }

    public void moveDOWN() {
        pacman.moveDown();
    }


    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public JTable getTable() {
        return table;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
