import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordConstructor {
	
	private CharMap m_charMap;
	private HashMap<Integer, List<String>> m_words;
	
	public WordConstructor() {
		m_charMap = new CharMap();
		m_words = new HashMap<Integer, List<String>>();
	}
	
	public void readFile(String filePath) {
		m_charMap.readFile(filePath);
	}
	
	public void inventWord() {
		StringBuilder word = new StringBuilder("");
		while(true) {
			char ch = m_charMap.findLetter(word.toString());
			if(ch == '\0')
				break;
			else
				word.append(ch);
		}
		
		int size = word.length();
		if(!m_words.containsKey(size))
			m_words.put(size,new ArrayList<String>());
		
		m_words.get(size).add(word.toString());
	}
	
	public List<String> getWordsWithLength(int length) {
		if(!m_words.containsKey(length))
			return null;
		
		return m_words.get(length);
	}
}
