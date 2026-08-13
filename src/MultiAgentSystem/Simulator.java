package MultiAgentSystem;

import game.CellState;
import game.Game;
import gui.GUISimulator;
import gui.Simulable;

public class Simulator<TState extends CellState> implements Simulable {
    private final GUISimulator gui;
    private final MultiAgentSystem multiAgentSystem;

    public Simulator(GUISimulator gui, MultiAgentSystem multiAgentSystem) {
        this.gui = gui;
        this.multiAgentSystem = multiAgentSystem;
        gui.setSimulable(this);
    }

    @Override
    public void next() {
        this.multiAgentSystem.nextState();
        this.draw();
    }

    public void draw() {
        this.gui.reset(); // It will be better if the clear only make temporarily death the cells that are
                          // alive and not the clearing of all cells
        this.multiAgentSystem.draw(gui);
    }

    @Override
    public void restart() {
        this.gui.reset();
        multiAgentSystem.restart();
        this.multiAgentSystem.draw(gui);
    }


}