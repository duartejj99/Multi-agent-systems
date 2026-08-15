package CellularAutomata;

import MultiAgentSystem.MultiAgentSystem;
import MultiAgentSystem.AutonomousAgents;

import java.util.List;

public abstract class CellularAutomata<TState extends CellState> extends MultiAgentSystem {

    public static final int CELL_SIZE = 20;
    public static final int CELL_OFFSET = CELL_SIZE / 2;

    public CellularAutomata(int rows, int columns) {
        this.grid =  new Grid<>(rows, columns);
        this.initialGrid = new Grid<>(rows, columns);
        this.windowSizeX = this.grid.rows() * CELL_SIZE;
        this.windowSizeY = this.grid.columns() * CELL_SIZE;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell<TState> cell = new Cell<TState>(row, column, newState());
                grid.setCell(row, column, cell);
                initialGrid.setCell(row, column, cell.clone());
            }
        }
    }

    public CellularAutomata() {
        this(50);
    }
    // User sized game
    public CellularAutomata(int size) {
        this(size, size);
    }

    public CellularAutomata(CellularAutomata<TState> otherAutomata) {
        // how to clone this. // we need to do a deep copy.

        // This is a clone, a proper clone
        // If I don't clone, the restart mechanism won't work
        grid = new Grid<>(otherAutomata.grid);
        initialGrid = otherAutomata.initialGrid;
        windowSizeX = otherAutomata.getWindowSizeX();
        windowSizeY = otherAutomata.getWindowSizeY();
    }


    @Override
    public AutonomousAgents agents() {
        return this.grid;
    }

    @Override
    public AutonomousAgents initialAgents() {
        return this.initialGrid;
    }


    @Override
    public void restart() {
        this.grid = this.initialGrid.clone();
    }


    @Override
    public int getWindowSizeX() {
        return windowSizeX;
    }

    @Override
    public int getWindowSizeY() {
        return windowSizeY;
    }

    /*
        Abstract method to generate a new random
        cell state for the cells being created on a
        Cellular Automata instance.
     */
    public abstract TState newState();
    // This is to be used as a Immutable borrow (RUST)
    public abstract TState cellNextState(Cell<TState> cell);


    /// Calculates next state of the game.
    /// The new state calculated on the "newGrid" based on the
    /// actual state maintained on this.grid
    public void nextState() {
        CellularAutomata<TState> newState = newCellularAutomata();
        for  (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell<TState> cell = this.grid.getCell(row, column);
                newState.grid.setCellState(row, column, this.cellNextState(cell));
            }
        }
        this.grid = newState.grid;
    }

    public abstract CellularAutomata<TState> newCellularAutomata();

    public int getColumns() {
        return this.grid.columns();
    }

    public int getRows() {
        return this.grid.rows();
    }

    public List<Cell<TState>> getCellNeighbors(Cell<TState> cell) {
        return this.grid.getCellNeighbors(cell);
    }

    /// Gets the Cell of the given coordinates.
    public Cell<TState> getCell(int x, int y) {
        return this.grid.getCell(x,y);
    }

    //private Cell<TState>[][] grid;

    // I changed from a [][] to a List<List<>> because
    // [][] checks types at runtime as well, and at run time
    // generics disappear, so you don't know which type of array you are handling.
    // List only
    protected Grid<TState> grid;
    protected final Grid<TState> initialGrid;
}
