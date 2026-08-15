package immigrationgame;

import CellularAutomata.CellState;

import java.awt.*;

import static java.awt.Color.*;
import static java.awt.Color.BLACK;

public class ImmigrationState implements CellState {

    private int state;

    public ImmigrationState(int state) {
        this.state = state;
    }

    public int getImmigrationState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Color toColor() {
        return switch (this.getImmigrationState()) {
            case 0 -> YELLOW;
            case 1 -> GREEN;
            case 2 -> RED;
            case 3 -> BLACK;
            default ->
                // This means my program is fault, not the user. This is not meant to be caught
                    throw new IllegalStateException("Unexpected value found on Immigration game cell: " + this.getImmigrationState());
        };
    }
}
