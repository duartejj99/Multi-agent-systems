package balls;

import java.awt.Color;

import gui.GUISimulator;

public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(700, 700, Color.BLACK);
        BallsSimulator simulator = new BallsSimulator(gui);
        gui.setSimulable(simulator);
        gui.setBounds(500, 500, 1000, 1000);
        System.out.println(gui.getBounds().toString());
    }
}
