package immigrationgame;

import game.Cell;
import game.Game;
import gameoflife.GameOfLife;
import gameoflife.LifeState;

import java.awt.*;
import java.util.List;
import java.util.Random;

import static java.awt.Color.*;

public class ImmigrationGame extends Game<ImmigrationState> {

    private static final int stateNumber = 4;
    public ImmigrationGame(int rows, int cols) {
        super(rows, cols);
    }

    public ImmigrationGame() {
        super();
    }

    public ImmigrationGame(ImmigrationGame game) {
        super(game);
    }

    public ImmigrationGame(int size) {
        super(size);
    }

    @Override
    public ImmigrationState newState() {
        Random random = new Random();
        return new ImmigrationState(random.nextInt(stateNumber));

    }

    @Override
    public void nextState() {
        ImmigrationGame newState = new ImmigrationGame(getRows(), getColumns());
        this.nextState(newState);
    }

    // cell pass from state k to k+1
    // if it has 3 or more neighbors on the k+1 state
    @Override
    public ImmigrationState cellNextState(Cell<ImmigrationState> cell) {
        List<Cell<ImmigrationState>> neighbors = this.getCellNeighbors(cell);
        int higherStateNeighbors = 0;
        int cellStatus = cell.getState().getImmigrationState();
        int nextStatus = (cellStatus + 1) % stateNumber;

        for (Cell<ImmigrationState> n : neighbors) {
            if (n.getState().getImmigrationState() == nextStatus) {
                higherStateNeighbors++;
            }
        }

        if  (higherStateNeighbors >= 3) {
            return new ImmigrationState(nextStatus);
        } else {
            return new ImmigrationState(cellStatus);
        }
    }



    @Override
    public Color getCellColor(int x, int y) throws IllegalStateException {
        Cell<ImmigrationState> cell = getCell(x, y);

        return switch (cell.getState().getImmigrationState()) {
            case 0 -> YELLOW;
            case 1 -> GREEN;
            case 2 -> RED;
            case 3 -> BLACK;
            default ->
                // This means my program is fault, not the user. This is not meant to be caught
                throw new IllegalStateException("Unexpected value found on Immigration game cell: " + cell.getState().getImmigrationState());
        };
    }
}
