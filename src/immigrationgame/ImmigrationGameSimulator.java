package immigrationgame;

import gameoflife.GameOfLife;
import gui.GUISimulator;
import gui.Simulable;

public class ImmigrationGameSimulator implements Simulable {
    private final GUISimulator gui;
    private ImmigrationGame immigrationGame;
    private final ImmigrationGame initialState;

    public ImmigrationGameSimulator(GUISimulator gui, ImmigrationGame game) {
        this.gui = gui;
        this.immigrationGame = game;
        this.initialState = new ImmigrationGame(game);
        gui.setSimulable(this);
    }

    public ImmigrationGameSimulator(GUISimulator gui) {
        this(gui, new ImmigrationGame(5));
    }

    @Override
    public void next() {
        this.immigrationGame.nextState();
        this.draw();
    }

    public void draw() {
        this.gui.reset(); // It will be better if the clear only make temporarily death the cells that are
        // alive and not the clearing of all cells
        this.immigrationGame.draw(gui);
    }

    @Override
    public void restart() {
        this.gui.reset();
        this.immigrationGame = new ImmigrationGame(initialState);
        this.immigrationGame.draw(gui);
    }
}
