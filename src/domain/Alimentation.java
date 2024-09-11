package domain;

import java.time.LocalDate;

public class Alimentation extends Consomation {
    private String typeAliment;
    private double poids;

    public Alimentation(int quantity, LocalDate dateDebut, LocalDate dateFin, String typeAliment, double poids) {
        super(quantity, dateDebut, dateFin, TypeConsommation.Alimentation);
        this.typeAliment = typeAliment;
        this.poids = poids;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        switch (typeAliment.toLowerCase()) {
            case "viande":
                impact = poids * 5.0;
                break;
            case "legume":
                impact = poids * 0.5;
                break;
        }
        return impact;
    }


    public String getTypeAliment() {
        return typeAliment;
    }

    public void setTypeAliment(String typeAliment) {
        this.typeAliment = typeAliment;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return "Alimentation{" +
                "typeAliment='" + typeAliment + '\'' +
                ", poids=" + poids +
                '}';
    }
}
