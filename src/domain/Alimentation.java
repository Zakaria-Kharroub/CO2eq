package domain;

import java.time.LocalDate;

public class Alimentation extends Consomation {
    private String typeAliment;
    private double pois;


    public Alimentation(int quantite, LocalDate dateDebut, LocalDate dateFin , String typeAliment, double pois) {
        super(quantite,dateDebut,dateFin);
        this.typeAliment = typeAliment;
        this.pois = pois;
    }

    @Override
    public double calculerImpact(){
        double impactConsomation = 0;
        if (this.typeAliment.equals("viande")){
            impactConsomation= 5.0;
        }else {
            impactConsomation = 0.5;
        }
        return impactConsomation * this.pois * super.getQuantite();
    }


    public String getTypeAliment() {
        return typeAliment;
    }

    public double getPois() {
        return pois;
    }
    public void setTypeAliment(String typeAliment) {
        this.typeAliment = typeAliment;
    }

    public void setPois(double pois) {
        this.pois = pois;
    }

    @Override
    public String toString() {
        return "Alimentation{" +
                "typeAliment='" + typeAliment + '\'' +
                ", pois=" + pois +
                '}';
    }
}
