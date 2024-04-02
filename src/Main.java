import CarParts.*;

class Main {
    public static void simulate() throws CarExplodedException, OutOfFuelException, InterruptedException, OutOfIterationsException {
        //UI testimine
        UserInterface ui = new UserInterface();
        ui.greetPlayer();

        boolean playGame = ui.askYesNoQuestion("Understood?");

        if (playGame) {
            UserInterface.clearTerminal();

            System.out.println("You're currently using the " + Constants.ANSI_GREEN + "default" + Constants.ANSI_RESET + " setup:");

            Motor motor = new Motor(1, 100);
            Tank tank = new Tank(0.1, 0.01, 0.4);
            Wheels wheels = new Wheels(0.3, 0.05);
            EnginePipe pipe = new EnginePipe(0.2, 20);
            Reductor reductor = new Reductor(1, 2.6);

            Car car = new Car(2.5, '█', motor, tank, wheels, pipe, reductor);
            ui.showSetup(car);

            boolean changeSetup = ui.askCustomYesNo("\nWould you like to use the " + Constants.ANSI_GREEN + "default" + Constants.ANSI_RESET + " (d) setup or " + Constants.ANSI_ORANGE + "change (c)" + Constants.ANSI_RESET + " your current setup?", new String[]{"d", "c"});
            if (!changeSetup) {
                car = ui.changePart(car);
                car.updateMaxRPM();
                car.updateMass();
            }

            //Track
            Track track = new Track();

            ui.displayCountdown();
            track.displayTrackPeriodically(track, car, 100 * Constants.FPS);

            // Check for conditions like car explosion or fuel depletion
            ui.closeScanner();
            if (car.getExceptionState() == 1) {
                throw new CarExplodedException("Car exploded!");
            }
            if (car.getExceptionState() == 2) {
                throw new OutOfFuelException("Out of fuel!");
            } else {
                throw new OutOfIterationsException("Simulation ran out of iterations!");
            }
        } else {
            UserInterface.displayMessage("Bye!");
        }
    }

    public static void simulateUsingArgs(String[] args) throws CarExplodedException, OutOfFuelException, OutOfIterationsException {
        Motor motor = new Motor(1, Double.parseDouble(args[0]));
        Tank tank = new Tank(0.1, 0.01, 0.4);
        Wheels wheels = new Wheels(0.3, Double.parseDouble(args[1]));
        EnginePipe pipe = new EnginePipe(0.2, 20);
        Reductor reductor = new Reductor(1, 2.6);

        Car car = new Car(2.5, '█', motor, tank, wheels, pipe, reductor);

        //Track
        Track track = new Track();

        track.simulateInstantly(car, 100 * Constants.FPS);

        // Check for conditions like car explosion or fuel depletion
        if (car.getExceptionState() == 1) {
            throw new CarExplodedException("Car exploded! Distance: " + car.getCarPos());
        }
        if (car.getExceptionState() == 2) {
            throw new OutOfFuelException("Out of fuel! Distance: " + car.getCarPos());
        } else {
            throw new OutOfIterationsException("Simulation ran out of iterations! Distance: " + car.getCarPos());
        }
    }
}