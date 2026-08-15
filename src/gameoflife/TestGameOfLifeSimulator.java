package gameoflife;

import java.awt.Color;

import MultiAgentSystem.Simulator;
import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(0, 0, Color.BLACK);

        GameOfLife game = new GameOfLife();
        Simulator simulator = new Simulator(gui, game);
        gui.setSize(game.getWindowSizeX(), game.getWindowSizeY() + 70);
        simulator.draw();


    }
}
