package SearchMethods.EA;

import java.util.UUID;

public class Individual {
    private UUID ID;
    private int genotype = -1;
    private Double fitness = 0.0;

    public Individual(int genotype, double fitness) {
        this.ID = UUID.randomUUID();
        this.genotype = genotype;
        this.fitness = fitness;
    }

    public Individual() {
        this.ID = UUID.randomUUID();
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public void setGenotype(int genotype) {
        this.genotype = genotype;
    }

    public int getGenotype() {
        return genotype;
    }

    public UUID getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Individual that = (Individual) o;

        if (genotype != that.genotype) return false;
        if (ID != null ? !ID.equals(that.ID) : that.ID != null) return false;
        return fitness != null ? fitness.equals(that.fitness) : that.fitness == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + genotype;
        result = 31 * result + (fitness != null ? fitness.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Individual{" +
                " genotype=" + genotype +
                ", fitness=" + fitness +
                '}';
    }
}
