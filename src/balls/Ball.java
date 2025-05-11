package balls;

import java.awt.Point;
import java.util.Random;

public class Ball {
    private Point position;
    private static final Random randomizer = new Random();
    private Territory visibleSpace;

    public Ball(Territory space) {
        this.position = new Point(randomizer.nextInt(space.getWidth()), randomizer.nextInt(space.getHeight()));
        this.visibleSpace = space;
    }

    public Ball(Ball ball) {
        this.position = new Point(ball.position);
    }

    public int getX() {
        return this.position.x;
    }

	public int getY() {
        return this.position.y;
	}

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        this.position = new Point(x, y);
    }

    public void move() {
        throw new UnsupportedOperationException("Unimplemented method 'Move'");
    }

    public void translate(int dx, int dy) {
        this.position.translate(dx, dy);
    }

    @Override
    public String toString() {
        return "Ball at  (x:" + this.position.x + " , y: " + this.position.y + " )"; 
    }


}
