package immigrationgame;

import game.CellState;

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
}
