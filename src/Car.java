import CarParts.*;
import java.util.ArrayList;

public class Car {
    //Main Variables
    private double velocity = 0;
    private double acceleration;
    private double totalMass;

    private char style;
    private double carPosX;
    private double carPosY;

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
    public Car(double acceleration, char style, ArrayList<CarPart> carPartList) {
        this.acceleration = acceleration;
        this.carPartList = carPartList;
        this.style = style;

        setCarPosX(1);
        setCarPosY(1);

        //Add every car part to its respective variable
        //...
    }

    public Car(double acceleration, char style, Motor motor, Tank tank, Wheels wheels, EnginePipe enginePipe, Reductor reductor) {
        this.acceleration = acceleration;
        this.style = style;
        Motor = motor;
        Tank = tank;
        Wheels = wheels;
        EnginePipe = enginePipe;
        Reductor = reductor;
        updateCarPartList(); //Makes sure the car part list is up-to-date

        setCarPosX(1);
        setCarPosY(1);
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

    public double getCarPosX() {
        return carPosX;
    }

    public double getCarPosY() {
        return carPosY;
    }

    public void setCarPosX(double carPosX) {
        this.carPosX = carPosX;
    }

    public void setCarPosY(double carPosY) {
        this.carPosY = carPosY;
    }

    //Main Methods
    public void updateVelocity() {
        //Upon calling, the method updates the current speed with the current set acceleration and also the new acceleration
        setVelocity(getVelocity() + getAcceleration());
        setAcceleration(getAcceleration()*0.9);
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
        return "Style: ['" + getStyle() + "']; Acceleration: " + getAcceleration() + " [m/s^2]; Velocity: " + getVelocity() + " [m/s] or " + convertVelocityTo("mps", getVelocity(), "kmh") + " [km/h]!";
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
}
