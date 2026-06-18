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
    protected Grid<TState> grid;
    private final Grid<TState> initialGrid;

    // User sized game
    public Game(int rows, int cols) {
        Random randomizer = new Random();
        this.grid = new Grid<>(rows, cols);
        this.initialGrid = new Grid<>(rows, cols);
        this.rows = rows;
        this.columns = cols;
        this.displayWidth = rows * CELL_SIZE;
        this.displayHeight = cols * CELL_SIZE;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, newState());
                grid.addCell(row, column, cell);
                initialGrid.addCell(row, column, cell.clone());
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

    public Game(Game<TState> otherGame) {
        // how to clone this. // we need to do a deep copy.

        // This is a clone, a proper clone
        // If I don't clone, the restart mechanism won't work
        this.grid = new Grid<>(otherGame.grid);

        this.rows = otherGame.rows;
        this.columns = otherGame.columns;
        displayHeight = otherGame.getDisplayHeight();
        displayWidth = otherGame.getDisplayWidth();
        this.initialGrid = otherGame.initialGrid;
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
            rows[0] = this.grid.rows() - 1;
        }

        if (cell.getY() == this.grid.columns() - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] =  this.grid.columns() - 1;
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
        return this.grid.getCell(x,y);
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
                Cell<TState> cell = this.grid.getCell(row, column);
                newState.grid.setCellState(row, column, this.cellNextState(cell));
            }
        }

        // TODO: I only need to update the grid, not the whole game.
        this.grid = newState.grid;
    }

    // This is to be used as a Immutable borrow (RUST)
    public abstract TState cellNextState(Cell<TState> cell);


    public abstract Color getCellColor(int x, int y);

    public void restart() {
        this.grid = new Grid<>(this.initialGrid);
    }
}
