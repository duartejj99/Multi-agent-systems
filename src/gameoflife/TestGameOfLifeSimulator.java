package gameoflife;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        boolean[][] figure = {
                { false, false, false, false, false },
                { false, false, true, false, false },
                { false, false, true, false, false },
                { false, false, true, false, false },
                { false, false, false, false, false },
        };
        GUISimulator gui = new GUISimulator(100, 300, Color.BLACK);
        GameOfLife game = new GameOfLife(figure);
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui, game);
        // gui.pack();
        simulator.draw();


    }
}
