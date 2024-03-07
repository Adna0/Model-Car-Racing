package CarParts;

public class EnginePipe extends CarPart {
    //Constructor
    public EnginePipe(double mass) {
        super(mass);
    }

    //Main Methods
    @Override
    public double getMass() {
        return mass;
    }
}
