package schelling;

import game.Cell;
import game.Game;

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Random;

public class SchellingModel extends Game<RoomState> {

    private static final int FAMILY_TYPES = 5;
    private int thresholdK = 6;
    PriorityQueue<Cell<RoomState>> queue = new PriorityQueue<>();
    @Override
    public RoomState newState() {
        Random rand = new Random();
        int cellState = rand.nextInt(FAMILY_TYPES + 1);

        // how to trick the randomizer to give me more 6 than anything else?
        // I know, if it's 0 or 6, then is empty, the rest corresponds to the other. I see
        // I include more possibilites that represents the emptiness.

        return null;
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
