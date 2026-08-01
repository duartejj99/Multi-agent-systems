package schelling;

import game.CellState;
import java.util.Random;
import schelling.SchellingConfiguration.RoomValue;

public class RoomState implements CellState {

    private RoomValue state;
    public RoomState() {
        this.state = randomRoomValue();
    }

    protected static RoomValue randomRoomValue() {
        Random rand = new Random();

        int cellState = rand.nextInt(SchellingConfiguration.ROOM_VALUES_COUNT + 5);

        return switch (cellState) {
            case 0 ->
                    RoomValue.BLACK;
            case 1 ->
                    RoomValue.WHITE;
            case 2 ->
                    RoomValue.YELLOW;
            case 3 ->
                    RoomValue.RED;
            case 4 ->
                    RoomValue.BROWN;
            case 5, 6, 7, 8 , 9, 10 ->
                    RoomValue.EMPTY;
            default->
                    throw new IllegalStateException("Unexpected value: " + cellState);
        };
    }
}
