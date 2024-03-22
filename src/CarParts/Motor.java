package CarParts;

public class Motor extends CarPart {
    //Main Variables
    private double power;
    private double temperature;
    private double fuelConsumption;
    private double rpm;
    private double maxrpm;

    //Constructor
    public Motor(double mass, double power, double fuelConsumption, double maxrpm) {
        super(mass);
        this.power = power;
        this.maxrpm = maxrpm;
        this.fuelConsumption = fuelConsumption;
    }

    public double getPower() {
        return power;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getMaxrpm() {
        return maxrpm;
    }

    @Override
    public double getMass() {
        return mass;
    }
}
