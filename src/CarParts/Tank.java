package CarParts;

public class Tank extends CarPart {
    //Main Variables
    private double capacity;

    //Constructor
    public Tank(double mass, double capacity) {
        super(mass);
        this.capacity = capacity;
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
