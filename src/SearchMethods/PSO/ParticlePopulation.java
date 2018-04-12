package SearchMethods.PSO;


import java.util.ArrayList;
import java.util.List;

public class ParticlePopulation {
    private List<Particle> particles;
    private int globalBest = -1;
    private double globalBestValue = 100000;


    public ParticlePopulation(List<Particle> particles) {
        this.particles = particles;
        setGlobalBest();
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
        if (particle.getPersonalBestValue() < globalBestValue) {
            globalBestValue = particle.getPersonalBestValue();
            globalBest = particle.getPersonalBest();
        }

    }

    public ParticlePopulation() {
        this.particles = new ArrayList<>();
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public int getGlobalBest() {
        return globalBest;
    }

    public void setGlobalBest() {
        if (particles != null) {
            Particle best = particles.get(0);
            for (Particle particle : particles) {
                if (particle.getFitness()<best.getFitness()) {
                    best = particle;
                }
            }
            globalBest = best.getPersonalBest();
            globalBestValue = best.getPersonalBestValue();
        }

    }
}
