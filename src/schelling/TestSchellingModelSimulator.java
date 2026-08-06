package schelling;

import game.Simulator;
import gui.GUISimulator;
import immigrationgame.ImmigrationGame;
import immigrationgame.ImmigrationState;

import java.awt.*;

public class TestSchellingModelSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        SchellingModel game = new SchellingModel();
        Simulator<RoomState> simulator = new Simulator<>(gui, game);
        gui.setSize(game.getDisplayWidth(), game.getDisplayHeight() + 70);
        simulator.draw();
    }
}