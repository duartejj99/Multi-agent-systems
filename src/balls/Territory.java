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


    public Optional<HorizontalBorder> getHorizontalBorder(int x) {

        boolean leftReached = x < 0;
        boolean rightReached = x > this.width;

        if (!leftReached && !rightReached) {
            return Optional.empty();
        }

        if (leftReached) {
            return Optional.of(HorizontalBorder.LEFT);
        }

        if (rightReached) {
            return Optional.of(HorizontalBorder.RIGHT);
        }

        return Optional.empty();
    }

    public Optional<VerticalBorder> getVerticalBorder(int y) {
        boolean bottomReached = y > this.height;
        boolean topReached = y < 0;

        if (!bottomReached && !topReached) {
            return Optional.empty();
        }

        if (bottomReached) {
            return Optional.of(VerticalBorder.BOTTOM);
        }

        if (topReached) {
            return Optional.of(VerticalBorder.TOP);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "My territory is: " + getWidth() + "x" + getHeight();
    }
}
