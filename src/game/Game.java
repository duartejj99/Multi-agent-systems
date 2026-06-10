package game;

import gui.GUISimulator;
import gui.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Game<TState extends CellState>{

    private static final int CELL_SIZE = 20;
    private static final int CELL_OFFSET = CELL_SIZE / 2;

    private final int displayHeight;
    private final int displayWidth;
    private final int rows;
    private final int columns;

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public int getDisplayHeight() {return  displayHeight;}
    public int getDisplayWidth() {return  displayWidth;}


    //private Cell<TState>[][] grid;

    // I changed from a [][] to a List<List<>> because
    // [][] checks types at runtime as well, and at run time
    // generics disappear, so you don't know which type of array you are handling.
    // List only
    protected ArrayList<ArrayList<Cell<TState>>> grid;

    // User sized game
    public Game(int rows, int cols) {
        Random randomizer = new Random();
        this.grid = new ArrayList<>(rows);
        this.rows = rows;
        this.columns = cols;
        this.displayWidth = rows * CELL_SIZE;
        this.displayHeight = cols * CELL_SIZE;

        for (int row = 0; row < rows; row++) {
            grid.add(new ArrayList<>(cols));
            for (int column = 0; column < cols; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, newState());
                grid.get(row).add(cell);
            }
        }
    }

    public Game() {
        this(50);
    }
    // User sized game
    public Game(int size) {
        this(size, size);
    }

    public Game(Game<TState> initialState) {
        // how to clone this. // we need to do a deep copy.

        // This is a clone, a proper clone
        // If I don't clone, the restart mechanism won't work
        int rows = initialState.grid.toArray().length;
        int columns = initialState.grid.getFirst().toArray().length;

        this.grid = new ArrayList<>(rows);
        for (int row = 0; row < rows; row++) {
            grid.add(new ArrayList<>(columns));
            for (int column = 0; column < columns; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, initialState.getCell(row, column).getState());
                grid.get(row).add(cell);
            }
        }

        this.rows = initialState.rows;
        this.columns = initialState.columns;
        displayHeight = initialState.getDisplayHeight();
        displayWidth = initialState.getDisplayWidth();
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

        if (cell.getX() == getRows() - 1) {
            rows[2] = 0;
        }

        if (cell.getX() == 0) {
            rows[0] = this.grid.toArray().length - 1;
        }

        if (cell.getY() == this.grid.getFirst().toArray().length - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] =  this.grid.getFirst().toArray().length - 1;
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

    /// Gets the Cell of the given coordinates.
    public Cell<TState> getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= this.grid.toArray().length || y >= this.grid.getFirst().toArray().length) {
            throw new IndexOutOfBoundsException();
        }

        return this.grid.get(x).get(y);
    }


    public void draw(GUISimulator gui) {
        gui.reset();
        int marcoSizeX = getRows() * CELL_SIZE;
        int marcoSizeY = getColumns() * CELL_SIZE;
        Rectangle marco = new Rectangle(
                marcoSizeX / 2,
                marcoSizeY / 2,
                Color.GREEN,
                Color.GREEN,
                marcoSizeX + 1,
                marcoSizeY + 1
        );
        gui.addGraphicalElement(marco);

        Rectangle r;
        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                    r = new Rectangle(
                            column * CELL_SIZE + CELL_OFFSET,
                            row * CELL_SIZE + CELL_OFFSET,
                            Color.BLUE,
                            getCellColor(row, column),
                            CELL_SIZE
                    );

                gui.addGraphicalElement(r);
            }
        }
    }
    public abstract TState newState();

    public abstract void nextState();

    /// Calculates next state of the game.
    /// The new state calculated on the "newGrid" based on the
    /// actual state maintained on this.grid
    public void nextState(Game<TState> newState) {
        for  (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell<TState> cell = this.grid.get(row).get(column);
                newState.grid.get(row).get(column).setState(this.cellNextState(cell));
            }
        }

        // TODO: I only need to update the grid, not the whole game.
        this.grid = newState.grid;
    }

    // This is to be used as a Immutable borrow (RUST)
    public abstract TState cellNextState(Cell<TState> cell);


    public abstract Color getCellColor(int x, int y);
}
