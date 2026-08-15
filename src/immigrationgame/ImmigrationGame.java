package immigrationgame;

import CellularAutomata.CellularAutomata;
import CellularAutomata.Cell;

import java.util.List;
import java.util.Random;

import static immigrationgame.ImmigrationGameConfiguration.stateNumber;

public class ImmigrationGame extends CellularAutomata<ImmigrationState> {

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
    // cell pass from state k to k+1
    // if it has 3 or more neighbors on the k+1 state
    @Override
    public ImmigrationState cellNextState(Cell<ImmigrationState> cell) {
        List<Cell<ImmigrationState>> neighbors = this.getCellNeighbors(cell);
        int cellStatus = cell.getState().getImmigrationState();
        int nextStatus = (cellStatus + 1) % stateNumber;

        long higherStateNeighbors = this.getCellNeighbors(cell)
                .stream()
                .filter(n -> n.getState().getImmigrationState() == nextStatus)
                .count();

        if  (higherStateNeighbors >= 3) {
            return new ImmigrationState(nextStatus);
        } else {
            return new ImmigrationState(cellStatus);
        }
    }

    @Override
    public CellularAutomata<ImmigrationState> newCellularAutomata() {
        return new ImmigrationGame(this.getRows(), this.getColumns());
    }
}
