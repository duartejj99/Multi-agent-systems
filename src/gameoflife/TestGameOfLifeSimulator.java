package gameoflife;

import java.awt.Color;

import game.Simulator;
import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        GameOfLife game = new GameOfLife();
        Simulator<LifeState> simulator = new Simulator<>(gui, game);
        gui.setSize(game.getDisplayWidth(), game.getDisplayHeight() + 70);
        simulator.draw();


    }
}
