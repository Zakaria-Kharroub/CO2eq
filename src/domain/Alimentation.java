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


//    public double calculerImpact() {
//        double impactConsumption=0;
//        if(this.typeDeVehicule=="voiture") impactConsumption=0.5;
//        else impactConsumption=0.1;
//        return super.getCarbon()this.distanceParcourueimpactConsumption;
//    }
    @Override
    public double calculerImpact() {
        double impactConsomation = 0;
        if (this.typeAliment.equals("viande")){
            impactConsomation= 5.0;
        }else {
            impactConsomation = 0.5;
        }
        return impactConsomation * this.poids* super.getQuantite();
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
        return
                " id consomation :" + id + " " +
                super.toString() +
                ", typeAliment : " + typeAliment+
                ", poids :" + poids
        ;
    }

}
