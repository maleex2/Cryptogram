import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	String username;
	String sentence;
	String encryptedSentence;
	String dashes;
	int gameTracker;
	
	public Game(String username, String encryptedSentence, String sentence, String dashes, int gameTracker) {
		this.username = username;
		this.encryptedSentence = encryptedSentence;
		this.sentence = sentence;
		this.dashes = dashes;
		this.gameTracker = gameTracker;	
	}
	
	//getters
	public String getUsername() {return this.username;}
	public String getSentence() {return this.sentence;}
	public String getEncryptedSentence() {return this.encryptedSentence;}
	public String getDashes() {return this.dashes;}
	public int getGameTracker() {return this.gameTracker;}
}
