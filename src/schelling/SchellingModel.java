package schelling;

import game.Cell;
import game.Game;

import java.awt.Color;
import java.util.*;

import game.Grid;
import schelling.SchellingConfiguration.RoomValue;
import static schelling.SchellingConfiguration.TOLERANCE_THRESHOLD;

public class SchellingModel extends Game<RoomState> {

    // TODO: Instead of a List, I would like to randomize the empty room that a family choose to get
    // In this case a hashmap could serve better for improving access time
    // But how to get a random value from a hashmap or hashset?
    List<Cell<RoomState>> emptyRooms;
    List<Cell<RoomState>> initialEmptyRooms;

    public SchellingModel(int rows, int columns) {
        super(rows, columns);

        this.emptyRooms = new LinkedList<>();
        this.initialEmptyRooms = new LinkedList<>();

        for (ArrayList<Cell<RoomState>> cell : this.grid.cells()) {
            for (Cell<RoomState> c : cell) {
                if (c.getState().getRoomValue().equals(RoomValue.EMPTY)) {
                    emptyRooms.add(c.clone());
                }
            }
        }
        this.initialEmptyRooms = new LinkedList<>(emptyRooms);

    }

    public SchellingModel(SchellingModel model) {
        super(model);
        this.emptyRooms = new LinkedList<>(model.emptyRooms);
        this.initialEmptyRooms = new LinkedList<>(model.initialEmptyRooms);
    }

    public SchellingModel() {
        this(50, 50);
    }

    @Override
    public RoomState newState() {
        return new RoomState();
    }

    @Override
    public void nextState() {
        SchellingModel game = new SchellingModel(this);
        this.nextState(game);
    }

    @Override
    public RoomState cellNextState(Cell<RoomState> cell) {
        assert(!cell.getState().getRoomValue().equals(RoomValue.EMPTY));
        RoomValue myType = cell.getState().getRoomValue();
        long undesirableNeighborCount = this.getCellNeighbors(cell).stream()
                .filter(maybeNeighbor -> !maybeNeighbor.getState().getRoomValue().equals(RoomValue.EMPTY))
                .filter(neighbor -> !neighbor.getState().getRoomValue().equals(myType)).count();

        if (undesirableNeighborCount > TOLERANCE_THRESHOLD && !emptyRooms.isEmpty()) {
            return new RoomState(RoomValue.EMPTY);
        }

        return new RoomState(cell.getState().getRoomValue());
    }

    public void nextState(SchellingModel newState) {
        for (Cell<RoomState> busyCell : collectBusyRooms()) {
            RoomState nextCellState = this.cellNextState(busyCell); // empty or the same
            if (nextCellState.getRoomValue().equals(RoomValue.EMPTY)) {
                Cell<RoomState> newBusyCell = moveBusyCellFamilyIntoEmptyRoom(busyCell);
                newState.grid.setCellState(newBusyCell.getX(), newBusyCell.getY(), newBusyCell.getState());
            }
            newState.grid.setCellState(busyCell.getX(), busyCell.getY(), nextCellState.clone());
        }

        this.grid = newState.grid;
    }

    private Cell<RoomState> moveBusyCellFamilyIntoEmptyRoom(Cell<RoomState> busyCell) {
        Cell<RoomState> newBusyCell = pollEmptyRoom();
        Cell<RoomState> newEmptyRoom = new Cell<>(busyCell.getX(), busyCell.getY(), new RoomState(RoomValue.EMPTY));
        newBusyCell.setState(busyCell.getState().clone());
        this.emptyRooms.add(newEmptyRoom);

        return newBusyCell;
    }

    private ArrayList<Cell<RoomState>> collectBusyRooms() {
        // TODO: This could be an iterator over all members of grid.
        ArrayList<Cell<RoomState>> busyRooms = new ArrayList<>();
        for  (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell<RoomState> cell = this.grid.getCell(row, column);
                if (!cell.getState().getRoomValue().equals(RoomValue.EMPTY)) {
                    busyRooms.add(cell);
                }
            }
        }
        return busyRooms;
    }

    private Cell<RoomState> pollEmptyRoom() {
        Random random = new Random();
        int index = random.nextInt(this.emptyRooms.size());
        return this.emptyRooms.remove(index);
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

    @Override
    public void restart() {
        this.grid = new Grid<>(this.initialGrid);
        this.emptyRooms = new LinkedList<>(this.initialEmptyRooms);
    }
}
