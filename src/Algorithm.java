import java.util.Random;

public class Algorithm {
 
	//private static CharactersEnumeration charactersEnumeration = new CharactersEnumeration();
	
	//private static final double UNIFORM_RATE = 0.5;
	
	private static final int WORD_CUT_NUMBER_MIN = 2;
	private static final int WORD_CUT_NUMBER_BONUS = 4;
	private static final int WORD_CUT_SIZE_MIN = 1;
	private static final int WORD_CUT_SIZE_MAX = 3;
	
    private static final double MUTATION_RATE = 0.00015;
    private static final int TOURNAMENT_SIZE = 5; 
 
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population();
        //newPopulation.fillPopulationFromFile(filePath);
 
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = 0; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.insereIndividual(newIndiv);
        }
 
        // Mutate population
        for (int i = 0; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }
 
        return newPopulation;
    }
 
    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual[] parents = new Individual[2];
        parents[0] = indiv1;
        parents[1] = indiv2;
        
        int[] it = new int[2];
        it[0] = 0;
        it[1] = 0;
        
        int[] size = new int[2];
        size[0] = parents[0].size();
        size[1] = parents[1].size();
       
        
        String word = "";
        int wordCutNumber = WORD_CUT_NUMBER_MIN + (int)(Math.random() * WORD_CUT_NUMBER_BONUS);
        for(int i = 0; i < wordCutNumber; i++) {
        	int rand = (int) Math.round(Math.random());
        	int wordCutSize = WORD_CUT_SIZE_MIN + (int)(Math.random() * Math.min(WORD_CUT_SIZE_MAX, parents[rand].size()));
        	int startCutIndex = (int) ((parents[rand].size() - wordCutSize + 1) * Math.random());
        	for(int j = startCutIndex; j < wordCutSize; j++) {
        		word += parents[rand].getGene(j);
        	}
        }
        
        return new Individual(word);
    }
 
    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                //indiv.setGene(i, charactersEnumeration.getRandomCharacter());
            	Random random = new Random();
            	indiv.setGene(i, (char)('a' + random.nextInt(26)));
            }
        }
    }
 
    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population();
        
        // For each place in the tournament get a random individual
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.insereIndividual(pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getMoreCompetent();
        return fittest;
    }
}