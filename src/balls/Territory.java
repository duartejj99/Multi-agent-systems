package balls;

import java.util.Optional;

public class Territory {
    private final int width;
    private final int height;

    public Territory(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Optional<Border> getReachedBorder(int x, int y) {

        boolean bottomReached = y > this.height;
        boolean topReached = y < 0;
        boolean leftReached = x < 0;
        boolean rightReached = x > this.width;

        if (topReached) {
            if (leftReached) {
                return Optional.of(Border.TOP_LEFT);
            }

            if (rightReached) {
                return Optional.of(Border.TOP_RIGHT);
            }

            return Optional.of(Border.TOP);
        }

        if (bottomReached) {
            if (leftReached) {
                return Optional.of(Border.BOTTOM_LEFT);
            }

            if (rightReached) {
                return Optional.of(Border.BOTTOM_RIGHT);
            }

            return Optional.of(Border.BOTTOM);
        }

        if (leftReached) {
            return Optional.of(Border.LEFT);
        }

        if (rightReached) {
            return Optional.of(Border.RIGHT);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "My territory is: " + getWidth() + "x" + getHeight();
    }
}
