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

    public static CellState from(int state) throws Exception {
        if (state == 1) {
            return ALIVE;
        } else if (state == 0) {
            return DEAD;
        }

        switch (state) {
            case 0:
                return ALIVE;

            case 1:
                return DEAD;

            default:
                throw new Exception("WOOOOOOOOOOOOOOOOOOW");
        }
    }
}
