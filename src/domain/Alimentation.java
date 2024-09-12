package domain;

import java.time.LocalDate;

public class Alimentation  extends Consomation{
    private int id;
    private double poids;
    private String typeAliment;
    Consomation consomation;

   public Alimentation(LocalDate dateDebut, LocalDate dateFin, double quantite, TypeConsommation typeConsommation,User user, double poids, String typeAliment) {
        super(quantite, dateDebut, dateFin, typeConsommation,user);
        this.poids = poids;
        this.typeAliment = typeAliment;
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

    public double getPoids() {
        return poids;
    }
    public void setPoids(double poids) {
        this.poids = poids;
    }
    public String getTypeAliment() {
        return typeAliment;
    }
    public void setTypeAliment(String typeAliment) {
        this.typeAliment = typeAliment;
    }


    @Override
    public String toString() {
        return "Alimentation{" +
                "id=" + id +
                ", poids=" + poids +
                ", typeAliment='" + typeAliment + '\'' +
                super.toString() +
                '}';
    }

}
