package schelling;

import game.Cell;
import game.CellState;
import java.util.Random;
import schelling.SchellingConfiguration.RoomValue;

public class RoomState implements CellState, Cloneable {

    private RoomValue state;
    public RoomState() {
        this.state = randomRoomValue();
    }

    public RoomState(RoomValue roomValue) {
        this.state = roomValue;
    }

    @Override
    public RoomState clone() {
        return new RoomState(this.state);
    }

    protected static RoomValue randomRoomValue() {
        Random rand = new Random();

        int cellState = rand.nextInt(SchellingConfiguration.ROOM_VALUES_COUNT + 1);

        return switch (cellState) {
            case 0 ->
                    RoomValue.BLACK;
            case 1 ->
                    RoomValue.PINK;
            case 2 ->
                    RoomValue.YELLOW;
            case 3 ->
                    RoomValue.RED;
            case 4 ->
                    RoomValue.ORANGE;
            default->
                    RoomValue.EMPTY;
        };
    }

    public RoomValue getRoomValue() {
        return state;
    }
}
