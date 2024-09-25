package pl.pja.edu.s27619.content;

import java.awt.*;

public class MovebleObject extends BasicClass{
    private int currentColumn;
    private int currentRow;
    private int startColumn;
    private int startRow;
    private boolean isBonusApplied;

    public MovebleObject(int startRow, int startColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.currentRow = this.startRow;
        this.currentColumn = this.startColumn;

    }

    @Override
    public void draw(Graphics g) {

    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }
    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public boolean isBonusApplied() {
        return isBonusApplied;
    }

    public void setBonusApplied(boolean bonusApplied) {
        isBonusApplied = bonusApplied;
    }
}

