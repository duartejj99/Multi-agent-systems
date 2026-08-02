package schelling;

import game.Cell;
import game.Game;

import java.awt.Color;
import java.util.PriorityQueue;
import schelling.SchellingConfiguration.RoomValue;

public class SchellingModel extends Game<RoomState> {

    PriorityQueue<Cell<RoomState>> queue = new PriorityQueue<>();

    public SchellingModel(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public RoomState newState() {
        // TODO: If RoomState is empty, insert the cell into the emptyCell queue
        return new RoomState();
    }

    @Override
    public void nextState() {
        SchellingModel game = new SchellingModel(getRows(), getColumns());
        this.nextState(game);
    }

    @Override
    public RoomState cellNextState(Cell<RoomState> cell) {

        //  1-Check if I want to change of neighborhood
        //  2-Search for a free place
        //  3-Move to the free place // MODIFY THE FREE PLACE COLLECTION

        long undesirableNeighborType = this.getCellNeighbors(cell).stream().filter(neighbor -> {
            RoomValue neighborType = neighbor.getState().getRoomValue();
            RoomValue myType = cell.getState().getRoomValue();

            return !neighborType.equals(myType);
        }).count();

        if (undesirableNeighborType > SchellingConfiguration.threshold) {
            // Actual cell become empty
            // Take one cell from the empty list.
            return new RoomState(RoomValue.EMPTY);
        }

        return new RoomState(cell.getState().getRoomValue());
    }

    @Override
    public Color getCellColor(int x, int y) {
        Cell<RoomState> cell = this.grid.getCell(x,y);
        return switch (cell.getState().getRoomValue()) {
            case BLACK ->
                Color.black;
            case RED ->
                Color.red;
            case ORANGE ->
                Color.ORANGE;
            case YELLOW ->
                Color.YELLOW;
            case PINK ->
                Color.PINK;
            default -> Color.GRAY;
        };
    }
}
