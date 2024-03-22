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
            //Mida võimsam on mootor, seda paremini veab ning tippkiirus suureneb
            //Maxrpm näitab, kui kaua auto saab kiirendada, mis sisuliselt ka suurendab tippkiirust
            Motor motor = new Motor(1, 100, 7, 40000);
            Tank tank = new Tank(0.1, 10);
            Wheels wheels = new Wheels(0.3, 0.05);
            EnginePipe pipe = new EnginePipe(0.2);
            Reductor reductor = new Reductor(1, 2.6);

            Car car = new Car(2.5, '█', motor, tank, wheels, pipe, reductor);

            //Track
            Track track = new Track();

            ui.displayCountdown();
            track.displayTrackPeriodically(track, car, 100 * Constants.FPS);
        } else {
            UserInterface.displayMessage("Bye!");
        }

        ui.closeScanner();
    }
}