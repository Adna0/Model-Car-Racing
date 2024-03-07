package CarParts;

public class Reductor extends CarPart {
    //Constructor
    public Reductor(double mass) {
        super(mass);
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
