package balls;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Balls {

    private static final int ballsNumber = 5;

    private List<Ball> balls;
    private List<Ball> initialPositions;


    public Balls(Point windowMargins) {
        this.balls = new ArrayList<Ball>(ballsNumber);
        this.initialPositions = new ArrayList<Ball>(ballsNumber);
        for (int i = 0; i < ballsNumber; i++) {

            Ball ball = new Ball(new Territory((int) windowMargins.getX(), (int) windowMargins.getY()));
            Ball initialball = new Ball(ball);

            this.balls.add(ball);
            this.initialPositions.add(initialball);
        }

    }

    public List<Ball> getBalls() {
        return balls;
    }


    public void move() {
        for (Ball ball : this.balls) {
            ball.move();
        }
    }

    public void translate(int dx, int dy) {
        for (Ball ball : this.balls) {
            ball.translate(dx, dy);
        } 
    }

    public void setPosition(int x, int y) {
        for (Ball ball : this.balls) {
            ball.setPosition(x, y);
        } 
    }

    public void reInit() {
        this.balls.clear();
        // Deep Copy, How to do better?
        for (Ball p : this.initialPositions) {
            this.balls.add(new Ball(p));
        }
    }

    @Override
    public String toString() {
        String balls = "Balls!: \n";
        for (Ball ball : this.balls) {
            balls += ball.toString() + "\n";
        }

        return balls;
    }

}
