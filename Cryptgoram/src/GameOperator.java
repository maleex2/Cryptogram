import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameOperator {
	Game game;
	static String absFilePath = new File("").getAbsolutePath();
	static String savedGameTxt = "//utility//savedgame.txt";
	ArrayList<Game> games = new ArrayList<Game>();


	public Game loadGame(String player) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(absFilePath.concat(savedGameTxt)));
		String line;
		while((line = read.readLine()) != null) {
			String[] info = line.split("%");
			String username = info[0];
			String encryptedSentence = info[1];
			String sentence = info[2];
			String dashes = info[3];
			int gameChecker = Integer.parseInt(info[4]);
			Game game = new Game(username, encryptedSentence, sentence, dashes, gameChecker);
			games.add(game);
		}
		read.close();
		Game temp = null;
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).username.equals(player)) {
				temp = games.get(i);
			}
		}
		return temp;
	}
	
	public char checkSave(String username) {
		char temp = ' ';
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).username.equals(username)) {
				System.out.println("There is already a saved game under your username, would you like to overwrite? Y/N");
				Scanner read = new Scanner(System.in);
				temp = read.next().toUpperCase().charAt(0);
			}
		}
		return temp;
	}	

	public void saveGame(Game game) throws IOException {				
		String filePath = absFilePath.concat(savedGameTxt);
		File savedGameFile = new File(filePath);
		savedGameFile.createNewFile();
		char temp = checkSave(game.username);
		if (temp == ' ') {
			BufferedWriter output = new BufferedWriter(new FileWriter(savedGameFile));
			output.write(game.username + "%" + game.encryptedSentence + "%" + game.sentence + "%" + game.dashes + "%" + game.gameTracker);
			output.newLine();
			output.close();
		}
		else if(temp == 'Y') {
			for (int i = 0; i < games.size(); i++) {
				if (games.get(i).username.equals(game.username)) {
					games.remove(i);
				}
			}
			File tempFile = new File(absFilePath.concat("temp.txt"));
			tempFile.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(tempFile));
			BufferedReader reader = new BufferedReader(new FileReader(savedGameFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] info = line.split("%");
				String name = info[0];
				if (!(name == game.username)) {
					output.write(line);
					output.newLine();
				}	
			}
			output.write(game.username + "%" + game.encryptedSentence + "%" + game.sentence + "%" + game.dashes + "%" + game.gameTracker);
			output.newLine();
			output.close();
			reader.close();
			savedGameFile.delete();
			tempFile.renameTo(savedGameFile);
		}
	}
}
