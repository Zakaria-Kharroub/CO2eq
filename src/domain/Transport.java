package domain;

import java.time.LocalDate;

public class Transport extends Consomation {
    private int id;
    private double distanceParcourue;
    private String typeDeVehicule;
    Consomation consomation;



    public Transport(LocalDate dateDebut, LocalDate dateFin,double quantite,TypeConsommation typeConsommation, double distanceParcourue, String typeDeVehicule) {
        super(quantite, dateDebut, dateFin, typeConsommation);
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
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

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }
    public String getTypeDeVehicule() {
        return typeDeVehicule;
    }
    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeDeVehicule = typeDeVehicule;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", distanceParcourue=" + distanceParcourue +
                ", typeDeVehicule='" + typeDeVehicule + '\'' +
                ", consomation=" + consomation.getId() +
                '}';
    }
}
