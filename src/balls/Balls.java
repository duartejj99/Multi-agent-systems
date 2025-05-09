package balls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class Balls {

    private List<Point> balls;
    private List<Point> initialPositions;

    public Balls() {
        this.balls = new ArrayList<Point>(10);
        this.initialPositions = new ArrayList<Point>(10);
        Random r = new Random();
        for (int i=0; i<10; i++) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
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
        this.balls = this.initialPositions;
    }

    @Override
    public String toString() {
        String balls = "Positions  are: \n";
        for (Point ball : this.balls) {
            balls += "( x = " + ball.y + ", y = " + ball.x + ")\n";
        }

        return balls;

    }

}
