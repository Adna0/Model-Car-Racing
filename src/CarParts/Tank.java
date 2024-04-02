package CarParts;

public class Tank extends CarPart {
    private double mass; //paagi enda mass kilogrammides
    private double survetoru;// ristlõike pindala ruutmeetrites
    private double maht; //liitrites
    private double maxmaht;
    private boolean maxi = true;
    private double jarelmaht;

    public Tank(double mass, double survetoru, double maht) {
        super(mass);
        this.mass += maht*0.74;

        this.survetoru = survetoru;
        this.maht = maht;
        this.maxmaht = maht;
        this.jarelmaht = 0;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass*0.74;
    }

    public double getSurvetoru() {
        return survetoru;
    }

    public void setSurvetoru(double survetoru) {
        this.survetoru = survetoru;
    }

    public double getMaht() {
        return maht;
    }

    public double getJarelmaht() {
        return jarelmaht;
    }

    public double getMaxmaht() {
        return maxmaht;
    }

    public void setMaht(double maht) {
        this.maht = maht;
    }

    public double rohk(double kiirendus){
        return (mass* kiirendus) / (survetoru * 10);
    }

    public double ccmin(){ // kutuse sissevott liitrites minutis
        return maxmaht / 5;
    }

    public double kutusekulu(double kiirendus){
        return  ccmin()* (Math.sqrt(rohk(kiirendus)) / 150);
    }

    public void veelalles(double kutusekulu){ //arvestab kui palju kutust on alles liitrides
        double uusmaht = maht - kutusekulu;
        if (uusmaht > 0) {
            setMaht(uusmaht);
            setMass(uusmaht);
        }  else if(uusmaht <= 0){
            setMaht(0.0);
        }

    }

    public void jarelpaak(){ // seda kasutada, kui kiirenuds jõuab nulli ja siis maxkutusekulu peale ule minna.
        if (maxi){
            maxi = false;
            jarelmaht =maht;
        }
    }

    public double maxkutusekulu(double jarelmaht){ // max kiirusel kasutatav kutuselkulu(teises on see null max kiirusel)
        return jarelmaht / 240;
    }
}