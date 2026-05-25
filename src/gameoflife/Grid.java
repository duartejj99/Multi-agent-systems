package gameoflife;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    Cell[][] cells;
    private final int rows;
    private final int columns;

    public Grid(int size) {
        this(size, size);
    }

    public Grid(Grid cells) {
        this(cells.rows, cells.columns);

        for (int row = 0; row < cells.rows; row++){
            for (int col = 0; col < cells.columns; col++){
                this.cells[row][col].setState(cells.getCell(row, col).getState()); // TODO: Verify clone
            }
        }
    }

    public Grid(int rowsNumber, int columnsNumber) {
        rows = rowsNumber;
        columns = columnsNumber;
        cells = new Cell[rows][columns];

        for (int line = 0; line < rows; line++) {
            for (int column = 0; column < columns; column++) {
                this.cells[line][column] = new Cell(line, column);
            }
        }
    }


    public int getRowsCount() {
        return rows;
    }

    public int getColumnsCount() {
        return columns;
    }

    public void setCellState(int line, int column, Object state) {
        this.cells[line][column].setState(state);
    };
    public Object getCellState(int line, int column) {
         return this.cells[line][column].getState();
    };

    // Future Grid function
    public List<Cell> getCellNeighbors(Cell cell) {
        int[] rows = new int[3];
        int[] columns = new int[3];

        rows[0] = cell.getX() - 1;
        rows[1] = cell.getX();
        rows[2] = cell.getX() + 1;

        columns[0] = cell.getY() - 1;
        columns[1] = cell.getY();
        columns[2] = cell.getY() + 1;

        if (cell.getX() == this.cells.length - 1) {
            rows[2] = 0;
        }

        if (cell.getX() == 0) {
            rows[0] = this.cells.length - 1;
        }

        if (cell.getY() == this.cells[0].length - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] = this.cells[0].length - 1;
        }

        List<Cell> neighbors = new ArrayList<Cell>(8);

        for (int x : rows) {
            for (int y : columns) {
                if (cell.getX() == x && cell.getY() == y) {
                    continue;
                }

                Cell neighbor = this.getCell(x, y);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    /// Gets the Cell of the given coordinates.
    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= this.cells.length || y >= this.cells[0].length) {
            throw new IndexOutOfBoundsException();
        }

        return this.cells[x][y];
    }
}
