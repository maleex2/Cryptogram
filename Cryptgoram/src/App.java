import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class App {
	static String absFilePath = new File("").getAbsolutePath();
	static String sentencesDir = "//utility//quotes.txt";

	//generates a random sentence from the possible sentences in the txt file
	public static String randomSentenceGenerator(String dir) throws FileNotFoundException {
		Random rand = new Random();
		Scanner sc = new Scanner(new File(dir));
		List<String> temp =  new ArrayList<String>();
		do {
			temp.add(sc.nextLine());
		}while(sc.hasNextLine());

		String sentence = temp.get(rand.nextInt(temp.size()));
		sc.close();
		return sentence;
	}

	//players input
	public static char dashesInput() {
		System.out.println("Which letter would you like to input: ");
		Scanner scanner = new Scanner(System.in);
		char ch = ' ';
		ch = scanner.next().toUpperCase().charAt(0);
		return ch;
	}

	//encrypted sentence char input
	public static char encryptedInput(char c) {
		System.out.printf("In the place of which letter would you like to input %s: \n", c);
		Scanner scanner = new Scanner(System.in);
		char ch = ' ';
		ch = scanner.next().toUpperCase().charAt(0);
		return ch;
	}
	
	//asks player at start of game whether he wants to view top10 leaderboard
	public static void top10List(){
	    System.out.println("Type 'V' to view leaderboard or 'C' to continue:");
		Scanner scn = new Scanner(System.in);
		char ch;
		ch = scn.next().toUpperCase().charAt(0);
		PlayerOperator pO = new PlayerOperator();
		
		try {
			if(ch == 'V') { ArrayList<Player> top10 = new ArrayList<Player>(pO.loadAllPlayers());}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//asking the player for a nickname (creates new Player if none has been created)
	public static Player askPlayerName() {
		System.out.println("Welcome to the Cryptogram Game by Team L!");
		System.out.println("Do you already have a player account? Y/N: ");
		Scanner scn = new Scanner(System.in);
		char ch = ' ';
		ch = scn.next().toUpperCase().charAt(0);
		String playerName;
		Player p;
		PlayerOperator pO = new PlayerOperator();

		
		while (!(ch == 'Y' || ch == 'N')){
			System.out.println("Invalid argument.");
			System.out.println("Please retype.");
			scn = new Scanner(System.in);
			ch = scn.next().toUpperCase().charAt(0);
			System.out.println(ch);
		}

		if (ch == 'Y'){
			System.out.println("What is the player's username?");
			scn = new Scanner(System.in);
			playerName = scn.next();
			p = new Player(playerName);
			try {pO.readFile();} catch (IOException e) {e.printStackTrace();System.out.println("Could not read from file.");}
			if (pO.findPlayer(p)){
				p = pO.loadPlayer(p);				
			}
			else {
				System.out.println("Haha you jokester!");
				System.out.printf("I am adding you as a new player %s\n", playerName);
				try{pO.readFile();} catch (IOException e) {e.printStackTrace();System.out.println("Could not read from file.");}
				pO.addPlayer(p);
				try{pO.savePlayers();} catch (IOException e) {e.printStackTrace();System.out.println("Could not write to file.");}
			}
		}

		else {
			System.out.println("What would you like your player nickname to be?");
			scn = new Scanner(System.in);
			playerName = scn.next();
			p = new Player(playerName);
			try{pO.readFile();} catch (IOException e) {e.printStackTrace();System.out.println("Could not read from file.");}
			pO.addPlayer(p);
			try{pO.savePlayers();} catch (IOException e) {e.printStackTrace();System.out.println("Could not write to file.");}
		}

		return p;
	}

	//initializes player's input - dashes represent placeholder for user's input
	public static char[] initDashes(char[] dashes){
		for (int i = 0; i < dashes.length; i++) {
			if (dashes[i] != ' '){
				dashes[i] = '-';
			}

			else {
				dashes[i] = ' ';
			}
		}
		return dashes;
	}

	public static boolean checkWin(String sentence, char[] dashes){
		char[] temp = sentence.toUpperCase().toCharArray();
		return Arrays.equals(temp, dashes);
	}

	//prompts the user for which type of cryptogram they want to play
	public static Cryptogram cPick(String sentence) {
		System.out.println("Which cryptogram type would you like to pick: ");
		System.out.println("Letter-Encrypted Cryptogram - 'L' ");
		System.out.println("Number-Encrypted Cryptogram - 'N' ");
		Scanner scn = new Scanner(System.in);
		char c = scn.next().toUpperCase().charAt(0);
		while(!(c == 'L')) {
			if (c == 'N') {
				System.out.println("This feature is under construction. Please try again: ");
				scn = new Scanner(System.in);
				c = scn.next().toUpperCase().charAt(0);
			}
			else {
				System.out.println("Please try again: ");
				scn = new Scanner(System.in);
				c = scn.next().toUpperCase().charAt(0);
			}
		}
		Cryptogram l = new LetterCryptogram(sentence);
		return l;
	}
	
	//option to view the user's stats at beginning of game
	public static void showStats(String playerName) {
		System.out.println("Would you like to view your stats?:Y/N");
		Scanner scn = new Scanner(System.in);
		char ch = ' ';
		ch = scn.next().toUpperCase().charAt(0);
		PlayerOperator pO = new PlayerOperator();
		Player p = new Player(playerName);
		if (ch == 'Y') {
			try{pO.readFile();} catch (IOException e) {e.printStackTrace();System.out.println("Could not read from file.");}
			p = pO.loadPlayer(p);
			System.out.println("Nickname: " + p.getName());
			System.out.println("Accuracy: " + p.getAccuracy());
			System.out.println("Cryptograms Won/Cryptograms Played:  " + p.getNumCryptogramsWon() + " / " + p.getNumCryptogramsPlayed());
			System.out.println("Correct Guesses/Total Guesses " + p.getCorrectGuesses() + " / " + p.getTotalGuesses());
		}
	}

    //asks player if they'd like a new game or to load a saved game
	public static char newOrLoad() {
		boolean exit=false;
		char c=' ';
		while(!exit) {
			System.out.println("press N for new game, press L to load game");
			Scanner scn = new Scanner(System.in);
			char temp = scn.next().toUpperCase().charAt(0);
			if(temp=='N'||temp=='L') {
				c=temp;
				exit=true;
			}
			else {
				System.out.println("Invalid input");
			}
		}
		return c;

	}

	public static void main(String args[]) {
		try {
			Player player = askPlayerName();//player
			top10List();//asks player for leaderboards
			showStats(player.getName());//asks playes if they want to view their own stats
			String tempPath = absFilePath.concat(sentencesDir);//full path for available sentences
			char saveOrLoad = newOrLoad();
			Game cryptogramGame = null;
			Cryptogram cryp = null; //instance of encrypted cryptogram
			String sentence; //cryptogram sentence 
			String encryptedSentence; //encrypted cryptogram sentence
			char[] dashes;
			int gameChecker;//gamechecker to check if game cryptogram is fully input
			GameOperator gO = new GameOperator();
			PlayerOperator pO = new PlayerOperator();

			if (saveOrLoad == 'L') {
				cryptogramGame = gO.loadGame(player.getName());
				sentence = cryptogramGame.sentence; 
				encryptedSentence = cryptogramGame.encryptedSentence;
				dashes = cryptogramGame.dashes.toCharArray();
				gameChecker = cryptogramGame.gameTracker;
			}
			else {
				sentence = randomSentenceGenerator(tempPath); //cryptogram sentence (random)
				cryp = cPick(sentence);//instance of encrypted cryptogram
				encryptedSentence = cryp.getEncryptedSentence(); //encrypted cryptogram sentence
				dashes = encryptedSentence.toCharArray();
				dashes = initDashes(dashes);
				//temporary str to make it so that gameChecker's length is only of the letters, without any whitespace 
				String str = encryptedSentence.replaceAll("\\s+","");
				gameChecker = str.length();
			}

			
			System.out.println("Cryptogram loaded.");
			System.out.println("Your cryptogram is: " + encryptedSentence);
			System.out.println("To play, follow the instructions carefully.\n"
					+ "If you make an input mistake, you can undo by typing in '!'\n"
					+ "If you want to save your game at any point type '$'\n"
					+ "If you want to show the solution type '?'\n"
					+ "If you want to view the letter frequencies type '%'\n"
					+ "If you would like a hint type '-'");
			System.out.println("########################################");

			ArrayList<Character> charEncrypted = new ArrayList<Character>();//array to store encrypted chars
			for (int i = 0; i < encryptedSentence.length(); i++) {
				charEncrypted.add(encryptedSentence.charAt(i));
			}
			final ArrayList<Character> charEncrypted2 = new ArrayList<Character>(charEncrypted);//array to store encrypted chars AGAIN - used to assign appropriate values to missing chars in charEncrypted
			ArrayList<Character> charDashes = new ArrayList<Character>();// array to store chars for dashes - 1 unique letter in it (no duplicates)

			//GAMESTARTSHERE
			while(gameChecker != 0) {
				//first input
				System.out.println(encryptedSentence);
				System.out.println(dashes);
				char ch = dashesInput();				

				while (ch == '!' || ch == '$'|| ch == '?' || ch =='%' || ch == '-') {
					if (ch == '!') {
						if (charDashes.size() == 0) {
							System.out.println("Unable to undo.");
							ch = dashesInput();
						}
						else {
							char temp = charDashes.remove(charDashes.size()-1);
							for (int j = 0; j < encryptedSentence.length(); j++) {
								if (dashes[j] == temp) {
									dashes[j] = '-';
									charEncrypted.set(j, charEncrypted2.get(j));
									gameChecker++;				
								}
							}
						}
					}
					else if (ch == '$') {
						String dash = new String(dashes);
						Game saveGame = new Game(player.getName(), encryptedSentence, sentence, dash, gameChecker);
						gO.saveGame(saveGame);
						player.incrementCryptogramsPlayed();
						player.updateAccurracy();
						pO.readFile();
						pO.addPlayer(player);
						pO.savePlayers();
						System.out.println("Successfuly saved!");
						System.exit(0);
					}

					else if (ch == '?') {
						String solution ="";
						solution=sentence;
						System.out.println("The solution was:\n"+solution);
						System.out.println("Saving player and terminating game.");
						player.incrementCryptogramsPlayed();
						player.updateAccurracy();
						pO.readFile();
						pO.addPlayer(player);
						pO.savePlayers();
						System.exit(0);
					}
					
					else if (ch == '%') {
						List<Integer> percentages = cryp.getFrequencies();
						char[] alphabet = cryp.getAlphabet();
						double[] freqAlphabet = cryp.getFreqAlphabet();
						List<Character> distinctChars = cryp.getDistinctChars();
						/*prints each distinct character from sentence and each letter of alphabet with frequency percentage*/
						System.out.println("===============================");
						System.out.println("Cryptogram Frequencies");
						System.out.println("===============================");
				 		for (int i = 0; i < distinctChars.size(); i++ ) {
							System.out.println("Frequency of " + distinctChars.get(i) +  ": " + percentages.get(i) + "%");
						}
						System.out.println("===============================");
						System.out.println("Alphabet Frequencies");
						System.out.println("===============================");
				 		for (int i = 0; i < alphabet.length; i++ ) {
							System.out.println("Frequency of " + alphabet[i] +  ": " + freqAlphabet[i] + "%");
						}						
					}
					
					else if (ch == '-') {
						Random r = new Random();
						int sentLength = sentence.length();
						int n = r.nextInt(sentLength);
						while (dashes[n] != '-' && gameChecker != 0) {
							n = r.nextInt(sentLength);
						}
						if (gameChecker == 0) {
							break;
						}
						for (int j = 0; j < sentence.length(); j++) {
							if (sentence.toUpperCase().charAt(j) == sentence.toUpperCase().charAt(n)) {
								dashes[j] = sentence.toUpperCase().charAt(j);
								charEncrypted.set(j, ' ');
								gameChecker--;
							}
						}
						charDashes.add(sentence.toUpperCase().charAt(n));
					}
					System.out.println(gameChecker);
					if (gameChecker == 0) {
						break;
					}
					System.out.println(encryptedSentence);
					System.out.println(dashes) 	;
					ch = dashesInput();
				}

				while (charDashes.contains(ch)) {
					System.out.println("The letter has already been input.");
					System.out.println(encryptedSentence);
					System.out.println(dashes);
					ch = dashesInput();
				}

				//second input
				if (gameChecker == 0) {
					break;
				}
				char ch2 = encryptedInput(ch);
				while (ch2 == '!' || ch2 == '$'|| ch2 == '?' || ch2 == '%' || ch2 == '-') {
					if (ch2 == '!') {
						System.out.println("Unable to undo at this time, please proceed further and try again.");
					}
					else if (ch2 == '$'){
						String dash = new String(dashes);
						Game saveGame = new Game(player.getName(), encryptedSentence, sentence, dash, gameChecker);
						gO.saveGame(saveGame);
						player.incrementCryptogramsPlayed();
						player.updateAccurracy();
						pO.readFile();
						pO.addPlayer(player);
						pO.savePlayers();
						System.out.println("Successfuly saved!");
						System.exit(0);
					}
					else if (ch2 == '?') {
						String solution ="";	
						solution=sentence;
						System.out.println("The sloution was:\n"+solution);
						System.out.println("Game terminating");
						player.incrementCryptogramsPlayed();
						player.updateAccurracy();
						pO.readFile();
						pO.addPlayer(player);
						pO.savePlayers();
						System.exit(0);
					}
					else if (ch2 == '%') {
						List<Integer> percentages = cryp.getFrequencies();
						char[] alphabet = cryp.getAlphabet();
						double[] freqAlphabet = cryp.getFreqAlphabet();
						List<Character> distinctChars = cryp.getDistinctChars();
						/*prints each distinct character from sentence and each letter of alphabet with frequency percentage*/
						System.out.println("===============================");
						System.out.println("Cryptogram Frequencies");
						System.out.println("===============================");
						for (int i = 0; i < distinctChars.size(); i++ ) {
							System.out.println("Frequency of " + distinctChars.get(i) +  ": " + percentages.get(i) + "%");
						}
						System.out.println("===============================");
						System.out.println("Alphabet Frequencies");
						System.out.println("===============================");
						for (int i = 0; i < alphabet.length; i++ ) {
							System.out.println("Frequency of " + alphabet[i] +  ": " + freqAlphabet[i] + "%");
						}	
					}
					else if(ch2 == '-') {
						Random r = new Random();
						int sentLength = sentence.length();
						int n = r.nextInt(sentLength);
						while (dashes[n] != '-' && gameChecker != 0) {
							n = r.nextInt(sentLength);
						}
						if (gameChecker == 0) {
							break;
						}
						for (int j = 0; j < sentence.length(); j++) {
							if (sentence.toUpperCase().charAt(j) == sentence.toUpperCase().charAt(n)) {
								dashes[j] = sentence.toUpperCase().charAt(j);
								charEncrypted.set(j, ' ');
								gameChecker--;
							}
						}
						charDashes.add(sentence.toUpperCase().charAt(n));
					}
					System.out.println(gameChecker);
					if (gameChecker == 0) {
						break;
					}
					System.out.println(encryptedSentence);
					System.out.println(dashes);
					ch2 = encryptedInput(ch);
				}
				while (!charEncrypted.contains(ch2)) {
					if (gameChecker == 0) {
						break;
					}
					System.out.println("Incorrect value.");
					System.out.println(encryptedSentence);
					System.out.println(dashes);
					ch2 = encryptedInput(ch);
				}

				for (int j = 0; j < charEncrypted.size(); j++) {
					if (charEncrypted.get(j) == ch2) {
						dashes[j] = ch;
						player.incrementTotalGuesses();
						if (sentence.toUpperCase().charAt(j) == ch){
							player.incrementCorrectGuesses();
						}
						charEncrypted.set(j, ' ');
						gameChecker--;
					}
				}
				charDashes.add(ch);
			}

			boolean b = checkWin(sentence, dashes);
			if (b) {
				System.out.println("Congratulations, you have completed the Cryptogram!");
				player.incrementCryptogramsWon();
				player.incrementCryptogramsPlayed();
			}
			else {
				System.out.println("Sorry, i'm afraid you'll have to do better...");
				String solution = "";
				solution = sentence;
				System.out.println("The sloution was:\n"+solution);
				player.incrementCryptogramsPlayed();
			}
			player.updateAccurracy();
			pO.readFile();
			pO.addPlayer(player);
			pO.savePlayers();
			System.exit(0);
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File could not be found.");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Could not read/write from file.");
			e.printStackTrace();
		}
	}
}
