package CarParts;

public class Reductor extends CarPart {
    // Variables
    private double[] gearRatios;
    private int currentGear;
    private int maxGear;
    private double reductorEfficiency;

    //Constructor
    public Reductor(double mass, double[] gearRatios, int currentGear, int maxGear, int revLimit, double reductorEfficiency) {
        super(mass);
        this.gearRatios = gearRatios;
        this.currentGear = currentGear;
        this.maxGear = maxGear;
        this.reductorEfficiency = reductorEfficiency;
    }
    // Getter/Setters


    public double[] getGearRatios() {
        return gearRatios;
    }

    public void setGearRatios(double[] gearRatios) {
        this.gearRatios = gearRatios;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public int getMaxGear() {
        return maxGear;
    }

    public void setMaxGear(int maxGear) {
        this.maxGear = maxGear;
    }

    public double getReductorEfficiency() {
        return reductorEfficiency;
    }

    public void setReductorEfficiency(double reductorEfficiency) {
        this.reductorEfficiency = reductorEfficiency;
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
