package CarParts;

public class Motor extends CarPart {
    //Main Variables
    private final double power;
    private double temperature;
    private double rpm;
    private double maxrpm;
    private double tempChange = 0.15;

    //Constructor
    public Motor(double mass, double power) {
        super(mass);
        this.power = power;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void updateTemperature() {
        double L = 90; //Maximum temp
        double k = 0.0001; //Steepness of the curve
        double TemperatureFromRPM = L * (1-Math.pow(Math.E, -k*getRpm()));
        double TemperatureChange = k*Math.pow(Math.E, -k*getRpm())*L*1000;
        this.tempChange = TemperatureChange;
        setTemperature(TemperatureFromRPM);
    }

    public double getTempChange() {
        return tempChange;
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

    public void setMaxrpm(double maxrpm) {
        this.maxrpm = maxrpm;
    }

    public double getMaxrpm() {
        return maxrpm;
    }

    @Override
    public double getMass() {
        return mass;
    }
}
