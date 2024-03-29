package CarParts;

public class Wheels extends CarPart {
    //Main Variables
    private double diameter;

    //Constructor
    public Wheels(double mass, double diameter) {
        super(mass);
        this.diameter = diameter;
    }

    public double getDiameter() {
        return diameter;
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
