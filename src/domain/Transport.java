package domain;

import java.time.LocalDate;

public class Transport extends Consomation {
    private int distanceParcourue;
    private String typeVehicule;

    public Transport(int quantite, LocalDate dateDebut, LocalDate dateFin, int distanceParcourue, String typeDeVehicule) {
        super(quantite, dateDebut, dateFin);
        this.distanceParcourue = distanceParcourue;
        this.typeVehicule = typeDeVehicule;
    }

    @Override
    public double calculerImpact() {
        double impactConsomation = 0;
        if (this.typeVehicule.equals("voiture")){
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
        return typeVehicule;
    }

    public void setDistanceParcourue(int distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeVehicule = typeDeVehicule;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "distanceParcourue=" + distanceParcourue +
                ", typeDeVehicule='" + typeVehicule + '\'' +
                '}';
    }
}
