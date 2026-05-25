package gameoflife;

import java.util.Random;

public class GameOfLifeState {
    public enum CellState {
        ALIVE, DEAD;
    }

    private final CellState state;
    public GameOfLifeState(boolean state) {
        if (state) {
            this.state = CellState.ALIVE;
        } else {
            this.state = CellState.DEAD;
        }
    }

    public GameOfLifeState(CellState state) {
        this.state = state;
    }

    public  GameOfLifeState (int state) throws Exception {
        switch (state) {
            case 0 ->this.state = CellState.ALIVE;
            case 1 -> this.state = CellState.DEAD;
            default -> throw new Exception("WOOOOOOOOOOOOOOOOOOW");
        };

    }
    public GameOfLifeState() {
        Random randomizer = new Random();
        this.state = (randomizer.nextInt(2) == 0) ? CellState.ALIVE : CellState.DEAD;
    }

    public Boolean isAlive() {
        return state == CellState.ALIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        return this.state == ((GameOfLifeState)o).state;
    }
}

