package balls;

import java.awt.Color;
import gui.GUISimulator;

public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        gui.setSimulable(new BallsSimulator());
    }
}
