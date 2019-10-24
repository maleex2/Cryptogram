import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.List;

public class NumberCryptogram implements Cryptogram {
	String phrase;
	String crypticPhrase;
	HashMap<Character, Integer> encryptionScheme = new HashMap<Character, Integer>();
	
	public NumberCryptogram(String phrase) {
		this.phrase = phrase;
		this.crypticPhrase = encryptCryptogram();
	}

	//getters
	public String getSentence(){return this.phrase;}
	public String getEncryptedSentence(){return this.crypticPhrase;}
	public List<Integer> getFrequencies() {
		// TODO
		return null;
	}
	
	public String encryptCryptogram() {
		char[] encryptedMessage = phrase.toUpperCase().toCharArray();
		ArrayList<Integer> encryptedArray = new ArrayList<Integer>();
		ArrayList<String> arrayString = new ArrayList<String>();
	
		ArrayList<Character> alphabet = new ArrayList<Character>();
		for (int i = 97; i < 123; i++) {
			alphabet.add((char) i);
		}
		
		Random rand = new Random();
 		
		for (int i = 0; i < 26; i++) {
			int t = rand.nextInt(alphabet.size());
			encryptionScheme.put(alphabet.remove(t), i);
			alphabet.trimToSize();

		}
		
		System.out.println(encryptionScheme);
		System.out.println(encryptedMessage);
		
		for (int i = 0; i < encryptedMessage.length; i++) {
			encryptedArray.add(encryptionScheme.get(Character.toLowerCase(encryptedMessage[i])));
		}
		System.out.println(encryptedArray);
	
		
		String something = encryptedArray.toString();
		return something;
	}

	public char[] getAlphabet() {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] getFreqAlphabet() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Character> getDistinctChars() {
		// TODO Auto-generated method stub
		return null;
	}

}
