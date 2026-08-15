package MultiAgentSystem;

import gui.GUISimulator;
import gui.Rectangle;

import java.awt.*;

public abstract class MultiAgentSystem{

    public abstract AutonomousAgents agents();
    public abstract AutonomousAgents initialAgents();
    protected int windowSizeX;
    protected int windowSizeY;

    public abstract void nextState();

    /**
     * Restart the MultiAgentSystem to its initial state.
     */
    public abstract void restart();

    /*
        Draws the game on the GUI.
     */
    public void draw(GUISimulator gui) {
        gui.reset();

        int windowSizeX = getWindowSizeX();
        int windowSizeY = getWindowSizeY();

        Rectangle marco = new Rectangle(
                windowSizeX / 2,
                windowSizeY / 2,
                Color.GREEN,
                Color.GREEN,
                windowSizeX + 1,
                windowSizeY + 1
        );
        gui.addGraphicalElement(marco);

        for (Agent a : agents()) {
            gui.addGraphicalElement(a.getGraphicalElement());
        }
    }

    public abstract int getWindowSizeX();
    public abstract int getWindowSizeY();
}
