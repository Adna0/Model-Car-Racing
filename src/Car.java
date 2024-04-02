import CarParts.*;
import java.util.ArrayList;

public class Car {
    //Main Variables
    private double velocity = 1;
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

    private boolean maxRPMReached = false;
    private int exceptionState = 0;

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

        updateMass();
        updateMaxRPM();
    }

    //Getters and Setters
    //Some variables don't have unique setters, as they instead have "Update..." methods that automatically get the info and update it

    public int getExceptionState() {
        return exceptionState;
    }

    public void setExceptionState(int exceptionState) {
        this.exceptionState = exceptionState;
    }

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

    //Sets the given car part
    public void setCarPart(CarPart carPart) {
        if (carPart instanceof Motor) {
            this.Motor = (Motor) carPart;
        } else if (carPart instanceof Wheels) {
            this.Wheels = (Wheels) carPart;
        } else if (carPart instanceof Tank) {
            this.Tank = (Tank) carPart;
        } else if (carPart instanceof Reductor) {
            this.Reductor = (Reductor) carPart;
        } else if (carPart instanceof EnginePipe) {
            this.EnginePipe = (EnginePipe) carPart;
        }
        updateCarPartList();
    }

    //Get the specified carpart
    public <T extends CarPart> T getCarPart(Class<T> carPartClass) {
        for (CarPart part : getCarPartList()) {
            if (carPartClass.isInstance(part)) {
                return carPartClass.cast(part);
            }
        }
        // If no matching object found, return null or throw an exception
        return null;
    }

    //Finds the cars maximum possible RPM based on its part variables
    public void updateMaxRPM() {
        double k1 = 5500;
        double k2 = 10;
        double k3 = 10;
        double maxRPMFormula = (k1*Motor.getPower() / (k2*Wheels.getDiameter() * k3*getTotalMass()));
        Motor.setMaxrpm(maxRPMFormula);
    }

    //Main Methods
    public void updateVelocity() {
        updateMass();

        double RPM = (getVelocity()*4/(Wheels.getDiameter() / Math.PI)) * Reductor.getGearRatios();
        Motor.updateTemperature();
        if (RPM >= Motor.getMaxrpm() || maxRPMReached) {
            maxRPMReached = true;

            Motor.setRpm(Motor.getMaxrpm());
            setAcceleration(0);

            Tank.jarelpaak();
            Tank.veelalles(Tank.maxkutusekulu(Tank.getJarelmaht()));
            if (Tank.getMaht() == 0) {
                setAcceleration(-Constants.GRAVITATIONAL_CONSTANT);
            }
        } else {
            Motor.setRpm(RPM);

            double a = 0.22;
            double c = 1;
            double b = 0.6;

            double accelerationFormula = ((Motor.getPower() / Motor.getRpm()) * Reductor.getGearRatios()) / (Wheels.getDiameter() * getTotalMass());
            double accelerationFunction = b*Math.pow(Math.E, Math.pow(-(a*accelerationFormula-c), 2));
            setAcceleration(accelerationFunction);

            double Consumption = Tank.kutusekulu(accelerationFormula);
            Tank.veelalles(Consumption / Constants.FPS);
        }

        //Compute the velocity
        double newVelo = (getVelocity() + getAcceleration() / Constants.FPS);
        if (newVelo > 0) {
            setVelocity(newVelo);
        } else {
            setVelocity(0);
        }

        //Find new position
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

    public double calculateQuotient() {
        double tc = Motor.getTempChange();
        return tc;
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

        return "«Car Variables»" + "\n" + "Style: ['" + getStyle() + "']; Acceleration: " + currentAccel + " [m/s^2]; Velocity: " + round(getVelocity(), 1000) + " [m/s] or " + round(convertVelocityTo("mps", getVelocity(), "kmh"), 1000) + " [km/h]!" + "\n" + "Current RPM: " + currentRPMColor + currentRPM + Constants.ANSI_RESET + "; Distance: " + currentPos + " [m]!" + "\n" + "\n" + "«Part Variables»" + "\n" + "Total Mass: " + round(getTotalMass(),1000) + " [kg]; " + round(Tank.getMaxmaht(), 100) + "/" + round(Tank.getMaht(),1000) + " (" + round((Tank.getMaht()/Tank.getMaxmaht())*100, 1000) + "%)" + "\n" + "Temperature: " + Motor.getTemperature() + "; " + Motor.getTempChange();
    }

    public double convertVelocityTo(String fromUnit, double currentVelocity, String toUnit) {
        //Convert velocity based on units
        //Choices are "mps", "kmh", "mph"
        double convertedVelocity = 0.0;

        switch (fromUnit.toLowerCase()) {
            case "mps":
                // m/s to mph
                convertedVelocity = switch (toUnit.toLowerCase()) {
                    case "mps" -> currentVelocity;
                    case "kmh" -> currentVelocity * 3.6; // m/s to km/h
                    case "mph" -> currentVelocity * 2.23694;
                    default -> convertedVelocity;
                };
                break;
            case "kmh":
                // km/h to mph
                convertedVelocity = switch (toUnit.toLowerCase()) {
                    case "mps" -> currentVelocity / 3.6; // km/h to m/s
                    case "kmh" -> currentVelocity;
                    case "mph" -> currentVelocity * 0.621371;
                    default -> convertedVelocity;
                };
                break;
            case "mph":
                convertedVelocity = switch (toUnit.toLowerCase()) {
                    case "mps" -> currentVelocity / 2.23694; // mph to m/s
                    case "kmh" -> currentVelocity / 0.621371; // mph to km/h
                    case "mph" -> currentVelocity;
                    default -> convertedVelocity;
                };
                break;
        }

        return convertedVelocity;
    }

    public static double round(double number, double digits) {
        return Math.round(number*digits)/digits;
    }
}
