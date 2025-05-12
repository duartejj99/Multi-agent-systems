package balls;

import java.awt.Point;
import java.util.Optional;
import java.util.Random;

public class Ball {

    private static final Random randomizer = new Random();
    private static final int SPEED_PARAMETER = 10;
    private Point position; // mostly there is a Vector refactorization
    private Point speed; // mostly there is a Vector refactorization
    private Territory visibleSpace;

    public Ball(Territory space) {
        this.visibleSpace = space;
        this.position = new Point(randomizer.nextInt(space.getWidth()), randomizer.nextInt(space.getHeight()));
        this.speed = new Point(SPEED_PARAMETER, SPEED_PARAMETER);
    }

    public Ball(Ball ball) {
        this.position = new Point(ball.position);
        this.speed = new Point(ball.speed);
        this.visibleSpace = new Territory(ball.visibleSpace.getWidth(), ball.visibleSpace.getHeight());
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
        int newX = this.position.x + this.speed.x;
        int newY = this.position.y + this.speed.y;

        Optional<Border> reachedBorder = visibleSpace.getReachedBorder(newX, newY);

        if (reachedBorder.isPresent()) {
            changeDirection(reachedBorder.get());
        } else {
            this.position.x = newX;
            this.position.y = newY;
        }

    }

    private void changeDirection(Border borderReached) {
        int virtualX = this.position.x + this.speed.x;
        int virtualY = this.position.y + this.speed.y;

        switch (borderReached) {
            case BOTTOM:
                this.speed.y = -1 * this.speed.y;
                this.position.y = virtualY - 2 * (virtualY - visibleSpace.getHeight());
                break;
            case BOTTOM_LEFT:
                this.speed.y = -1 * this.speed.y;
                this.position.y = virtualY - 2 * (virtualY - visibleSpace.getHeight());

                this.speed.x *= -1;
                this.position.x = -virtualX;
                break;
            case BOTTOM_RIGHT:
                this.speed.y = -1 * this.speed.y;
                this.position.y = virtualY - 2 * (virtualY - visibleSpace.getHeight());

                this.speed.x *= -1;
                this.position.x = virtualX - 2 * (virtualX - this.visibleSpace.getWidth());
                break;
            case LEFT:
                this.speed.x *= -1;
                this.position.x = -virtualX;
                break;
            case RIGHT:
                this.speed.x *= -1;
                this.position.x = virtualX - 2 * (virtualX - this.visibleSpace.getWidth());
                break;
            case TOP:
                this.speed.y *= -1;
                this.position.y = -virtualY;
                break;
            case TOP_LEFT:
                this.speed.y *= -1;
                this.position.y = -virtualY;

                this.speed.x *= -1;
                this.position.x = -virtualX;
                break;
            case TOP_RIGHT:
                this.speed.y *= -1;
                this.position.y = -virtualY;

                this.speed.x *= -1;
                this.position.x = virtualX - 2 * (virtualX - this.visibleSpace.getWidth());
                break;
        }
    }

    public void translate(int dx, int dy) {
        this.position.translate(dx, dy);
    }

    @Override
    public String toString() {
        return "Ball at  (x:" + this.position.x + " , y: " + this.position.y + " )"; 
    }

    public String myTerritory() {
        return visibleSpace.toString();
    }

}
