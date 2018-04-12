import UI.UI;

public class Main {

    public static void main(String[] args) {
//        System.out.println("SearchMethods.EA: ");
//        IndividualFileRepo repo = new IndividualFileRepo("D:/AI/Laborator2/02_date4.txt",2);
//        EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(repo);
//        evolutionaryAlgorithm.start();
//
//
//        System.out.println();
//        System.out.println();
//        System.out.println("SearchMethods.PSO: ");
//        ParticleFileRepo particleFileRepo = new ParticleFileRepo("D:/AI/Laborator2/02_date4.txt",2);
//        ParticleSwarmOptimization particleSwarmOptimization = new ParticleSwarmOptimization(particleFileRepo);
//        particleSwarmOptimization.start();

        UI ui = new UI();
        ui.showMenu();
    }
}
