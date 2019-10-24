import java.util.List;

public interface Cryptogram {
	String encryptCryptogram();
	List<Integer> getFrequencies();
	String getEncryptedSentence();
	String getSentence();
	char[] getAlphabet();
	double[] getFreqAlphabet();
	List<Character> getDistinctChars();	
}
