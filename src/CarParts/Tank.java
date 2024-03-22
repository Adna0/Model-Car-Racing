package CarParts;

public class Tank extends CarPart {
    //Main Variables
    private double capacity;
    private double maxCapacity;

    //Constructor
    public Tank(double mass, double maxCapacity) {
        super(mass);
        this.maxCapacity = maxCapacity;
    }

    //Main Methods
    @Override
    public double getMass() {
        //Assuming that our fuel consists of 80% methanol, 20% oil.
        //Oil density is 0.96, methanol density is 0.79 [kg/L]

        double metVol = getCapacity()*0.8;
        double oilVol = getCapacity()*0.2;

        double metMass = metVol * 0.79;
        double oilMass = oilVol * 0.96;
        return (metMass + oilMass) + mass;
    }

    public double getCapacity() {
        return capacity;
    }

    public void updateCapacity() {

    }
}
