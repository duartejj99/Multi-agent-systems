package gameoflife;

import game.Cell;
import game.Game;
import game.LifeState;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOfLife extends Game<LifeState> {
    // Randomly generated

    // User sized game
    public GameOfLife(int size) {
        super(size);

    }

    public GameOfLife(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public LifeState newState() {
        return new LifeState();
    }

    public GameOfLife() {
        this(50);
    }

    // For testing purposes
    public GameOfLife(boolean[][] initialState) {
        super(initialState.length, initialState[0].length);


        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                Cell<LifeState> cell = new Cell<>(
                        row,
                        column,
                        new LifeState(initialState[row][column]));

                // TODO; Enough, this is so tight with the ArrayList model with weird access
                // I imagine something as grid.setCellState(row, col, state)
                this.grid.get(row).set(column, cell);
            }
        }
    }

    // For testing purposes
    public GameOfLife(int[][] initialState) {
        super(initialState.length, initialState[0].length);

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                try {
                    int value = initialState[row][column];
                    boolean isAlive;
                    switch (value) {
                        case 0:
                        case 1:
                            isAlive = value == 1;
                            break;
                        default:
                            throw new IllegalArgumentException("Game of Life: Cell invalid state value " + value);
                    }

                    Cell<LifeState> cell = new Cell<>(
                            row,
                            column,
                            new LifeState(isAlive));

                    // TODO; Enough, this is so tight with the ArrayList model with weird access
                    // I imagine something as grid.setCellState(row, col, state)
                    this.grid.get(row).set(column, cell);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // BUGGY, doesn't work, it is a shallow copy, not deep
    public GameOfLife(GameOfLife initialState) {
        super(initialState);
    }

    /// Calculates next state of the game of life.
    /// The new state calculated on the "newGrid" based on the
    /// actual state maintained on this.grid
    @Override
    public void nextState() {
        GameOfLife newState = new GameOfLife(getRows(), getColumns());
        for  (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell<LifeState> cell = this.grid.get(row).get(column);
                newState.grid.get(row).set(column,  this.cellNextState(cell));
            }
        }

        // TODO: I only need to update the grid, not the whole game.
        this.grid = newState.grid;
    }

    @Override
    public Color getCellColor(int x, int y) {
        if (isAlive(getCell(x,y))) {
            return Color.BLUE;
        } else {
            return Color.WHITE;
        }
    }

    // Game function
    public Cell<LifeState> cellNextState(Cell<LifeState> cell) {
        List<Cell<LifeState>> neighbors = this.getCellNeighbors(cell);
        int aliveNeighbors = 0;

        for (Cell<LifeState> n : neighbors) {
            if (isAlive(n)) {
                aliveNeighbors++;
            }
        }

        if (isAlive(cell)) {
            if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                return new Cell<>(cell.getX(), cell.getY(), new LifeState(true));
            } else {
                return new Cell<>(cell.getX(), cell.getY(), new LifeState(false));
            }
        } else {
            if (aliveNeighbors == 3) {
                return new Cell<>(cell.getX(), cell.getY(), new LifeState(true));
            } else {
                return new Cell<>(cell.getX(), cell.getY(), new LifeState(false));
            }
        }
    }

    private boolean isAlive(Cell<LifeState> n) {
        return n.getState().isAlive();
    }


    @Override
    public String toString() {
        StringBuilder game = new StringBuilder();
        for (ArrayList<Cell<LifeState>> row : grid) {
            for (Cell<LifeState> cell : row) {
                if (isAlive(cell)) {
                     game.append("[x]");
                } else {
                    game.append("[ ]");
                }
            }
            game.append("\n");
        }

        return game.toString();
    }

    @Override
    public boolean equals(Object other) {
        if ((other == this)) {
            return true;
        }

        if (!(other instanceof GameOfLife otherGame)) {
            return false;
        }

        if (
            this.getRows() != otherGame.getRows() ||
            this.getColumns() != otherGame.getColumns()
        ) {
            return false;
        }

        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getColumns(); col++) {
                Cell<LifeState> gameCell = getCell(row, col);
                Cell<LifeState> otherGameCell = otherGame.getCell(row,col);
                if (!gameCell.equals(otherGameCell)) {
                    return false;
                }
            }
        }

        return true;
    }
}
