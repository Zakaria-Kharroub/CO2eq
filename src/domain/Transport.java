package domain;

import java.time.LocalDate;

public class Transport extends Consomation {
    private double distanceParcourue;
    private String typeVehicule;

    public Transport(int quantite, LocalDate dateDebut, LocalDate dateFin, double distanceParcourue, String typeVehicule) {
        super(quantite, dateDebut, dateFin, TypeConsommation.Transport);
        this.distanceParcourue = distanceParcourue;
        this.typeVehicule = typeVehicule;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        switch (typeVehicule.toLowerCase()) {
            case "voiture":
                impact = distanceParcourue * 0.5;
                break;
            case "train":
                impact = distanceParcourue * 0.1;
                break;
        }
        return impact;
    }

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }
}
