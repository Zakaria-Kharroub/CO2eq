package domain;

import java.time.LocalDate;

public abstract class Consomation {
    private int quantite;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private TypeConsommation typeConsommation;

    public Consomation(int quantite, LocalDate dateDebut, LocalDate dateFin, TypeConsommation typeConsommation) {
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeConsommation = typeConsommation;

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

    public TypeConsommation getTypeConsommation() {
        return typeConsommation;
    }

}