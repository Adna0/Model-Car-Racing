import CarParts.*;
import java.util.ArrayList;

public class Car {
    //Main Variables
    private double velocity = 1;
    private double maxVelocity;
    private double acceleration;
    private double totalMass;

    private char style;
    private double carPos = 0;

    //Secondary Variables
    private double centripetalForce;
    private double centrifugalForce;
    private double frictionForce;
    private double weight; //"F-normal = F-gravity = weight = mg"

    private double totalActingForce;


    //Car Parts
    private Motor Motor;
    private Tank Tank;
    private Wheels Wheels;
    private EnginePipe EnginePipe;
    private Reductor Reductor;
    private ArrayList<CarPart> carPartList; //Useful, if we want to iterate over every part without looking for unique elements

    //Constructors
    //You are able to give in a compiled list of all car parts or every individual car part separately. Acceleration is mandatory
    public Car(double acceleration, char style, Motor motor, Tank tank, Wheels wheels, EnginePipe enginePipe, Reductor reductor) {
        this.acceleration = acceleration;
        this.style = style;
        Motor = motor;
        Tank = tank;
        Wheels = wheels;
        EnginePipe = enginePipe;
        Reductor = reductor;
        updateCarPartList(); //Makes sure the car part list is up-to-date

        double maxSpeed = (Motor.getMaxrpm() * Wheels.getDiameter()) / (6 * Reductor.getGearRatios() * Math.PI);
        maxVelocity = maxSpeed;
    }

    //Getters and Setters
    //Some variables don't have unique setters, as they instead have "Update..." methods that automatically get the info and update it
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public ArrayList<CarPart> getCarPartList() {
        return carPartList;
    }

    public char getStyle() {
        return style;
    }

    public double getCarPos() {
        return carPos;
    }

    public void setCarPos(double carPos) {
        this.carPos = carPos;
    }

    public double getTotalMass() {
        return totalMass;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    //Main Methods
    public void updateVelocity() {
        Tank.updateCapacity();
        updateMass();

        double RPM = (getVelocity()*6/(Wheels.getDiameter() / Math.PI)) * Reductor.getGearRatios();
        if (RPM >= Motor.getMaxrpm()) {
            Motor.setRpm(Motor.getMaxrpm());
            setAcceleration(0);
        } else {
            Motor.setRpm(RPM);

            //0.22; 1; 0.6
            double a = 0.22;
            double c = 1;
            double b = 0.6;

            double accelerationFormula = ((Motor.getPower() / Motor.getRpm()) * Reductor.getGearRatios()) / (Wheels.getDiameter() * getTotalMass());
            setAcceleration(b*Math.pow(Math.E, Math.pow(-(a*accelerationFormula-c), 2)));
        }

        setVelocity((getVelocity() + getAcceleration() / Constants.FPS));
        setCarPos(getCarPos() + getVelocity() / Constants.FPS);
    }

    public void updateMass() {
        //This method updates the total mass of the car based on the masses of all of its car parts.
        double totalMass = 0;
        for (CarPart cp : getCarPartList()) {
            totalMass += cp.getMass();
        }

        this.totalMass = totalMass;
    }

    public void updateCarPartList() {
        //Updates the car part list with the current car parts
        ArrayList<CarPart> carPartL = new ArrayList<>();
        carPartL.add(this.Motor);
        carPartL.add(this.Tank);
        carPartL.add(this.Wheels);
        carPartL.add(this.EnginePipe);
        carPartL.add(this.Reductor);
        this.carPartList = carPartL;
    }

    //Other Methods
    @Override
    public String toString() {
        //Display Styles
        double currentAccel = round(getAcceleration(), 1000);
        double currentPos = round(getCarPos(), 1000);

        double currentRPM = round(Motor.getRpm(), 1000);
        double rpmPercentage = currentRPM / Motor.getMaxrpm();
        String currentRPMColor;
        if (rpmPercentage < 0.5) {
            currentRPMColor = Constants.ANSI_GREEN;
        } else if (rpmPercentage < 0.85) {
            currentRPMColor = Constants.ANSI_YELLOW;
        } else {
            currentRPMColor = Constants.ANSI_RED;
        }

        return "«Car Variables»" + "\n" + "Style: ['" + getStyle() + "']; Acceleration: " + currentAccel + " [m/s^2]; Velocity: " + round(getVelocity(), 1000) + " [m/s] or " + round(convertVelocityTo("mps", getVelocity(), "kmh"), 1000) + " [km/h]!" + "\n" + "Current RPM: " + currentRPMColor + currentRPM + Constants.ANSI_RESET + "; Distance: " + currentPos + " [m]!" + "\n" + "\n" + "«Part Variables»" + "\n" + "Total Mass: " + getTotalMass() + " [kg]";
    }

    public double convertVelocityTo(String fromUnit, double currentVelocity, String toUnit) {
        //Convert velocity based on units
        //Choices are "mps", "kmh", "mph"
        double convertedVelocity = 0.0;

        switch (fromUnit.toLowerCase()) {
            case "mps":
                switch (toUnit.toLowerCase()) {
                    case "mps":
                        convertedVelocity = currentVelocity;
                        break;
                    case "kmh":
                        convertedVelocity = currentVelocity * 3.6; // m/s to km/h
                        break;
                    case "mph":
                        convertedVelocity = currentVelocity * 2.23694; // m/s to mph
                        break;
                }
                break;
            case "kmh":
                switch (toUnit.toLowerCase()) {
                    case "mps":
                        convertedVelocity = currentVelocity / 3.6; // km/h to m/s
                        break;
                    case "kmh":
                        convertedVelocity = currentVelocity;
                        break;
                    case "mph":
                        convertedVelocity = currentVelocity * 0.621371; // km/h to mph
                        break;
                }
                break;
            case "mph":
                switch (toUnit.toLowerCase()) {
                    case "mps":
                        convertedVelocity = currentVelocity / 2.23694; // mph to m/s
                        break;
                    case "kmh":
                        convertedVelocity = currentVelocity / 0.621371; // mph to km/h
                        break;
                    case "mph":
                        convertedVelocity = currentVelocity;
                        break;
                }
                break;
        }

        return convertedVelocity;
    }

    public double round(double number, double digits) {
        return Math.round(number*digits)/digits;
    }
}
