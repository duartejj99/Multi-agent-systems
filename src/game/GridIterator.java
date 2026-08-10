package game;

import java.util.Iterator;

public class GridIterator<TState extends CellState> implements Iterator<Cell<TState>> {
    private final Grid<TState> cells;
    private int cursorX;
    private int cursorY;

    public GridIterator(Grid<TState> cells) {
        this.cells = cells;
        this.cursorX = 0;
        this.cursorY = 0;
    }

    @Override
    public boolean hasNext() {
        return cursorX < cells.rows() && cursorY < cells.columns();
    }

    @Override
    public Cell<TState> next() {
        if (!hasNext())
            return null;
        Cell<TState> cell = this.cells.getCell(cursorX, cursorY);

        if (cursorY  == cells.columns() -1)
            cursorX++;
        cursorY = (cursorY + 1) % cells.columns();

        return cell;
    }
}