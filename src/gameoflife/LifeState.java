package gameoflife;

import CellularAutomata.CellState;

import java.awt.*;
import java.util.Random;

public class LifeState implements CellState {

    private boolean state;

    public LifeState() {
        Random rand = new Random();
        this.state = rand.nextBoolean();
    }

    public LifeState(boolean state) {
        this.state = state;
    }

    public boolean isAlive() {
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if  (!(obj instanceof LifeState other)) {
            return false;
        }

        return this.state == other.state;
    }

    public Color toColor() {
        if (this.isAlive()) {
            return Color.BLUE;
        } else {
            return Color.WHITE;
        }
    }
}
