package gameoflife;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestGameOfLife {
    @Test
    public void testNeighborhood() {
        GameOfLife game = new GameOfLife(5);

        Cell cellInTheMiddle = game.grid.getCell(2, 2);
        List<Cell> neighbors = game.grid.getCellNeighbors(cellInTheMiddle);
        

        Assert.assertTrue(neighbors.contains(game.grid.getCell(1, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(1, 2)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(1, 3)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(2, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(2, 3)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 2)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 3)));
        
        Assert.assertFalse(neighbors.contains(game.grid.getCell(2, 2)));
    }

    @Test
    public void testCellOnTheEdgeNeighborhood() {
        GameOfLife game = new GameOfLife(5);

        Cell cellInTheMiddle = game.grid.getCell(4, 2);
        List<Cell> neighbors = game.grid.getCellNeighbors(cellInTheMiddle);
        

        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 2)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(3, 3)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(4, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(4, 3)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(0, 1)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(0, 2)));
        Assert.assertTrue(neighbors.contains(game.grid.getCell(0, 3)));
        
    }

    @Test
    public void testNextState() {
        boolean[][] oscilator = {
                { false, false, false, false, false },
                { false, false, true, false, false },
                { false, false, true, false, false },
                { false, false, true, false, false },
                { false, false, false, false, false },
        };

        boolean[][] nextState = {
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, true, true, true, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
        };

        GameOfLife game = new GameOfLife(oscilator);
        GameOfLife gameNextState = new GameOfLife(nextState);

        game.nextState();

        for (int row = 0; row < game.grid.getRowsCount(); row++) {
            for (int col = 0; col < game.grid.getColumnsCount(); col++) {
                Cell cell = game.grid.getCell(row, col);
                Cell nextStateCell = gameNextState.grid.getCell(row, col);
                Assert.assertEquals((GameOfLifeState)cell.getState(), (GameOfLifeState)nextStateCell.getState());
            }
        }
    }
}
