package domain;

public class Alimentation {
    private String typeAliment;
    private double pois;


    public Alimentation(String typeAliment, double pois) {
        this.typeAliment = typeAliment;
        this.pois = pois;
    }


    public String getTypeAliment() {
        return typeAliment;
    }

    public double getPois() {
        return pois;
    }
    public void setTypeAliment(String typeAliment) {
        this.typeAliment = typeAliment;
    }

    public void setPois(double pois) {
        this.pois = pois;
    }

    @Override
    public String toString() {
        return "Alimentation{" +
                "typeAliment='" + typeAliment + '\'' +
                ", pois=" + pois +
                '}';
    }
}
