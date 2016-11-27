import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CharMap {
	
	private static final char BEGIN_VALUE = '\0';
	private static final char END_VALUE = '\0';
	
	private HashMap<Character, HashMap<Character, Integer>> m_charMap;
	private HashMap<String, HashMap<Character, Integer>> m_stringMap; // TODO
	
	private HashMap<Character, HashMap<Character, Float>> m_charMap2;
	
	public CharMap() {
		m_charMap = new HashMap<Character, HashMap<Character, Integer>>();
		m_stringMap = new HashMap<String, HashMap<Character, Integer>>();

		m_charMap2 = new HashMap<Character, HashMap<Character, Float>>();
	}
	
	public void readFile(String filePath) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			String currentWord;
			while ((currentWord = br.readLine()) != null) {
				insereWord(currentWord);
			}
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
		computePourcentage();
	}
	
	private void insereWord(String word) {
		if(word.length() <= 0)
			return;
		
		insereChar(BEGIN_VALUE, normalizeCharacter(word.charAt(0)));
		
		int size = word.length();
		for(int i=1; i<size; i++) {
			insereChar(normalizeCharacter(word.charAt(i-1)) , normalizeCharacter(word.charAt(i)));
			if(i > 1) {
				String substring = normalizeString(word.substring(0,i));
				insereString(substring, normalizeCharacter(word.charAt(i)));
			}
		}
		insereChar(normalizeCharacter(word.charAt(size-1)), END_VALUE);
		
		String substring = normalizeString(word.substring(0,size));
		insereString(substring, END_VALUE);
	}
	
	private void insereChar(char pre, char suf) {
		if(!m_charMap.containsKey(pre))
			m_charMap.put(pre, new HashMap<Character, Integer>());

		HashMap<Character, Integer> map = m_charMap.get(pre);
		
		if(!map.containsKey(suf))
			map.put(suf, new Integer(1));
		else
			map.put(suf, map.get(suf)+1);
	}
	
	private void insereString(String pre, char suf) {
		if(!m_stringMap.containsKey(pre))
			m_stringMap.put(pre, new HashMap<Character, Integer>());
		
		HashMap<Character, Integer> map = m_stringMap.get(pre);
		
		if(!map.containsKey(suf))
			map.put(suf, new Integer(1));
		else
			map.put(suf, map.get(suf)+1);
	}
	
	private char normalizeCharacter(char ch) {
		if(ch >= 'A' && ch <= 'Z')
			return (char)(ch + 'a' - 'A');
		
		return ch;
	}
	
	private String normalizeString(String st) {
		StringBuilder stringBuilder = new StringBuilder(st);
		
		int size = stringBuilder.length();
		for(int i=0; i<size; i++) {
			stringBuilder.setCharAt(i, normalizeCharacter(stringBuilder.charAt(i)));
		}
		
		return stringBuilder.toString();
	}
	
	public char findLetter(String word) {
		char ch;
		if(word.length() == 0)
			ch = BEGIN_VALUE;
		else
			ch = word.charAt(word.length()-1);
		
		HashMap<Character, Float> map = m_charMap2.get(ch);
		
		Random random = new Random();
		float randomValue = random.nextFloat();
		float currentValue = 0.f;
		char lastKey = END_VALUE;
		
		for (Map.Entry<Character, Float> entry : map.entrySet()) {
			currentValue += entry.getValue();
			lastKey = entry.getKey();
			
			if(randomValue <= currentValue)
				return lastKey;
		}
		
		return lastKey;
	}
	
	private void computePourcentage() {
		m_charMap2.clear();
		for (Map.Entry<Character, HashMap<Character, Integer>> entry1 : m_charMap.entrySet()) {
			char key = entry1.getKey();
			m_charMap2.put(key, new HashMap<Character, Float>());
			int count = getTotal(key);
			for (Map.Entry<Character, Integer> entry2 : entry1.getValue().entrySet()) {
				m_charMap2.get(key).put(entry2.getKey(), new Float((float)entry2.getValue() / count));
			}
		}
	}
	
	private int getTotal(char ch) {
		int count = 0;
		HashMap<Character, Integer> map = m_charMap.get(ch);
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			count += entry.getValue();
		}
		return count;
	}
	
	public String toString() {
		String string = "";
		
		for (Map.Entry<Character, HashMap<Character, Float>> entry1 : m_charMap2.entrySet()) {
			string += "[" + entry1.getKey() + "]:\n";
			for (Map.Entry<Character, Float> entry2 : entry1.getValue().entrySet()) {
				string += "\t" + entry2.getKey() + " = " + entry2.getValue() + "\n";
			}
		}
		
		string += "---------------------------------------\n";
		
		for (Map.Entry<String, HashMap<Character, Integer>> entry1 : m_stringMap.entrySet()) {
			if(entry1.getKey().length() > 3)
				continue;
			
			string += "[" + entry1.getKey() + "]:\n";
			for (Map.Entry<Character, Integer> entry2 : entry1.getValue().entrySet()) {
				string += "\t" + entry2.getKey() + " = " + entry2.getValue() + "\n";
			}
		}
		
		return string;
	}
}
