package gameoflife;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameOfLife {
    // public static void main(String[] args) {

    // }

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
}
