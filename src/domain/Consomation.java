package domain;

import java.time.LocalDate;

public abstract class Consomation {
    private int id;
    private double quantite;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private User user;

    private TypeConsommation typeConsommation;

    public Consomation(double quantite, LocalDate dateDebut, LocalDate dateFin, TypeConsommation typeConsommation) {
        this.quantite = quantite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeConsommation = typeConsommation;
    }

    public User getUser() {
        return user;
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
        return "Consomation{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", user=" + user +
                ", typeConsommation=" + typeConsommation +
                '}';
    }
}
