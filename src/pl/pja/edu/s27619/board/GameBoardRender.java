package pl.pja.edu.s27619.board;

import pl.pja.edu.s27619.cell.Cell;
import pl.pja.edu.s27619.cell.Pass;
import pl.pja.edu.s27619.cell.Wall;
import pl.pja.edu.s27619.content.BasicClass;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GameBoardRender extends DefaultTableCellRenderer {

    private Object cell;

    /**
     * Custom cell render to draw our JTable
     *
     * @param table      the <code>JTable</code>
     * @param value      the value to assign to the cell at
     *                   <code>[row, column]</code>
     * @param isSelected true if cell is selected
     * @param hasFocus   true if cell has focus
     * @param row        the row of the cell to render
     * @param col        the column of the cell to render
     * @return
     */
    public Component getTableCellRendererComponent
    (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        cell = value;
        component.setBackground(new Color(0, 0, 0));
        setText("");

        return component;
    }

    /**
     * This method uses to paint cells in our JTable
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        if (cell instanceof Pass) {
            //get content of pass and set necessary color
            var topContent = ((Pass) cell).getTopContent();
            if (topContent != null) {
                topContent.draw(g);
            } else {
                g.setColor(new Color(0, 0, 0));
            }
        } else {
            g.setColor(new Color(0, 255, 0));
            g.fillRect(0, 0, 25, 25);

        }


    }
}


