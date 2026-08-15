package schelling;

import MultiAgentSystem.Simulator;
import gui.GUISimulator;

import java.awt.*;

public class TestSchellingModelSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        SchellingModel game = new SchellingModel();
        Simulator simulator = new Simulator(gui, game);
        gui.setSize(game.getWindowSizeX(), game.getWindowSizeY() + 70);
        simulator.draw();
    }
}