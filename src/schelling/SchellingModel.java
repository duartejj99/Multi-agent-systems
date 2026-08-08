package schelling;

import game.Cell;
import game.Game;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

import game.Grid;
import schelling.SchellingConfiguration.RoomValue;

public class SchellingModel extends Game<RoomState> {

    Queue<Cell<RoomState>> emptyRooms;
    Queue<Cell<RoomState>> initialEmptyRooms;

    public SchellingModel(int rows, int columns) {
        super(rows, columns);

        // collectInitialEmptyRooms
        this.emptyRooms = new ArrayDeque<>();
        for (ArrayList<Cell<RoomState>> cell : this.grid.cells()) {
            for (Cell<RoomState> c : cell) {
                if (c.getState().getRoomValue().equals(RoomValue.EMPTY))
                    // TODO: I don't need new cells, only to track the (x,y) pos
                    emptyRooms.add(c.clone());
            }
        }

        this.initialEmptyRooms = new ArrayDeque<>(emptyRooms);
    }

    public SchellingModel(SchellingModel model) {
        super(model);
        this.emptyRooms = new ArrayDeque<>(model.emptyRooms);
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
        //  1-Check if I want to change of neighborhood and there is a free place
        long undesirableNeighborCount = this.getCellNeighbors(cell).stream().filter(neighbor -> {
            RoomValue neighborType = neighbor.getState().getRoomValue();
            RoomValue myType = cell.getState().getRoomValue();

            return !neighborType.equals(RoomValue.EMPTY) &&  !neighborType.equals(myType);
        }).count();

        if (undesirableNeighborCount > SchellingConfiguration.threshold && !emptyRooms.isEmpty()) {
            // IF EMPTY SPACE TO MOVE -> Actual cell become empty
            return new RoomState(RoomValue.EMPTY);
        }

        return new RoomState(cell.getState().getRoomValue());
    }

    public void nextState(SchellingModel newState) {
        // 1. Build non empty room list
        List<Cell<RoomState>> busyRooms = new ArrayList<>();
        // TODO: This could be an iterator over all members of grid.
        for  (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                Cell<RoomState> cell = this.grid.getCell(row, column);
                if (!cell.getState().getRoomValue().equals(RoomValue.EMPTY)) {
                    busyRooms.add(cell);
                }
            }
        }
        // Update them
        for (Cell<RoomState> busyCell : busyRooms) {
            RoomState nextCellState = this.cellNextState(busyCell); // empty or the same
            if (nextCellState.getRoomValue().equals(RoomValue.EMPTY)) {
                // Pick an empty room
                Cell<RoomState> newFamilyHouse = this.emptyRooms.poll();
                // Warning because poll is not verified to be not null,
                //but If I have a RoomValue.Empty, that means I already did the
                // verification on the cellNextState, this should be improved.
                //newFamilyHouse.setState(busyCell.getState());
                newState.grid.setCellState(newFamilyHouse.getX(), newFamilyHouse.getY(), busyCell.getState().clone());
                busyCell.setState(nextCellState.clone());
                this.emptyRooms.add(busyCell.clone());
            }
            newState.grid.setCellState(busyCell.getX(), busyCell.getY(), nextCellState.clone());
        }

        // TODO: I only need to update the grid, not the whole game.
        this.grid = newState.grid;
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

    // TODO: For the game restart I need to save the emptyRooms on an initialState.
    @Override
    public void restart() {
        this.grid = new Grid<>(this.initialGrid);
        this.emptyRooms = new ArrayDeque<>(this.initialEmptyRooms);
    }
}
