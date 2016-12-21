import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Population {

	private ArrayList<Individual> individuals = new ArrayList<Individual>();
    
    public void fillPopulationFromFile(final String filePath) {
    	BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			String currentWord;
			while ((currentWord = br.readLine()) != null)
				individuals.add(new Individual(currentWord));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
 
    public Individual getIndividual(final int index) {
        return individuals.get(index);
    }
 
    public Individual getMoreCompetent() {
        Individual moreCompetent = getIndividual(0);

        int size = size();
        for (int i = 1; i < size; i++)
            if (moreCompetent.getCompetence() <= getIndividual(i).getCompetence())
                moreCompetent = getIndividual(i);
        
        return moreCompetent;
    }
 
    public int size() {
        return individuals.size();
    }
    
    public void insereIndividual(Individual indiv) {
        individuals.add(indiv);
    }    
}