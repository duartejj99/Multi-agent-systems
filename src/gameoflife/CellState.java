package gameoflife;

public enum CellState {
    ALIVE, DEAD;

    public static CellState from(boolean state) {
        if (state == true) {
            return ALIVE;
        } else {
            return DEAD;
        }
    }
}
