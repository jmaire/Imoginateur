public class Main {

	public static String filePath = "./input/priest.txt";
	
	private static final int GENERATION_NUMBER = 6;
	
	public static void main(String[] args) {
		
		CharMap charMap = new CharMap();
		charMap.readFile(filePath);
		Skill.setCharMap(charMap);
		Population initPop = new Population();
		initPop.fillPopulationFromFile(filePath);

		System.out.println("Population de depart (" + initPop.size() + " individus) :");
		for(int i = 0; i < initPop.size(); i++) {
			Individual indiv = initPop.getIndividual(i);
			System.out.println(indiv.getCompetence() + " => " + indiv);
		}
		
		for(int i = 0; i < GENERATION_NUMBER; i++) {
			System.out.println("#Génération "+i);
			initPop = Algorithm.evolvePopulation(initPop);
			Individual indiv = initPop.getMoreCompetent();
			System.out.println("Meilleur individu: " + indiv.getCompetence() + " : " + initPop.getIndividual(i).getLifespan() + " => " + indiv);
		}
		
		System.out.println("\nPopulation finale = " + initPop.size() + " individus");
		System.out.println("Top :");
		for(int i = 0; i < 7; i++) {
			Individual indiv = initPop.getMoreCompetent();
			System.out.println(indiv.getCompetence() + " : " + initPop.getIndividual(i).getLifespan() + " => "+ indiv);
			initPop.removeIndividual(indiv);
		}
	}
}
