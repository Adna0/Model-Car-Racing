import CarParts.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        //Sven'i Testimine
        Motor motor = new Motor(150, 95, 7);
        Tank tank = new Tank(80, 100);

        System.out.println("Motor Mass: " + motor.getMass() + " kg");
        System.out.println("Tank Mass: " + tank.getMass() + " kg");
    }
}