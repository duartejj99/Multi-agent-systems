package gameoflife;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(100, 300, Color.BLACK);
        GameOfLife game = new GameOfLife(Patterns.GLIDER);
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui, game);
        // gui.pack();
        simulator.draw();


    }
}
