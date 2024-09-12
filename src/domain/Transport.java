package domain;

import java.time.LocalDate;

public class Transport extends Consomation {
    private int id;
    private double distanceParcourue;
    private String typeDeVehicule;
    Consomation consomation;


//    public double calculerImpact() {
//        double impactConsumption=0;
//        if(this.typeDeVehicule=="voiture") impactConsumption=0.5;
//        else impactConsumption=0.1;
//        return super.getCarbon()this.distanceParcourueimpactConsumption;
//    }

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






    public Transport(LocalDate dateDebut, LocalDate dateFin,double quantite,TypeConsommation typeConsommation,User user, double distanceParcourue, String typeDeVehicule) {
        super(quantite, dateDebut, dateFin, typeConsommation,user);
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
        return
                " id consomation :" + id + " " +
                super.toString() +

                ", typeDeVehicule : " + typeDeVehicule+
                ", distanceParcourue :" + distanceParcourue

                ;
    }
}
