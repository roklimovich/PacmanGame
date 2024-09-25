package pl.pja.edu.s27619.board;

import pl.pja.edu.s27619.cell.Cell;
import pl.pja.edu.s27619.cell.Pass;
import pl.pja.edu.s27619.cell.Wall;
import pl.pja.edu.s27619.content.*;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GameBoardTableModel extends AbstractTableModel {
    private Cell[][] gameBoardData;
    private int numOfRows;
    private int numOfColumns;
    private int maxScore;


    public GameBoardTableModel(Pacman pacman, List<Enemy> enemies, int rows, int columns) {
        numOfRows = rows;
        numOfColumns = columns;
        gameBoardData = new Cell[numOfRows][numOfColumns];

        for (int i = 0; i < numOfRows; i++) {
            for (int k = 0; k < numOfColumns; k++) {

                //borders
                if (i == 0 || i == rows - 1 || k == 0 || k == columns - 1) {
                    gameBoardData[i][k] = new Wall();
                }

                if ((i == 2 && k == 2) || (i == 3 && k == 2) || (i == 2 && k == 3)) {
                    gameBoardData[i][k] = new Wall();
                    gameBoardData[numOfRows - 1 - i][numOfColumns - 1 - k] = new Wall();
                    gameBoardData[i][numOfColumns - 1 - k] = new Wall();
                    gameBoardData[numOfRows - 1 - i][k] = new Wall();
                }

                if ((i == numOfRows / 2 && k == 0) || ((i == numOfRows / 2 - 1) && k == 0) ||
                        (i == numOfRows / 2 && k == numOfColumns - 1) || ((i == numOfRows / 2 - 1) &&
                        k == numOfColumns - 1)) {
                    gameBoardData[i][k] = new Pass(new Point());
                    maxScore++;
                } else if (i == 1 && k == 1) {
                    gameBoardData[i][k] = new Pass(pacman);
                    pacman.setCurrentRow(i);
                    pacman.setCurrentColumn(k);
                } else if ((i == numOfRows / 2 - 1 && k == numOfColumns / 2) || (i == numOfRows / 2 - 1 &&
                        k == numOfColumns / 2 - 1)) {
                    Enemy enemy = new Enemy(i, k, null);
                    gameBoardData[i][k] = new Pass(enemy);
                    enemy.setCurrentRow(i);
                    enemy.setCurrentColumn(k);
                    enemies.add(enemy);
                } else if ((i == numOfRows - 2 && k == numOfColumns - 2) || (i == 1 && k == numOfColumns - 2)
                        || (i == numOfRows - 2 && k == 1)) {
                    gameBoardData[i][k] = new Pass(new Bonus());
                } else if (gameBoardData[i][k] == null) {
                    gameBoardData[i][k] = new Pass(new Point());
                    maxScore++;
                }
            }
        }
    }


    @Override
    public int getRowCount() {
        return numOfRows;
    }

    @Override
    public int getColumnCount() {
        return numOfColumns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return gameBoardData[rowIndex][columnIndex];
    }

    public int getMaxScore() {
        return maxScore;
    }

}

