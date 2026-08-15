package schelling;

import CellularAutomata.CellularAutomata;
import CellularAutomata.Cell;

import java.util.*;
import java.util.stream.Collectors;

import CellularAutomata.Grid;
import schelling.SchellingConfiguration.RoomValue;
import static schelling.SchellingConfiguration.TOLERANCE_THRESHOLD;

public class SchellingModel extends CellularAutomata<RoomState> {

    // TODO: Instead of a List, I would like to randomize the empty room that a family choose to get
    // In this case a hashmap could serve better for improving access time
    // But how to get a random value from a hashmap or hashset?
    List<Cell<RoomState>> emptyRooms;
    List<Cell<RoomState>> initialEmptyRooms;

    public SchellingModel(int rows, int columns) {
        super(rows, columns);
        this.emptyRooms = new LinkedList<>();
        this.initialEmptyRooms = new LinkedList<>();

        this.emptyRooms = this.grid.stream()
                        .filter(cell -> cell.getState().isRoomEmpty())
                        .map(Cell::clone)
                        .collect(Collectors.toCollection(LinkedList::new));
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
    public CellularAutomata<RoomState> newCellularAutomata() {
        return new SchellingModel(this);
    }

    /*
        "Overrides" the nextState method used on the "Game" base class
    */
    public void nextState(SchellingModel newState) {
        for (Cell<RoomState> busyCell : collectBusyRooms()) {
            RoomState nextCellState = this.cellNextState(busyCell); // empty or the same
            if (nextCellState.isRoomEmpty()) {
                Cell<RoomState> newBusyCell = moveBusyCellFamilyIntoEmptyRoom(busyCell);
                newState.grid.setCellState(newBusyCell.getX(), newBusyCell.getY(), newBusyCell.getState());
            }
            newState.grid.setCellState(busyCell.getX(), busyCell.getY(), nextCellState.clone());
        }

        this.grid = newState.grid;
    }

    @Override
    public RoomState cellNextState(Cell<RoomState> cell) {
        assert(!cell.getState().isRoomEmpty());
        RoomValue myType = cell.getState().getRoomValue();
        long undesirableNeighborCount =
                        this.getCellNeighbors(cell)
                        .stream()
                        .filter(maybeNeighbor -> !maybeNeighbor.getState().isRoomEmpty())
                        .filter(neighbor -> !neighbor.getState().getRoomValue().equals(myType))
                        .count();

        if (undesirableNeighborCount > TOLERANCE_THRESHOLD && !emptyRooms.isEmpty()) {
            return new RoomState(RoomValue.EMPTY);
        }

        return new RoomState(cell.getState().getRoomValue());
    }

    private Cell<RoomState> moveBusyCellFamilyIntoEmptyRoom(Cell<RoomState> busyCell) {
        Cell<RoomState> newBusyCell = pollEmptyRoom();
        Cell<RoomState> newEmptyRoom = new Cell<RoomState>(busyCell.getX(), busyCell.getY(), new RoomState(RoomValue.EMPTY));
        newBusyCell.setState(busyCell.getState().clone());
        this.emptyRooms.add(newEmptyRoom);

        return newBusyCell;
    }

    private ArrayList<Cell<RoomState>> collectBusyRooms() {
        // TODO: This could be an iterator over all members of grid.
        return this.grid
                .stream()
                .filter(cell -> !cell.getState().isRoomEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private Cell<RoomState> pollEmptyRoom() {
        Random random = new Random();
        int index = random.nextInt(this.emptyRooms.size());
        return this.emptyRooms.remove(index);
    }

    @Override
    public void restart() {
        this.grid = new Grid<RoomState>(this.initialGrid);
        this.emptyRooms = new LinkedList<>(this.initialEmptyRooms);
    }
}
