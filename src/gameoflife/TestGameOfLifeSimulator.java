package gameoflife;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(100, 300, Color.BLACK);
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui);
        // gui.pack();
        simulator.draw();


    }
}
