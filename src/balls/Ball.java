package balls;

import java.awt.Point;
import java.util.Optional;
import java.util.Random;

public class Ball {

    private static final Random randomizer = new Random();
    private static final int SPEED_PARAMETER = 20;
    private Point position; // mostly there is a Vector refactorization
    private Point speed; // mostly there is a Vector refactorization
    private Territory visibleSpace;

    public Ball(Territory space) {
        this.visibleSpace = space;
        this.position = new Point(randomizer.nextInt(space.getWidth()), randomizer.nextInt(space.getHeight()));
        this.speed = new Point(randomizer.nextInt(SPEED_PARAMETER), randomizer.nextInt(SPEED_PARAMETER));
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

        Optional<HorizontalBorder> horizontalBorder = visibleSpace.getHorizontalBorder(newX);
        Optional<VerticalBorder> verticalBorder = visibleSpace.getVerticalBorder(newY);

        if (verticalBorder.isPresent()) {
            newY = bounceOnY(verticalBorder.get());
        }

        if (horizontalBorder.isPresent()) {
            newX = bounceOnX(horizontalBorder.get());
        }

        this.position.x = newX;
        this.position.y = newY;

    }

    private int bounceOnX(HorizontalBorder horizontalBorder) {
        int virtualX = this.position.x + this.speed.x;

        switch (horizontalBorder) {
            case LEFT:
                this.speed.x *= -1;
                this.position.x = -virtualX;
                break;
            case RIGHT:
                this.speed.x *= -1;
                this.position.x = virtualX - 2 * (virtualX - this.visibleSpace.getWidth());
                break;
            default:
                System.out.println("WTF");
                break;
        }
        return this.position.x;
    }

    private int bounceOnY(VerticalBorder verticalBorder) {
        int virtualY = this.position.y + this.speed.y;

        switch (verticalBorder) {
            case TOP:
                this.speed.y *= -1;
                this.position.y = -virtualY;
                break;
            case BOTTOM:
                this.speed.y = -1 * this.speed.y;
                this.position.y = virtualY - 2 * (virtualY - visibleSpace.getHeight());
                break;
            default:
                System.out.println("WTF");
                break;
        }

        return this.position.y;
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
