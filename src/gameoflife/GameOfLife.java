package gameoflife;

import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;
import java.util.List;

public class GameOfLife extends Game{

    public int getSize() {
        return super.nbOfRows;
    }

    public GameOfLife(int size) {
        super(size);

        // specific to each game
        for (int row = 0; row < this.getSize(); row++) {
            for (int column = 0; column < this.getSize(); column++) {
                GameOfLifeState state = new GameOfLifeState();
                this.grid.getCell(row, column).setState(state);
            }
        }
    }

    public GameOfLife() {
        this(50);
    }

    public GameOfLife(GameOfLife initialState) {
        super(initialState);
    }

    /// Calculates next state of the game of life.
    /// The new state calculated on the "newGrid" based on the
    /// actual state maintained on this.grid
    @Override
    public void nextState() {

        int rowsCount = this.grid.getRowsCount();
        int columnsCount = this.grid.getColumnsCount();
        Grid newGrid = new Grid(rowsCount, columnsCount);
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnsCount; column++) {
                Cell cell = this.grid.getCell(row, column);
                GameOfLifeState state = this.cellNextState(cell);
                newGrid.setCellState(row, column, state);
            }
        }

        this.grid = newGrid;
    }

    // Game function
    public GameOfLifeState cellNextState(Cell cell) {
        List<Cell> neighbors;
        neighbors = this.grid.getCellNeighbors(cell);
        int aliveNeighbors = 0;

        for (Cell n : neighbors) {
            if (isCellAlive(n)) {
                aliveNeighbors++;
            }
        }

        if (isCellAlive(cell)) {
            if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                return new GameOfLifeState(GameOfLifeState.CellState.ALIVE);
            } else {
                return new GameOfLifeState(GameOfLifeState.CellState.DEAD);
            }
        } else {
            if (aliveNeighbors == 3) {
                return new GameOfLifeState(GameOfLifeState.CellState.ALIVE);
            } else {
                return new GameOfLifeState(GameOfLifeState.CellState.DEAD);
            }
        }
    }

    private Boolean isCellAlive(Cell cell) {
        return  ((GameOfLifeState)cell.getState()).isAlive();
    }

    private Boolean isCellAlive(int row, int column) {
        Cell cell = this.grid.getCell(row, column);
        return ((GameOfLifeState)cell.getState()).isAlive();
    }

    public void draw(GUISimulator gui) {
        gui.reset();
        int marcoSizeX = grid.getRowsCount() * CELL_SIZE;
        int marcoSizeY = grid.getColumnsCount() * CELL_SIZE;
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
        for (int row = 0; row < grid.getRowsCount(); row++) {
            for (int column = 0; column < grid.getColumnsCount(); column++) {
                if (!isCellAlive(row, column)) {
                    r = new Rectangle(
                        column * CELL_SIZE + CELL_OFFSET,
                        row * CELL_SIZE + CELL_OFFSET,
                        Color.BLUE,
                        Color.WHITE,
                        CELL_SIZE
                    );
                } else {
                    r = new Rectangle(
                        column * CELL_SIZE + CELL_OFFSET,
                        row * CELL_SIZE + CELL_OFFSET,
                        Color.BLUE,
                        Color.BLUE,
                        CELL_SIZE
                    );
                }

                gui.addGraphicalElement(r);
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder game = new StringBuilder();
        for (Cell[] row : this.grid.cells) {
            for (Cell cell : row) {
                game.append(cell.toString());
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
            this.grid.getRowsCount() != otherGame.grid.getRowsCount() ||
            this.grid.getColumnsCount() != otherGame.grid.getColumnsCount()
        ) {
            return false;
        }

        for (int row = 0; row < this.grid.getRowsCount(); row++) {
            for (int col = 0; col < this.grid.getColumnsCount(); col++) {
                Cell gameCell = this.grid.getCell(row, col);
                Cell otherGameCell = otherGame.grid.getCell(row, col);
                if (!gameCell.equals(otherGameCell)) {
                    return false;
                }
            }
        }

        return true;
    }

    // For testing purposes
    public GameOfLife(boolean[][] initialState) {
        int nbOfRows = initialState.length;
        int nbOfColumns = initialState[0].length;
        super(nbOfRows, nbOfColumns);

        for (int row = 0; row < nbOfRows; row++) {
            for (int column = 0; column < nbOfColumns; column++) {
                this.grid.getCell(row, column).setState(initialState[row][column]);
            }
        }
    }

    // For testing purposes
    public GameOfLife(int[][] initialState) {
        int nbOfRows = initialState.length;
        int nbOfColumns = initialState[0].length;
        super(nbOfRows, nbOfColumns);

        for (int row = 0; row < nbOfRows; row++) {
            for (int column = 0; column < nbOfColumns; column++) {
                try {
                    this.grid.getCell(row, column).setState(initialState[row][column]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
