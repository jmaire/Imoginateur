public class Main {

	public static String filePath = "./input/bg.txt";
	
	public static void main(String[] args) {
		
		CharMap charMap = new CharMap();
		charMap.readFile(filePath);
		Skill.setCharMap(charMap);
		Population initPop = new Population();
		initPop.fillPopulationFromFile(filePath);

		for(int i = 0; i < 8; i++) {
			System.out.println("Génération "+i);
			initPop = Algorithm.evolvePopulation(initPop);
		}
		
		System.out.println("Population finale (" + initPop.size() + " individus) :");
		int size = initPop.size();
		for(int i = 0; i < size; i++) {
			System.out.println(initPop.getIndividual(i) + " : " + initPop.getIndividual(i).getCompetence());
		}
		
		System.out.println("Gagnant : " + initPop.getMoreCompetent() + " avec " + initPop.getMoreCompetent().getCompetence());
	}
}
