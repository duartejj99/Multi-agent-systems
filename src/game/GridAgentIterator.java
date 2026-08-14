package game;

import MultiAgentSystem.Agent;

import java.util.Iterator;

public class GridAgentIterator<TState extends CellState> implements Iterator<Agent> {
    private final Iterator<Agent> gridIterator;

    public GridAgentIterator(Grid<TState> cells) {
        this.gridIterator = cells.iterator();
    }
    @Override
    public boolean hasNext() {
        return this.gridIterator.hasNext();
    }
    @Override
    public Agent next() {
        return this.gridIterator.next();
    }
}