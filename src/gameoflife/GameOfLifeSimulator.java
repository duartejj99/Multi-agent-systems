package gameoflife;

import gui.GUISimulator;
import gui.Simulable;
public class GameOfLifeSimulator implements Simulable {
    private GUISimulator gui;
    private GameOfLife gameOfLife;

    public GameOfLifeSimulator() {
        this.gui = new GUISimulator(0, 0, null, null);
        this.gameOfLife = new GameOfLife(500);
        gui.setSimulable(this);
    }

    @Override
    public void next() {
        throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restart'");
    }


}