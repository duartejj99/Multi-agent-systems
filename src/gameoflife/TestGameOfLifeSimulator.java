package gameoflife;

import java.awt.Color;

import gui.GUISimulator;

public class TestGameOfLifeSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(50, 50, Color.WHITE);
        GameOfLifeSimulator simulator = new GameOfLifeSimulator(gui); 

        simulator.draw();


    }
}
