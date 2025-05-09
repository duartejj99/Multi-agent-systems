package balls;

import gui.Simulable;
public class BallsSimulator implements Simulable {

    private Balls balls;

    public BallsSimulator() {
        this.balls = new Balls();
    }

    @Override
    public void next() {
        this.balls.translate(1, 1);
        System.out.println(this.balls);
    }

    @Override
    public void restart() {
        this.balls.reInit();
    }

}