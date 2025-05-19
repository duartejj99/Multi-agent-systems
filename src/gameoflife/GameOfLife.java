package gameoflife;

import java.awt.Color;

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
        Cell[] neighbors = this.getCellNeighbors(cell);
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

    public Cell[] getCellNeighbors(Cell cell){
        if (cell.getX() == this.grid.length - 1) {

        }
        // Cell[] neighbors = new Cell[8];
        throw new UnsupportedOperationException("Unimplemented method 'next'");

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
