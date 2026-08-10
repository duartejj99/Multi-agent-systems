package schelling;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import schelling.SchellingConfiguration.RoomValue;

public class TestRoomState {

    @Test
    public void testRoomState(){
        RoomState roomState = new RoomState();
        float emptyRoomPorcentage = 0.0F;

        for(int i = 0; i < 100; i++){
            RoomValue room = RoomState.randomRoomValue();
            if (room.equals(RoomValue.EMPTY))
                emptyRoomPorcentage += 1;
        }

        System.out.println("emptyRoomPorcentage = " + emptyRoomPorcentage);
    }

    @Test
    public void testRandomGenerator(){
        Random random = new Random();
        int cellState;
        for(int i = 0; i < 100; i++){
            cellState = random.nextInt(SchellingConfiguration.ROOM_VALUES_COUNT + 5);
            Assert.assertTrue(cellState >= 0 && cellState < SchellingConfiguration.ROOM_VALUES_COUNT + 5);
            System.out.println("Random value number #"+ i + ": " +cellState);

        }


    }
}
