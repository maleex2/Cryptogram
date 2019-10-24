import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LetterCryptogram implements Cryptogram {

	String phrase;
	String crypticPhrase;

	public LetterCryptogram(String phrase) {
		this.phrase = phrase;
		this.crypticPhrase = encryptCryptogram().toUpperCase();
	}
	
	//getters
	public String getSentence(){return this.phrase;}
	public String getEncryptedSentence(){return this.crypticPhrase;}
	public List<Character> getDistinctChars(){
		List<Character> distinctChars = new ArrayList<Character>();
 		char checkLetter; 		
		String sentence = getEncryptedSentence();
 		char[] characters = sentence.toCharArray();
		/*adds character from sentence to distinctChars ArrayList if not already in it*/
 		for (int i = 0; i < characters.length; i++) {
 			checkLetter = characters[i]; 
	 		if(!distinctChars.contains(checkLetter) && checkLetter != ' '){
				distinctChars.add(checkLetter);
	 		}
 		}
		return distinctChars;
	}
	public char[] getAlphabet(){
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		return alphabet;
	}
	public double[] getFreqAlphabet(){
		//frequency of letters in the English alphabet according to an analysis done on the Concise Oxford Dictionary (9th edition, 1995)
		double[] freqAlphabet = {8.50, 2.07, 4.54, 3.38, 11.16, 1.81, 2.47, 3.00, 7.54, 0.20, 1.10, 5.49, 3.01, 6.65, 7.16, 3.17, 0.20, 7.58, 5.74, 6.95, 3.63, 1.01, 1.29, 0.29, 1.78, 0.27};
		return freqAlphabet;
	}
	public List<Integer> getFrequencies() {
		String sentence = getEncryptedSentence();
		List<Integer> occurrences = new ArrayList<Integer>();
 		List<Integer> percentages = new ArrayList<Integer>();
		char[] characters = sentence.toCharArray();
 		char checkLetter; 		
 		/*Initialises occurrences and percentages ArrayList*/
 		for (int i = 0; i < characters.length; i++ ){
 			occurrences.add(0);
 			percentages.add(0);
 		}
 		List<Character> distinctChars = this.getDistinctChars();
 		/*counts the number of times the characters from distinctChars appear in the sentence*/
 		for (int i = 0; i < distinctChars.size(); i++ ) { 
			checkLetter = distinctChars.get(i);	
			for (int a = 0; a < characters.length; a++ ) {
				if (characters[a] == checkLetter) {
					Integer count = occurrences.get(i);
					count++;
					occurrences.set(i, count);
				}
			}	
		}
 		/*get percentages of characters in cryptogram*/
 		int total = 0;
 		int percentage;
 		for (int i : occurrences){
 			total += i;
 		}
 		for (int i = 0; i < occurrences.size(); i++) {
 			percentage = (occurrences.get(i))*100/total;
 			percentages.set(i, percentage);
 		}
 		return percentages;
	}

	//generates the encrypted phrase as a sequence of random letters
	public String encryptCryptogram() {
		String encryptedMessage = phrase.toUpperCase();
		//ASCII Unicode table for more info
		ArrayList<Character> alphabet = new ArrayList<Character>();
		for(int c = 97; c <= 122; c++) {
			alphabet.add((char)c);
		}

		Random rand = new Random();
		int alphabetSizeCounter = 26;

		for(int i = 0; i < 26; i++) {
			int x = i + 65;
			int r = rand.nextInt(alphabetSizeCounter);
			encryptedMessage = encryptedMessage.replace((char) x, alphabet.get(r));
			alphabet.remove(r);
			alphabetSizeCounter--;
		}

		return encryptedMessage;
	}

}
