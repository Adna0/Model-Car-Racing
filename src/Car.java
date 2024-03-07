import CarParts.*;
import java.util.ArrayList;

public class Car {
    //Main Variables
    private double velocity;
    private double acceleration;
    private double totalMass;

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
    public Car(double acceleration, ArrayList<CarPart> carPartList) {
        this.acceleration = acceleration;
        this.carPartList = carPartList;

        //Add every car part to its respective variable
        //...
    }

    public Car(double acceleration, Motor motor, Tank tank, Wheels wheels, EnginePipe enginePipe, Reductor reductor) {
        this.acceleration = acceleration;
        Motor = motor;
        Tank = tank;
        Wheels = wheels;
        EnginePipe = enginePipe;
        Reductor = reductor;
        updateCarPartList(); //Makes sure the car part list is up-to-date
    }

    //Getters and Setters
    //Some variables don't have unique setters, as they instead have "Update..." methods that automatically get the info and update it
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public ArrayList<CarPart> getCarPartList() {
        return carPartList;
    }

    //Main Methods
    public void updateVelocity() {
        //Upon calling, the method updates the current speed with the current set acceleration
        setVelocity(getVelocity() + getAcceleration());
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
}
