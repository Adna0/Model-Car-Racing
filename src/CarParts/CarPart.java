package CarParts;

//Every Car Part extends the CarPart class, which is an abstract class. Every Car Part needs to have a @Override getMass() method (thus the mass doesn't need to be defined in the constructor).
public abstract class CarPart {
    //Main Variables
    protected double mass;

    public CarPart(double mass) {
        this.mass = mass;
    }

    //Main Methods
    public abstract double getMass();
}
