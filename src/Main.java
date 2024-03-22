import CarParts.*;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws InterruptedException {






        //Sven Testing Things
        //UI testimine
        UserInterface ui = new UserInterface();
        ui.greetPlayer();

        boolean playGame = ui.askYesNoQuestion("Understood?");
        Motor motor;
        double MotorMass = 1;
        double MotorPower = 100;
        double MotorFuelConsumption = 7;
        double MotorMaxRPM = 40000;
        Tank tank;
        double TankMass = 0.1;
        double TankMaxCapacity = 10;
        Wheels wheels;
        double WheelsMass = 0.3;
        double WheelsDiameter = 0.05;
        EnginePipe pipe;
        double EnginePipeMass = 0.2;
        Reductor reductor;
        double ReductorMass = 1;
        double ReductorGearRatios = 2.6;

        if (playGame) {
            if(ui.askYesNoQuestion("Vanilla juppid (y) või loome uued (n)?")){
                motor = new Motor(MotorMass, MotorPower, MotorFuelConsumption, MotorMaxRPM);
                tank = new Tank(TankMass, TankMaxCapacity);
                wheels = new Wheels(WheelsMass, WheelsDiameter);
                pipe = new EnginePipe(EnginePipeMass);
                reductor = new Reductor(ReductorMass, ReductorGearRatios);
            } else {
                System.out.println("Loome uued juppid!");
                System.out.println(Constants.ANSI_RED + "Mootor:" + Constants.ANSI_RESET);
                System.out.print("Mass (default: "+MotorMass+"): ");
                MotorMass = ui.scanner.nextDouble();
                System.out.print("Power (default: "+MotorPower+"): ");
                MotorPower = ui.scanner.nextDouble();
                System.out.print("Fuel Consumption (default +"+MotorFuelConsumption+"): ");
                MotorFuelConsumption = ui.scanner.nextDouble();
                System.out.print("Max RPM (default "+MotorMaxRPM+"): ");
                MotorMaxRPM = ui.scanner.nextDouble();
                motor = new Motor(MotorMass, MotorPower, MotorFuelConsumption, MotorMaxRPM);
                System.out.println(Constants.ANSI_RED + "Tank:" + Constants.ANSI_RESET);
                System.out.print("Mass (default: "+TankMass+"): ");
                TankMass=ui.scanner.nextDouble();
                System.out.print("Max capacity (default: "+TankMaxCapacity+"): ");
                TankMaxCapacity = ui.scanner.nextDouble();
                tank = new Tank(TankMass, TankMaxCapacity);
                System.out.println(Constants.ANSI_RED+"Wheels:"+Constants.ANSI_RESET);
                System.out.print("Mass (default: "+WheelsMass+"): ");
                WheelsMass = ui.scanner.nextDouble();
                System.out.print("Diameter (default: "+WheelsDiameter+"): ");
                WheelsDiameter=ui.scanner.nextDouble();
                wheels = new Wheels(WheelsMass, WheelsDiameter);
                System.out.println(Constants.ANSI_RED+"Engine pipe:"+Constants.ANSI_RESET);
                System.out.print("Mass (default: "+EnginePipeMass+"): ");
                EnginePipeMass=ui.scanner.nextDouble();
                pipe = new EnginePipe(EnginePipeMass);
                System.out.println(Constants.ANSI_RED+"Reductor:"+Constants.ANSI_RESET);
                System.out.print("Mass (default: "+ReductorMass+"): ");
                ReductorMass=ui.scanner.nextDouble();
                System.out.print("Gear Ratio (default: "+ReductorGearRatios+"): ");
                ReductorGearRatios=ui.scanner.nextDouble();
                reductor = new Reductor(ReductorMass, ReductorGearRatios);
            }
            //Car
            //Mida võimsam on mootor, seda paremini veab ning tippkiirus suureneb
            //Maxrpm näitab, kui kaua auto saab kiirendada, mis sisuliselt ka suurendab tippkiirust

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