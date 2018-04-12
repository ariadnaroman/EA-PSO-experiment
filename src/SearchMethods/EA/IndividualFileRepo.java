package SearchMethods.EA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IndividualFileRepo {
    private List<Individual> individuals;
    private String filename;
    private int nrDim = 1;
    private int n;
    private int m;

    public IndividualFileRepo(String filename, int nrDim) {
        this.filename = filename;
        this.nrDim = nrDim;
        if (nrDim == 1)
            readFromFile();
        else
            readFromFile2();
    }

    private void readFromFile() {
        individuals = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            if (line != null) {
                n = Integer.parseInt(line.trim());
            }
            line = bufferedReader.readLine();
            String elems[] = line.split(" ");
            for (int i=0;i<n;i++) {
                double value = Double.parseDouble(elems[i]);
                int genotype = i;
                Individual individual = new Individual(genotype, value);
                individuals.add(individual);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");
        }
    }

    private void readFromFile2() {
        individuals = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            if (line != null) {
                n = Integer.parseInt(line.trim());
            }
            line = bufferedReader.readLine();
            if (line != null) {
                m = Integer.parseInt(line.trim());
            }

            for (int i=0;i<n;i++) {
                line = bufferedReader.readLine();
                String elems[] = line.split(" ");
                for (int j=0;j<m;j++) {
                    double value = Double.parseDouble(elems[j]);
                    int genotype = i*m+j;
                    Individual individual = new Individual(genotype, value);
                    individuals.add(individual);
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");
        }
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public Individual getIndividual(UUID uuid) {
        for (Individual individual : individuals) {
            if (individual.getID().equals(uuid))
                return individual;
        }
        return null;
    }

    public Individual getIndividual(int genotype) {
        return individuals.get(genotype);
    }

    public int getSize() {
        return individuals.size();
    }
}
