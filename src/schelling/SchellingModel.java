package schelling;

import game.Cell;
import game.Game;

import java.awt.*;
import java.util.PriorityQueue;

public class SchellingModel extends Game<RoomState> {

    PriorityQueue<Cell<RoomState>> queue = new PriorityQueue<>();

    @Override
    public RoomState newState() {
        return new RoomState();
    }

    @Override
    public void nextState() {

    }

    @Override
    public RoomState cellNextState(Cell<RoomState> cell) {


        return null;
    }

    @Override
    public Color getCellColor(int x, int y) {
        return null;
    }
}
