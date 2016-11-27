
public class Main {

	public static void main(String[] args) {
		CharMap charMap = new CharMap();
		charMap.readFile("D:/Downloads/esmo.txt");
		System.out.println(charMap.toString());
		
		/*
		WordConstructor wordConstructor = new WordConstructor();
		wordConstructor.readFile("D:/Downloads/esmo.txt");
		for(int i=0; i<1000; i++)
			wordConstructor.inventWord();
		/*
		for(int i=3; i<9; i++)
			System.out.println(wordConstructor.getWordsWithLength(i));*/
	}
}
