package domain;

public class Transport {
    private int distanceParcourue;
    private String typeDeVehicule;

    public Transport(int distanceParcourue, String typeDeVehicule) {
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
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
