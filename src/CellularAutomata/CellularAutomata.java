package CellularAutomata;

import MultiAgentSystem.MultiAgentSystem;
import game.CellState;
import game.Grid;

public class CellularAutomata<TState extends CellState> extends MultiAgentSystem {
    @Override
    public void nextState() {

    }

    @Override
    public int getWindowSizeX() {
        return 0;
    }

    @Override
    public int getWindowSizeY() {
        return 0;
    }
}
