import java.util.Arrays;

public class Skill {
 
	private static CharMap charMap = null;
 
	public static void setCharMap(CharMap cm) {
		charMap = cm;
	}
	
    public static int getSkill(Individual individual) {
    	int size = individual.size();
    	
    	if(size <= 0)
    		return 0;
    	
    	char[] genes = new char[size+2];
    	
    	for(int i=0; i<size; i++)
    		genes[i+1] = individual.getGene(i);
    	
    	genes[0] = CharMap.BEGIN_VALUE;
    	genes[size+1] = CharMap.END_VALUE;
    	
        int skill = 0;
        for (int i = 0; i < genes.length; i++) {
        	for (int j = 1; j < 5; j++) {
        		if (genes.length - i > j) {
        			char[] pre = Arrays.copyOfRange(genes, i, i+j);
        			skill += j*charMap.getOccurence(String.copyValueOf(pre), genes[i+j]);
        		}
        	}
        }
        
        skill = Math.round(skill * 100 / (size+1));
        return skill;
    }
    
}