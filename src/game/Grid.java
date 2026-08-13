package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Grid<TState extends CellState> implements Iterable<Cell<TState>> {
    private ArrayList<ArrayList<Cell<TState>>> cells;

    private final int rows;
    private final int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>(rows);
        for(int i = 0; i < rows; i++) {
            ArrayList<Cell<TState>> row = new ArrayList<>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(null);
            }
            cells.add(row);
        }
    }

    public Grid(int size) {
        this(size, size);
    }

    public Grid(Grid<TState> otherGrid) {
        this.rows = otherGrid.rows;
        this.cols = otherGrid.cols;
        this.cells = new ArrayList<>(rows);
        for(int row = 0; row < rows; row++) {
            this.cells.add( new ArrayList<>(cols));
            for(int column = 0; column < cols; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, otherGrid.getCellState(row, column));
                this.addCell(row, column, cell);
            }
        }
    }

    private TState getCellState(int row, int column) {
        return  cells.get(row).get(column).getState();
    }

    public void setCell(int row, int column, Cell<TState> cell) {
        if (row < 0 || column < 0 || row >= rows || column >= cols) {
            throw new IndexOutOfBoundsException("Invalid row or column");
        }
        cells.get(row).set(column, cell);
    }

    public void addCell(int row, int column, Cell<TState> cell) {
        if (row < 0 || column < 0 || row >= rows || column >= cols) {
            throw new IndexOutOfBoundsException("Invalid row or column");
        }
        cells.get(row).add(column, cell);
    }

    /*
        Return the number of rows on the grid
     */
    public int rows() {
        return rows;
    }

    /*
        Return the number of columns on each row.
     */
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

    // Future Grid function
    public List<Cell<TState>> getCellNeighbors(Cell<TState> cell) {
        int[] rows = new int[3];
        int[] columns = new int[3];

        rows[0] = cell.getX() - 1;
        rows[1] = cell.getX();
        rows[2] = cell.getX() + 1;

        columns[0] = cell.getY() - 1;
        columns[1] = cell.getY();
        columns[2] = cell.getY() + 1;

        if (cell.getX() == this.rows() - 1) {
            rows[2] = 0;
        }

        if (cell.getX() == 0) {
            rows[0] = this.rows() - 1;
        }

        if (cell.getY() == this.columns() - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] =  this.columns() - 1;
        }

        List<Cell<TState>> neighbors = new ArrayList<Cell<TState>>(8);

        for (int x : rows) {
            for (int y : columns) {
                if (cell.getX() == x && cell.getY() == y) {
                    continue;
                }

                Cell<TState> neighbor = this.getCell(x, y);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    //TODO: This should be deleted.
    // This gives control to over who use it to modify the cells.
    // Not good.
    public ArrayList<ArrayList<Cell<TState>>> cells() {
        return cells;
    }

    @Override
    public Iterator<Cell<TState>> iterator() {
        return new GridIterator<>(this);
    }

    public Stream<Cell<TState>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
