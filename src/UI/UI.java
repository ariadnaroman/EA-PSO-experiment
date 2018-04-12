package UI;

import SearchMethods.EA.EvolutionaryAlgorithm;
import SearchMethods.EA.IndividualFileRepo;
import SearchMethods.InformedSearchMethod;
import SearchMethods.PSO.ParticleFileRepo;
import SearchMethods.PSO.ParticleSwarmOptimization;

import java.util.Scanner;


public class UI {
    public void showMenu() {
        while (true) {
            System.out.println("");
            System.out.println();
            System.out.println("Select a search method(EA/PSO): ");
            Scanner scanner = new Scanner(System.in);
            String amCitit = scanner.next();
            if (amCitit.equals("EA")) {
                System.out.println("EA: ");
                System.out.println("Select data set (between 1 and 4): ");
                int n = scanner.nextInt();
                String dataFileName = "D:/AI/Laborator2/02_date" + n + ".txt";
                int nrDim;
                if (n == 4)
                    nrDim = 2;
                else
                    nrDim = 1;
                IndividualFileRepo individualFileRepo = new IndividualFileRepo(dataFileName,nrDim);
                EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(individualFileRepo);
                System.out.println("Use default parameters?");
                boolean useDefault = Boolean.valueOf(scanner.next());
                if (!useDefault) {
                    System.out.println("Insert initial population size: ");
                    int popsize = scanner.nextInt();
                    evolutionaryAlgorithm.setPopSize(popsize);
                    System.out.println("Insert the number of generations: ");
                    int nrGen = scanner.nextInt();
                    evolutionaryAlgorithm.setNrGenerations(nrGen);
                    System.out.println("Insert tournament size: ");
                    int tSize = scanner.nextInt();
                    evolutionaryAlgorithm.setTournamentSize(tSize);
                    System.out.println("Insert mutation rate (between 0 and 1): ");
                    double mRate = scanner.nextDouble();
                    evolutionaryAlgorithm.setMutationRate(mRate);
                    System.out.println("Insert crossover rate (between 0 and 1): ");
                    double cRate = scanner.nextDouble();
                    evolutionaryAlgorithm.setCrossoverRate(cRate);
                }
                evolutionaryAlgorithm.start();
                System.out.println("Global best is: " + evolutionaryAlgorithm.getGlobalBest());
            } else if (amCitit.equals("PSO")){
                System.out.println("PSO: ");
                System.out.println("Select data set (between 1 and 4): ");
                int n = scanner.nextInt();
                String dataFileName = "D:/AI/Laborator2/02_date" + n + ".txt";
                int nrDim;
                if (n == 4)
                    nrDim = 2;
                else
                    nrDim = 1;
                ParticleFileRepo particleFileRepo = new ParticleFileRepo(dataFileName,nrDim);
                ParticleSwarmOptimization particleSwarmOptimization = new ParticleSwarmOptimization(particleFileRepo);
                System.out.println("Use default parameters?");
                boolean useDefault = Boolean.valueOf(scanner.next());
                if (!useDefault) {
                    System.out.println("Insert initial population size: ");
                    int popsize = scanner.nextInt();
                    particleSwarmOptimization.setPopSize(popsize);
                    System.out.println("Insert the number of generations: ");
                    int nrGen = scanner.nextInt();
                    particleSwarmOptimization.setNrGenerations(nrGen);
                    System.out.println("Insert the initial velocity of the particles (double): ");
                    double initialV = scanner.nextDouble();
                    particleSwarmOptimization.setInitialVelocity(initialV);
                    System.out.println("Insert inertiaFactor (double): ");
                    double inertiaF = scanner.nextDouble();
                    particleSwarmOptimization.setInertiaFactor(inertiaF);
                    System.out.println("Insert learning rate (double): ");
                    double lRate = scanner.nextDouble();
                    particleSwarmOptimization.setLearningRate(lRate);
                    System.out.println("Insert social factor (double): ");
                    double sFactor = scanner.nextDouble();
                    particleSwarmOptimization.setSocialFactor(sFactor);
                }
                particleSwarmOptimization.start();
            } else {
                System.out.println("Please select EA/PSO");
                showMenu();
            }
        }
    }
}
