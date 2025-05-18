package gameoflife;

import gui.GUISimulator;
import gui.Simulable;
public class GameOfLifeSimulator implements Simulable {
    private GUISimulator gui;
    private GameOfLife gameOfLife;
    private GameOfLife initialState;

    public GameOfLifeSimulator(GUISimulator gui) {
        this.gui = gui;
        this.gameOfLife = new GameOfLife(5);
        gui.setSimulable(this);
    }

    @Override
    public void next() {
        this.gameOfLife.nextState();
        this.draw();
    }

    public void draw() {
        this.gui.reset(); // It will be better if the clear only make temporaly death the cells that are
                          // alives and not the clearing of all cells
        this.gameOfLife.draw(gui);
    }

    @Override
    public void restart() {
        this.gui.reset();
        this.gameOfLife = new GameOfLife(initialState);
        this.gameOfLife.draw(gui);
    }


}