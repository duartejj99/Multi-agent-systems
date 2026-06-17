package game;

import java.util.ArrayList;

public class Grid<TState extends CellState> {
    private ArrayList<ArrayList<Cell<TState>>> cells;

    private final int rows;
    private final int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>(rows);
        for(int i = 0; i < rows; i++) {
            cells.add( new ArrayList<>(cols));
        }
    }

    public Grid(int size) {
        this(size, size);
    }

    public Grid(Grid<TState> grid) {
        this.rows = grid.rows;
        this.cols = grid.cols;
        this.cells = new ArrayList<>(rows);
        for(int row = 0; row < rows; row++) {
            this.cells.add( new ArrayList<>(cols));
            for(int column = 0; column < cols; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, grid.getCellState(row, column));
                this.addCell(row, column, cell);
            }
        }
    }

    private TState getCellState(int row, int column) {
        return  cells.get(row).get(column).getState();
    }

    public void addCell(int row, int column, Cell<TState> cell) {
        if (row < 0 || column < 0 || row >= rows || column >= cols) {
            throw new IndexOutOfBoundsException("Invalid row or column");
        }
        cells.get(row).add(column, cell);
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return cols;
    }

    public Cell<TState> getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= this.rows || y >= this.cols) {
            throw new IndexOutOfBoundsException("Invalid row or column");

        }

        if (this.cells.get(x).get(y) == null) {
            throw new IllegalStateException("The cell at " + x + ", " + y + " is null");
        }

        return cells.get(x).get(y);
    }

    public void setCellState(int row, int column, TState cellState) {
        if (row < 0 || column < 0 || row >= this.rows || column >= this.cols) {
            throw new IndexOutOfBoundsException("Invalid row or column");
        }
        this.cells.get(row).get(column).setState(cellState);
    }


    //TODO: This should be deleted.
    // This gives control to over who use it to modify the cells.
    // Not good.
    public ArrayList<ArrayList<Cell<TState>>> cells() {
        return cells;
    }
}
