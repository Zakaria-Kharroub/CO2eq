package domain;

import java.time.LocalDate;

public class Transport extends Consomation {
    private int distanceParcourue;
    private String typeDeVehicule;

    public Transport(int quantite, LocalDate dateDebut, LocalDate dateFin, int distanceParcourue, String typeDeVehicule) {
        super(quantite, dateDebut, dateFin);
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
    }

    @Override
    public double calculerImpact() {
        double impactConsomation = 0;
        if (this.typeDeVehicule.equals("voiture")){
            impactConsomation = 0.5;
        }else {
            impactConsomation = 0.1;
        }
        return impactConsomation * this.distanceParcourue* super.getQuantite();
    }


    public int getDistanceParcourue() {
        return distanceParcourue;
    }

    public String getTypeDeVehicule() {
        return typeDeVehicule;
    }

    public void setDistanceParcourue(int distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeDeVehicule = typeDeVehicule;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "distanceParcourue=" + distanceParcourue +
                ", typeDeVehicule='" + typeDeVehicule + '\'' +
                '}';
    }
}
