package domain;

import java.time.LocalDate;

public abstract class Consomation {
    private int quantite;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Consomation(int quantite, LocalDate dateDebut, LocalDate dateFin) {
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    public abstract double calculerImpact();


    public int getQuantite() {
        return quantite;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}