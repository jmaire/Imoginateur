public class Skill {
 
	private static CharMap charMap = null;
 
	public static void setCharMap(CharMap cm) {
		charMap = cm;
	}
	
    public static int getSkill(Individual individual) {
    	int size = individual.size();
    	
    	if(size <= 0)
    		return 0;
    	
        int skill = charMap.getOccurence(CharMap.BEGIN_VALUE + "", individual.getGene(0));
        
        size--;
        for (int i = 0; i < size; i++) {
            skill += charMap.getOccurence(individual.getGene(i) + "", individual.getGene(i+1));
        }
        
        skill += charMap.getOccurence(individual.getGene(size) + "", CharMap.END_VALUE);
        
        skill /= size + 2;
        return skill;
    }
    
}