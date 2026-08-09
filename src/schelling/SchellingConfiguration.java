package schelling;



public class SchellingConfiguration {
    public static final int EMPTY_ROOMS_PARAMETER = 1;
    public static final int ROOM_VALUES_COUNT = RoomValue.values().length + EMPTY_ROOMS_PARAMETER;
    /*
    This represents the number of different colors neighbors at a given cell with color C
    needed to decide to change of neighborhood.
     */
    public static final int TOLERANCE_THRESHOLD = 3;

    public enum RoomValue {
        BLACK,
        PINK,
        ORANGE,
        YELLOW,
        RED,
        EMPTY
    };
}
