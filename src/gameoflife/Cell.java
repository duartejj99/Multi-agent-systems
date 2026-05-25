package gameoflife;

public class Cell implements Cloneable {
    protected int x;
    private Object state;

    public int getX() {
        return x;
    }

    protected int y;

    public int getY() {
        return y;
    }

    public Cell(int x, int y, Object state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getState() { return this.state;}

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public Cell clone() {
        return new Cell(this.x, this.y, this.state);
    };


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cell otherCell)) {
            return false;
        }
        return this.getX() == otherCell.getX() && this.getY() == otherCell.getY() &&  this.state == otherCell.state;
    }
}
