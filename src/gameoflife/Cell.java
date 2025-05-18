package gameoflife;

public class Cell implements Cloneable {
    private CellState state;

    public Cell() {
        this(CellState.DEAD);
    }

    public Cell(CellState state) {
        this.state = state;
    }

    public void calculateNextState(Cell[] neighbors) {

    }

    @Override
    public Cell clone() {
        return new Cell(this.state);
    }

}
