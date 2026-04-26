package gameoflife;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.GUISimulator;
import gui.Rectangle;

public class GameOfLife {

    private static final int CELL_SIZE = 20;
    private static final int CELL_OFFSET = CELL_SIZE / 2;
    private final int size;

    public int getSize() {
        return size;
    }

    private Cell[][] grid;

    // Randomly generated

    // User sized game
    public GameOfLife(int size) {

        Random randomizer = new Random();

        this.grid = new Cell[size][size];
        this.size = size;

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {

                CellState state = CellState.from(randomizer.nextBoolean());
                grid[row][column] = new Cell(row, column, state);
            }
        }
    }

    public GameOfLife() {
        this(50);
    }

    // For testing purposes
    public GameOfLife(boolean[][] initialState) {
        int nbOfRows = initialState.length;
        int nbOfColumns = initialState[0].length;
        this.grid = new Cell[nbOfRows][nbOfColumns];
        this.size = nbOfRows;

        for (int row = 0; row < nbOfRows; row++) {
            for (int column = 0; column < nbOfColumns; column++) {
                grid[row][column] = new Cell(row, column, CellState.from(initialState[row][column]));
            }
        }
    }

    // For testing purposes
    public GameOfLife(int[][] initialState) {
        int nbOfRows = initialState.length;
        int nbOfColumns = initialState[0].length;
        this.grid = new Cell[nbOfRows][nbOfColumns];
        this.size = nbOfRows;

        for (int row = 0; row < nbOfRows; row++) {
            for (int column = 0; column < nbOfColumns; column++) {
                try {
                    grid[row][column] = new Cell(row, column, CellState.from(initialState[row][column]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // BUGGY, doesn't work, it is a shallow copy, not deep
    public GameOfLife(GameOfLife initialState) {
        this.grid = initialState.grid.clone();
        this.size = initialState.size;
    }

    // Game function
    // TODO: BUG => if the cell is m x n,
    // there will addresses pointing to null on the matrix
    public void nextState() {
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Cell cell = this.grid[row][column];
                newGrid[row][column] = this.cellNextState(cell);
            }
        }

        this.grid = newGrid;
    }

    // Game function
    public Cell cellNextState(Cell cell) {
        List<Cell> neighbors = this.getCellNeighbors(cell);
        int aliveNeighbors = 0;

        for (Cell n : neighbors) {
            if (n.isAlive()) {
                aliveNeighbors++;
            }
        }

        if (cell.isAlive()) {
            if (aliveNeighbors == 2 || aliveNeighbors == 3) {
                return new Cell(cell.getX(), cell.getY(), CellState.ALIVE);
            } else {
                return new Cell(cell.getX(), cell.getY(), CellState.DEAD);
            }
        } else {
            if (aliveNeighbors == 3) {
                return new Cell(cell.getX(), cell.getY(), CellState.ALIVE);
            } else {
                return new Cell(cell.getX(), cell.getY(), CellState.DEAD);

            }
        }

    }

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

        if (cell.getX() == this.grid.length - 1) {
            rows[2] = 0;
        }

        if (cell.getX() == 0) {
            rows[0] = this.grid.length - 1;
        }

        if (cell.getY() == this.grid[0].length - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] = this.grid[0].length - 1;
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

    // Grid function
    public Cell getCell(int x, int y) {
        return this.grid[x][y];
    }

    public void draw(GUISimulator gui) {
        gui.reset();
        int marcoSizeX = grid.length * CELL_SIZE;
        int marcoSizeY = grid[0].length * CELL_SIZE;
        Rectangle marco = new Rectangle(marcoSizeX / 2, marcoSizeY / 2, Color.WHITE, Color.BLACK,
                marcoSizeX * marcoSizeY);
        gui.addGraphicalElement(marco);

        Rectangle r;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (this.grid[row][column].getState() == CellState.DEAD) {
                    r = new Rectangle(column * CELL_SIZE + CELL_OFFSET, row * CELL_SIZE + CELL_OFFSET,
                                    Color.BLUE,
                            Color.WHITE, CELL_SIZE);
                } else {
                    r = new Rectangle(column * CELL_SIZE + CELL_OFFSET, row * CELL_SIZE + CELL_OFFSET,
                                    Color.BLUE,
                            Color.BLUE, CELL_SIZE);
                }

                gui.addGraphicalElement(r);
            }
        }
    }

    @Override
    public String toString() {
        String game = new String();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                game += cell.toString();
            }
            game += "\n";
        }

        return game;
    }

    @Override
    public boolean equals(Object other) {

        if ((other == this)) {
            return true;
        }

        if (!(other instanceof GameOfLife)) {
            return false;
        }

        GameOfLife otherGame = (GameOfLife) other;

        if (this.grid.length != otherGame.grid.length || this.grid[0].length != otherGame.grid[0].length) {
            return false;
        }

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[0].length; col++) {
                Cell gameCell = this.grid[row][col];
                Cell otherGameCell = otherGame.grid[row][col];
                if (!gameCell.equals(otherGameCell)) {
                    return false;
                }
            }
        }

        return true;

    }

}
