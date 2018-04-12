package SearchMethods.EA;

import SearchMethods.BeamLocalSearch;
import SearchMethods.PSO.Particle;

import java.util.Random;

public class EvolutionaryAlgorithm  extends BeamLocalSearch<Individual>{
    private int tournamentSize = 5;
    private int nrGenerations = 500;
    private int popSize = 10;
    private double mutationRate = 0.4;
    private double crossoverRate = 0.7;

    private IndividualFileRepo repository;
    private Population population;

    private Random random = new Random();

    private Individual globalBest;

    public EvolutionaryAlgorithm(IndividualFileRepo repository) {
        this.repository = repository;
        globalBest = new Individual();
    }

    public void start() {
        population = new Population();

        for (int i=0;i<popSize;i++) {
            int rand = random.nextInt();
            int size = repository.getSize();
            int pos = Math.abs(rand%size);
            population.addIndividual(repository.getIndividual(pos));
        }

        System.out.println(findFittest());
    }

    public Individual findFittest() {
        for (int i=0;i<nrGenerations;i++) {
            evolvePopulation();
            Individual fittest = population.getFittest();
            System.out.println("Fittest for generation  " + i  + ": " + fittest);
            if (fittest.getFitness()<globalBest.getFitness())
                globalBest = fittest;
        }

        return population.getFittest();
    }

    public void evolvePopulation() {
        Population newPopulation = new Population();

        for (int i = 0; i < population.size(); i++) {
            Individual M = tournamentSelection();
            Individual F = tournamentSelection();
            Individual offspring = crossover(M, F);
            newPopulation.addIndividual(offspring);
        }

        for (int i = 0; i < newPopulation.size(); i++) {
            mutateIndividual(newPopulation.getIndividual(i));
        }

        population = newPopulation;
    }

    private void mutateIndividual(Individual individual) {
        if ( random.nextDouble() > mutationRate) {
            Double value = Math.floor((individual.getGenotype()*Math.abs(random.nextInt())));
            int genotype = Math.abs(value.intValue())%repository.getSize();
            individual.setGenotype(genotype);
            individual.setFitness(repository.getIndividual(genotype).getFitness());
        }
    }

    private Individual crossover(Individual M, Individual F) {
        Individual offspring = new Individual();
        Double genotypeD = M.getGenotype()*crossoverRate+F.getGenotype()*(1-crossoverRate);
        int genotype = genotypeD.intValue();
        offspring.setGenotype(genotype);
        offspring.setFitness(repository.getIndividual(genotype%repository.getSize()).getFitness());
        return offspring;
    }

    private Individual tournamentSelection() {
        Population tournament = new Population();
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = Math.abs(random.nextInt(popSize));
            tournament.addIndividual(repository.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        tournament.removeIndividual(fittest);
        return fittest;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public void setNrGenerations(int nrGenerations) {
        this.nrGenerations = nrGenerations;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public Individual getGlobalBest() {
        return globalBest;
    }
}
