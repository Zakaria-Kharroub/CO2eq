package domain;

import java.time.LocalDate;

public abstract class Consomation {
    private int id;
    private double quantite;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private User user;

    private TypeConsommation typeConsommation;

    public abstract double calculerImpact();

    public Consomation(double quantite, LocalDate dateDebut, LocalDate dateFin, TypeConsommation typeConsommation, User user) {
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeConsommation = typeConsommation;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public double getQuantite() {
        return quantite;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public TypeConsommation getTypeConsommation() {
        return typeConsommation;
    }

    @Override
    public String toString() {
        return
                user +
                ", typeConsommation :" + typeConsommation +
                ", quantite=" + quantite +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin

                ;
    }
}
