package domain;

import java.time.LocalDate;

public class Logement extends Consomation {
    private String typeEnergie;
    private int consommationEnergie;

    public Logement(int quantity, LocalDate dateDebut, LocalDate dateFin, String typeEnergie, int consommationEnergie) {
        super(quantity, dateDebut, dateFin, TypeConsommation.Logement);
        this.typeEnergie = typeEnergie;
        this.consommationEnergie = consommationEnergie;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        switch (typeEnergie.toLowerCase()) {
            case "electricite":
                impact = consommationEnergie * 1.5;
                break;
            case "gaz":
                impact = consommationEnergie * 2.0;
                break;
        }
        return impact;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }

    public int getConsommationEnergie() {
        return consommationEnergie;
    }

    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    public void setConsommationEnergie(int consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    @Override
    public String toString() {
        return "Logement{" +
                "typeEnergie='" + typeEnergie + '\'' +
                ", consommationEnergie=" + consommationEnergie +
                '}';
    }
}
