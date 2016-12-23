import java.util.Arrays;

public class Skill {
 
	private static CharMap charMap = null;
 
	public static void setCharMap(CharMap cm) {
		charMap = cm;
	}
	
    public static double getSkill(Individual individual) {
    	int size = individual.size();
    	
    	if(size <= 0)
    		return 0;
    	
    	char[] genes = new char[size+2];
    	
    	for(int i=0; i<size; i++)
    		genes[i+1] = individual.getGene(i);
    	
    	genes[0] = CharMap.BEGIN_VALUE;
    	genes[size+1] = CharMap.END_VALUE;
    	
        float skill = 0;
        for (int i = 0; i < genes.length; i++) {
        	for (int j = 1; j < 5; j++) {
        		if (genes.length - i > j) {
        			char[] pre = Arrays.copyOfRange(genes, i, i+j);
        			skill += j/(j+1.f) * charMap.getFrequency(String.copyValueOf(pre), genes[i+j]) * charMap.getOccurence(String.copyValueOf(pre), genes[i+j]);
        		}
        	}
        }
        
        skill = (float) (skill * 100.f / Math.sqrt(size+1.f));
        return skill;
    }
    
}