package gameoflife;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestGameOfLife {
    @Test
    public void testNeighborhood() {
        GameOfLife game = new GameOfLife(5);

        Cell cellInTheMiddle = game.getCell(2, 2);
        List<Cell> neighbors = game.getCellNeighbors(cellInTheMiddle);
        

        Assert.assertTrue(neighbors.contains(game.getCell(1, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(1, 2)));
        Assert.assertTrue(neighbors.contains(game.getCell(1, 3)));
        Assert.assertTrue(neighbors.contains(game.getCell(2, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(2, 3)));
        Assert.assertTrue(neighbors.contains(game.getCell(3, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(3, 2)));
        Assert.assertTrue(neighbors.contains(game.getCell(3, 3)));
        
        Assert.assertFalse(neighbors.contains(game.getCell(2, 2)));
    }

    @Test
    public void testCellOnTheEdgeNeighborhood() {
        GameOfLife game = new GameOfLife(5);

        Cell cellInTheMiddle = game.getCell(4, 2);
        List<Cell> neighbors = game.getCellNeighbors(cellInTheMiddle);
        

        Assert.assertTrue(neighbors.contains(game.getCell(3, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(3, 2)));
        Assert.assertTrue(neighbors.contains(game.getCell(3, 3)));
        Assert.assertTrue(neighbors.contains(game.getCell(4, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(4, 3)));
        Assert.assertTrue(neighbors.contains(game.getCell(0, 1)));
        Assert.assertTrue(neighbors.contains(game.getCell(0, 2)));
        Assert.assertTrue(neighbors.contains(game.getCell(0, 3)));
        
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

        Assert.assertEquals(game, gameNextState);
    }
}
