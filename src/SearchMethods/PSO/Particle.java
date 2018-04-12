package SearchMethods.PSO;

import java.util.UUID;

public class Particle implements Comparable<Particle> {
    private UUID ID;
    private int personalBest;
    private double personalBestValue = 100000;
    private double velocity;
    private int position;
    private double fitness;

    public Particle(double velocity, int position, double fitness, int globalBest) {
        this.ID = UUID.randomUUID();
        this.velocity = velocity;
        this.position = position;
        this.fitness = fitness;
        this.personalBest = position;
        this.personalBestValue = fitness;
    }

    public Particle(Particle particle) {
        this.ID = particle.getID();
        this.velocity = particle.getVelocity();
        this.position = particle.getPosition();
        this.fitness = particle.getFitness();
        this.personalBest = particle.getPersonalBest();
        this.personalBestValue = particle.getPersonalBestValue();
    }

    public Particle() {
        this.ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }


    public int getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(int personalBest) {
        this.personalBest = personalBest;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getPersonalBestValue() {
        return personalBestValue;
    }

    public void setPersonalBestValue(double personalBestValue) {
        this.personalBestValue = personalBestValue;
    }

    @Override
    public String toString() {
        return "Particle{" +
                " velocity=" + velocity +
                ", position=" + position +
                ", fitness=" + fitness +
                '}';
    }

    @Override
    public int compareTo(Particle o) {
        return Double.compare(o.getFitness(),this.getFitness());
    }
}
