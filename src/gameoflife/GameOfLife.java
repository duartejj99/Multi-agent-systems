package gameoflife;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.GUISimulator;
import gui.Rectangle;

public class GameOfLife {

    private static final int CELL_SIZE = 50;
    private static final int CELL_OFFSET = CELL_SIZE / 2;
    private Cell[][] grid;
    private Cell[][] oldGrid;

    public GameOfLife(int size) {
        this.grid = new Cell[5][5];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = new Cell(row, column);
            }
        }
    }


    public GameOfLife(GameOfLife initialState) {
        this.grid = initialState.grid.clone();
        this.oldGrid = initialState.oldGrid.clone();
    }

    public void nextState() {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Cell oldCell = this.oldGrid[row][column];
                this.grid[row][column] = this.cellNextState(oldCell);
            }
        }

    }

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
                return cell.clone();
            } else {
                return new Cell(CellState.DEAD);
            }
        } else {
            if (aliveNeighbors == 3) {
                return new Cell(CellState.ALIVE);
            } else {
                return cell.clone();
            }
        }

    }

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

        if (cell.getY() == this.grid.length - 1) {
            columns[2] = 0;
        }

        if (cell.getY() == 0) {
            columns[0] = this.grid.length - 1;
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

    public Cell getCell(int x, int y) {
        return this.oldGrid[x][y];
    }

    public void draw(GUISimulator gui) {
        int marcoSize = grid.length * CELL_SIZE;
        Rectangle marco = new Rectangle(marcoSize / 2, marcoSize / 2, Color.WHITE, Color.BLACK,
                marcoSize);
        gui.addGraphicalElement(marco);

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = new Rectangle(row * CELL_SIZE + CELL_OFFSET, column * CELL_SIZE + CELL_OFFSET, Color.BLUE,
                        Color.WHITE, CELL_SIZE);
                gui.addGraphicalElement(r);
            }
        }
    }
     

}
