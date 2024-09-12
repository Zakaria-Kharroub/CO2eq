package domain;

import java.time.LocalDate;

public class Logement extends Consomation {
    private int id;
    private double consommationEnergie;
    private String typeEnergie;

    private Consomation consomation;

    public Logement(LocalDate dateDebut, LocalDate dateFin, double quantite, TypeConsommation typeConsommation, double consommationEnergie, String typeEnergie) {
        super(quantite, dateDebut, dateFin, typeConsommation);
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Consomation getConsomation() {
        return consomation;
    }
    public void setConsomation(Consomation consomation) {
        this.consomation = consomation;
    }

    public double getConsommationEnergie() {
        return consommationEnergie;
    }

    public void setConsommationEnergie(double consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }

    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    @Override
    public String toString() {
        return "Logement{" +
                "id=" + id +
                ", consommationEnergie=" + consommationEnergie +
                ", typeEnergie='" + typeEnergie + '\'' +
                ", consomation=" + consomation.getId() +
                '}';
    }
}
