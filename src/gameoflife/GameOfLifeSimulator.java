package gameoflife;

import gui.GUISimulator;
import gui.Simulable;
public class GameOfLifeSimulator implements Simulable {
    private final GUISimulator gui;
    private GameOfLife gameOfLife;
    private final GameOfLife initialState;

    public GameOfLifeSimulator(GUISimulator gui, GameOfLife game) {
        this.gui = gui;
        this.gameOfLife = game;
        this.initialState = new GameOfLife(game);
        gui.setSimulable(this);
    }

    public GameOfLifeSimulator(GUISimulator gui) {
        this(gui, new GameOfLife(5));
    }

    @Override
    public void next() {
        this.gameOfLife.nextState();
        this.draw();
    }

    public void draw() {
        this.gui.reset(); // It will be better if the clear only make temporarily death the cells that are
                          // alive and not the clearing of all cells
        this.gameOfLife.draw(gui);
    }

    @Override
    public void restart() {
        this.gui.reset();
        this.gameOfLife = new GameOfLife(initialState);
        System.out.println(this.gameOfLife.toString());
        this.gameOfLife.draw(gui);
    }


}