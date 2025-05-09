package balls;

public class TestsBalls {
    public static void main(String[] args) {

        Balls myBalls = new Balls();

        System.out.println("TEST BALLS: \n");
        System.out.println("###########: \n\n");

        System.out.println("Initial state:");
        System.out.println(myBalls.toString());
        
        System.out.println("One step:");
        myBalls.translate(1, 1);
        System.out.println(myBalls.toString()+"\n");

        System.out.println("Two step:");
        myBalls.translate(10, 10);
        System.out.println(myBalls.toString()+"\n");
        
        
        System.out.println("Three step:");
        myBalls.translate(-10, -10);
        System.out.println(myBalls.toString()+"\n");
        
        System.out.println("Back to the initial step:");
        myBalls.translate(-1, -1);
        System.out.println(myBalls.toString()+"\n");
        
        System.out.println("Five step:");
        myBalls.translate(-10, -10);
        System.out.println(myBalls.toString()+"\n");


        System.out.println("Reinitialize \n");
        myBalls.reInit();
        System.out.println(myBalls.toString());

    }
}
