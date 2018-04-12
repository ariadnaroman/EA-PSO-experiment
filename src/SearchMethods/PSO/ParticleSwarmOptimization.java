package SearchMethods.PSO;

import SearchMethods.BeamLocalSearch;

import java.util.Random;

public class ParticleSwarmOptimization extends BeamLocalSearch<Particle>{
    private ParticleFileRepo particleFileRepo;
    private ParticlePopulation population;

    private int popSize = 100;
    private double initialVelocity = 0;
    private double inertiaFactor = 0.7;
    private double learningRate = 1.4;
    private double socialFactor = 0.9;
    private int nrGenerations = 1000;

    private Random random = new Random();

    public ParticleSwarmOptimization(ParticleFileRepo particleFileRepo) {
        this.particleFileRepo = particleFileRepo;
    }

    public double getSocialFactor() {
        return socialFactor;
    }

    public void setSocialFactor(double socialFactor) {
        this.socialFactor = socialFactor;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public double getInertiaFactor() {
        return inertiaFactor;
    }

    public void setInertiaFactor(double inertiaFactor) {
        this.inertiaFactor = inertiaFactor;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public int getNrGenerations() {
        return nrGenerations;
    }

    public void setNrGenerations(int nrGenerations) {
        this.nrGenerations = nrGenerations;
    }

    public void start() {
        population = new ParticlePopulation();
        int size = particleFileRepo.getSize();
        for (int i=0;i<popSize;i++) {
            int index = Math.abs(random.nextInt(size));
            Particle particle = particleFileRepo.getParticle(index);
            particle.setVelocity(initialVelocity);
            population.addParticle(particle);
        }
        population.setGlobalBest();
        System.out.println(findFittest());
    }

    public Particle findFittest() {
        for (int i=0;i<nrGenerations;i++) {
            ParticlePopulation particlePopulation = new ParticlePopulation();
            population.setGlobalBest();
            for (int j=0;j<popSize;j++) {
                Particle particle = population.getParticles().get(j);
                moveParticle(particle);
                particlePopulation.addParticle(particle);
            }
            particlePopulation.setGlobalBest();
            Particle fittest = particleFileRepo.getParticle(particlePopulation.getGlobalBest());
            System.out.println("Fittest for generation " + i + ": " + fittest);
            population = particlePopulation;
        }
        return particleFileRepo.getParticle(population.getGlobalBest());
    }

    private void moveParticle(Particle particle) {
        Double v = inertiaFactor*particle.getPosition() + learningRate*random.nextDouble()*Math.abs(particle.getPersonalBest()-particle.getPosition()) + socialFactor*random.nextDouble()*Math.abs(population.getGlobalBest()- particle.getPosition());
        Double x = particle.getPosition() + v;
        particle.setPosition(x.intValue()%particleFileRepo.getSize());
        particle.setVelocity(v);
        particle.setFitness(particleFileRepo.getParticle(particle.getPosition()).getFitness());
    }

    public ParticlePopulation getPopulation() {
        return population;
    }
}
