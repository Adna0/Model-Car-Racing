import CarParts.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {






        //Sven Testing Things
        //UI testimine
        UserInterface ui = new UserInterface();
        ui.greetPlayer();
        boolean playGame = ui.askYesNoQuestion("Understood?");

        if (playGame) {
            //Car
            Motor motor = new Motor(150, 95, 7);
            Tank tank = new Tank(12, 100);
            Wheels wheels = new Wheels(4, 50);
            EnginePipe pipe = new EnginePipe(10);
            Reductor reductor = new Reductor(10, new double[2], 1, 4, 1000, 0.5);

            Car car = new Car(2.5, 'C', motor, tank, wheels, pipe, reductor);

            //Track
            char[][] trackLayout = TrackGenerator.generateRectangularTrack(10);
            Track track = new Track(trackLayout);

            track.displayTrackPeriodically(track, car, 10);
        } else {
            UserInterface.displayMessage("Bye!");
        }

        ui.closeScanner();
    }
}