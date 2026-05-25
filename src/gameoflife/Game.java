package gameoflife;

public abstract class Game {

    //region Design variables
    protected static final int CELL_SIZE = 20;
    protected static final int CELL_OFFSET = CELL_SIZE / 2;
    protected final int displayHeight;
    protected final int displayWidth;
    public int getDisplayHeight() {return  displayHeight;}
    public int getDisplayWidth() {return  displayWidth;}
    // end region

    protected Grid grid;
    protected final int nbOfRows;

    public Game(int rows, int columns) {
        this.grid = new Grid(rows, columns);
        this.nbOfRows = rows;
        this.displayWidth = grid.getRowsCount() * CELL_SIZE;
        this.displayHeight = grid.getColumnsCount() * CELL_SIZE;
    }

    public Game(int size) {
        this(size, size);
    }

    public Game() {
        this(50);
    }


    // BUGGY, doesn't work, it is a shallow copy, not deep
    public Game(Game initialState) {
        this.grid = new Grid(initialState.grid);
        this.nbOfRows = initialState.nbOfRows;
        displayHeight = initialState.getDisplayHeight();
        displayWidth = initialState.getDisplayWidth();
    }

    public abstract void nextState();


}
