package immigrationgame;

import gui.GUISimulator;

import java.awt.*;

public class TestImmigrationGameSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        ImmigrationGame game = new ImmigrationGame();
        ImmigrationGameSimulator simulator = new ImmigrationGameSimulator(gui, game);
        gui.setSize(game.getDisplayWidth(), game.getDisplayHeight() + 70);
        simulator.draw();
    }
}
