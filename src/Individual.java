
public class Individual {

	private char[] genes = null;
	private Integer competence = null;
	
	public Individual() {}
	
	public Individual(String word) {
		genes = CharMap.normalizeString(word).toCharArray();
	}

	public void generateRandomIndividual() {
		int size = size();
		for (int i=0; i < size; i++) {
			char gene = (char) ((int)(Math.random()*26) + 'a');
			genes[i] = gene;
		}
	}
	
	public char getGene(int index) {
		return genes[index];
	}
	
	public char[] getGenes() {
		return genes;
	}
	
	public void setGene(int index, char value) {
		genes[index] = value;
		competence = null;
	}

	public int size() {
		if(null == genes)
			return -1;
		else
			return genes.length;
	}

	public int getCompetence() {
		if (competence == null) {
			competence = Skill.getSkill(this);
		}
		return competence;
	}

	@Override
	public String toString() {
		return String.valueOf(genes);
	}
}