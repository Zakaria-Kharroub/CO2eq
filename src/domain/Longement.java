package domain;

import java.time.LocalDate;

public class Longement extends Consomation {
    private int consommationEnergie;
    private String typeEnergie;

    public Longement(int quantite, LocalDate dateDebut, LocalDate dateFin,int consommationEnergie, String typeEnergie) {
        super(quantite,dateDebut,dateFin);
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }
    @Override
    public double calculerImpact(){
        double impactConsomation = 0;
        if (this.typeEnergie.equals("electricite")){
            impactConsomation = 1.5;
        }else {
            impactConsomation = 2.0;
        }
        return impactConsomation * this.consommationEnergie * super.getQuantite();

    }



    public int getConsommationEnergie() {
        return consommationEnergie;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }

    public void setConsommationEnergie(int consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    @Override
    public String toString() {
        return "Longement{" +
                "consommationEnergie=" + consommationEnergie +
                ", typeEnergie='" + typeEnergie + '\'' +
                '}';
    }
}
