package domain;

public class Longement {
    private int consommationEnergie;
    private String typeEnergie;

    public Longement(int consommationEnergie, String typeEnergie) {
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
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
