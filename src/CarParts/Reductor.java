package CarParts;

public class Reductor extends CarPart {
    // Variables
    private double gearRatio;

    //Constructor
    public Reductor(double mass, double gearRatios) {
        super(mass);
        this.gearRatio = gearRatios;
    }
    // Getter/Setters


    public double getGearRatios() {
        return gearRatio;
    }

    public void setGearRatios(double gearRatios) {
        this.gearRatio = gearRatios;
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
