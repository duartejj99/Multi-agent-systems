package gameoflife;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class GameOfLife {

    private static final int CELL_SIZE = 10;
    private Cell[][] grid;
    private Cell[][] oldGrid;

    public GameOfLife(int size) {
        this.grid = new Cell[5][5];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = new Cell(CellState.DEAD);
            }
        }
    }


    public GameOfLife(GameOfLife initialState) {
        this.grid = initialState.grid.clone();
        this.oldGrid = initialState.oldGrid.clone();
    }

    public void nextState() {
        throw new UnsupportedOperationException("Unimplemented method 'next'");


    }

    public Cell[] getCellNeighbors(Cell cell){
        // Cell[] neighbors = new Cell[8];
        throw new UnsupportedOperationException("Unimplemented method 'next'");

    }

    public void draw(GUISimulator gui) {
        Rectangle marco = new Rectangle(0, 0, Color.WHITE, Color.BLACK, grid.length * CELL_SIZE + CELL_SIZE);
        gui.addGraphicalElement(marco);

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                Rectangle r = new Rectangle(row * CELL_SIZE, column * CELL_SIZE, Color.BLACK, Color.BLUE, CELL_SIZE);
                gui.addGraphicalElement(r);
            }
        }
    }
     

}
