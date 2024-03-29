public class EnginePipe {
    private double mass; //kg
    private double pikkus; //võiks olla 0-40 vahel, kus 20 on perfektne

    public Toru(double pikkus) {
        this.pikkus = pikkus;
        mass=pikkus/100;
    }

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
        return (((-3/4)*pikkus+30*pikkus-20)/280);
    }
}
