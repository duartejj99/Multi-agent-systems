package CellularAutomata;

import MultiAgentSystem.Agent;

import java.util.Iterator;

public class GridAgentIterator<TState extends CellState> implements Iterator<Agent> {
    private final Iterator<Cell<TState>> gridIterator;

    public GridAgentIterator(Grid<TState> grid) {
        this.gridIterator = grid.cells();
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