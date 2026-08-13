package game;

import MultiAgentSystem.Agent;
import gui.GraphicalElement;

public class Cell<TState extends CellState> extends Agent implements Cloneable {
    private TState state;
    private final int x;

    public int getX() {
        return x;
    }

    private final int y;

    public int getY() {
        return y;
    }

    public Cell(int x, int y, TState state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }


    public TState getState() {
        return this.state;
    }
    public void setState(TState state) {
         this.state = state;
    }


    @Override
    public Cell<TState> clone() {
        return new Cell<TState>(this.x, this.y, this.state);
    }


    // TODO: Ask question about how this works with generics
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cell otherCell)) {
            return false;
        }
        return this.state.equals(otherCell.state)  && this.getX() == otherCell.getX() && this.getY() == otherCell.getY();
    }

    @Override
    public GraphicalElement getGraphicalElement() {
        return null;
    }
}
