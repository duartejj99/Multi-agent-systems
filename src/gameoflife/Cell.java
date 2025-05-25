package gameoflife;

public class Cell implements Cloneable {
    private CellState state;
    private int x;

    public int getX() {
        return x;
    }

    private int y;

    public int getY() {
        return y;
    }

    public Cell(int x, int y, CellState state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public Cell(int x, int y) {
        this(x, y, CellState.DEAD);
    }

    public CellState getState() {
        return this.state;
    }

    public boolean isAlive() {
        return this.state == CellState.ALIVE;
    }

    @Override
    public Cell clone() {
        return new Cell(this.x, this.y, this.state);
    }

}
