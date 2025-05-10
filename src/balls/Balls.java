package balls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class Balls {

    private static final int ballsNumber = 5;
    private final int randomPosRange;
    private List<Point> balls;

    public List<Point> getBalls() {
        return balls;
    }

    private List<Point> initialPositions;

    public Balls(Point windowMargins) {
        this.randomPosRange = windowMargins.x;

        this.balls = new ArrayList<Point>(ballsNumber);
        this.initialPositions = new ArrayList<Point>(ballsNumber);

        Random r = new Random();
        for (int i = 0; i < ballsNumber; i++) {
            int x = r.nextInt(randomPosRange);
            int y = r.nextInt(randomPosRange);
            Point point = new Point(x, y);
            Point initialPoint = new Point(point);

            this.balls.add(point);
            this.initialPositions.add(initialPoint);
        }

    }


    public void translate(int dx, int dy) {
        for  (Point ball : this.balls) {
            ball.translate(dx, dy);
        } 
    }

    public void setPosition(int x, int y) {
        for  (Point ball : this.balls) {
            ball.setLocation(x, y);
        } 
    }

    public void reInit() {
        this.balls.clear();
        // Deep Copy, How to do better?
        for (Point p : this.initialPositions) {
            this.balls.add(new Point(p));
        }
    }

    @Override
    public String toString() {
        String balls = "Balls!: \n";
        for (Point ball : this.balls) {
            balls += "( x = " + ball.y + ", y = " + ball.x + ")\n";
        }

        return balls;

    }

}
