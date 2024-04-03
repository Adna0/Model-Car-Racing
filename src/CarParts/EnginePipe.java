package CarParts;

public class EnginePipe extends CarPart {
    private double pikkus; //0-40 vahel, 20 on perfektne

    //Constructor
    public EnginePipe(double mass, double pikkus) {
        super(mass);
        this.pikkus = pikkus;
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getPikkus() {
        return pikkus;
    }

    public void setPikkus(double pikkus) {
        this.pikkus = pikkus;
    }

    public double kiirendusekoeffitsent(){
        return ((-0.75*pikkus+30.0*pikkus-20.0)/280.0) + Math.random()*0.2;
    }
}
