package CarParts;

public class Motor extends CarPart {
    //Main Variables
    private double power;
    private double temperature;
    private double fuelConsumption;

    //Constructor
    public Motor(double mass, double power, double fuelConsumption) {
        super(mass);
        this.power = power;
        this.fuelConsumption = fuelConsumption;
    }

    //Secondary Variables
    private double revolutionsPerMin;

    @Override
    public double getMass() {
        return mass;
    }
}
