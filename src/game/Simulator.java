package game;

import gameoflife.GameOfLife;
import gui.GUISimulator;
import gui.Simulable;

public class Simulator<TState extends CellState> implements Simulable {
    private final GUISimulator gui;
    private final Game<TState> game;

    public Simulator(GUISimulator gui, Game<TState> game) {
        this.gui = gui;
        this.game = game;
        gui.setSimulable(this);
    }

    @Override
    public void next() {
        this.game.nextState();
        this.draw();
    }

    public void draw() {
        this.gui.reset(); // It will be better if the clear only make temporarily death the cells that are
                          // alive and not the clearing of all cells
        this.game.draw(gui);
    }

    @Override
    public void restart() {
        this.gui.reset();
        game.restart();
        this.game.draw(gui);
    }


}