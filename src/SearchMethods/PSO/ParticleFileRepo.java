package SearchMethods.PSO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParticleFileRepo {
    private List<Particle> particles;
    private String filename;
    private int nrDim = 1;
    private int n;
    private int m;

    public ParticleFileRepo(String filename, int nrDim) {
        this.filename = filename;
        this.nrDim = nrDim;
        if (nrDim==1)
            readFromFile();
        else
            readFromFile2();
    }

    private void readFromFile() {
        particles = new ArrayList<>();
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
                int position = i;
                Particle particle = new Particle();
                particle.setFitness(value);
                particle.setPersonalBest(position);
                particle.setPersonalBestValue(value);
                particle.setPosition(position);
                particles.add(particle);
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
        particles = new ArrayList<>();
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
                    int position = i*m+j;
                    Particle particle = new Particle();
                    particle.setFitness(value);
                    particle.setPersonalBest(position);
                    particle.setPersonalBestValue(value);
                    particle.setPosition(position);
                    particles.add(particle);
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

    public List<Particle> getParticles() {
        return particles;
    }


    public Particle getParticle(int position) {
        return particles.get(position);
    }

    public int getSize() {
        return particles.size();
    }
}
