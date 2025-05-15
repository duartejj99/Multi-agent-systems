package balls;

import java.awt.Color;
import java.awt.Point;

import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

public class BallsSimulator implements Simulable {

    private Balls balls;
    private GUISimulator gui;

    public BallsSimulator() {
        Point windowSize = new Point(500, 500);
        this.balls = new Balls(windowSize);
        this.gui = new GUISimulator(windowSize.x, windowSize.y, Color.BLACK, this);
        this.gui.setSimulable(this);
    }

    public BallsSimulator(GUISimulator gui) {
        Point windowSize = new Point(gui.getPanelWidth(), gui.getPanelHeight());
        this.balls = new Balls(windowSize);
        this.gui = gui;
    }

    @Override
    public void next() {
        this.move();
        this.draw();
    }

    @Override
    public void restart() {
        System.out.println("Restart !");
        this.balls.reInit();
        this.draw();
    }

    private void move() {
        this.balls.move();
    }

    public void draw() {
        this.gui.reset();
        this.gui.addGraphicalElement(
                new Rectangle(gui.getPanelWidth() / 2, gui.getPanelHeight() / 2, Color.WHITE, Color.BLACK,
                        gui.getPanelWidth()));

        for (Ball ball : this.balls.getBalls()) {
            this.gui.addGraphicalElement(new Oval(ball.getX(), ball.getY(), Color.RED, Color.RED, 10));
        }
    }

}