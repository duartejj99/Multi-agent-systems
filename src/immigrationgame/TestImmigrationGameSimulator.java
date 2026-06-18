package immigrationgame;

import game.Simulator;
import gui.GUISimulator;

import java.awt.*;

public class TestImmigrationGameSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        ImmigrationGame game = new ImmigrationGame();
        Simulator<ImmigrationState> simulator = new Simulator<>(gui, game);
        gui.setSize(game.getDisplayWidth(), game.getDisplayHeight() + 70);
        simulator.draw();
    }
}
