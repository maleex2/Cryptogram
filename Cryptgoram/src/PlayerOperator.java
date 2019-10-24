import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerOperator{
	ArrayList<Player> players;
	static String absFilePath = new File("").getAbsolutePath();
	static String playerNames = "//utility//players.txt";


	
		public PlayerOperator() {
			players = new ArrayList<Player>();
		}
		
		public void readFile() throws IOException, FileNotFoundException {
			String filePath = absFilePath.concat(playerNames);
			File playersFile = new File(filePath);
			playersFile.createNewFile();
			
			BufferedReader read = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = read.readLine()) != null) {
				Player p;
				String[] info = line.split(" ");
				p = new Player(info[0]);
				double acc = Double.parseDouble(info[1]);
				int tg = Integer.parseInt(info[2]); 
				int cg = Integer.parseInt(info[3]);
				int cp = Integer.parseInt(info[4]);
				int cw = Integer.parseInt(info[5]);
				p.setStats(acc, tg, cg, cp, cw);
				if (!players.contains(p)) {
					players.add(p);
				}
			}
			read.close();
		}
	
		public boolean findPlayer(Player p) {
			String name = p.getName();
			boolean b = false;
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getName().equals(name)) {
					b = true;
				}
			}
			return b;
		}
		
		public void addPlayer(Player p) {
			String name = p.getName();
			if(!findPlayer(p)) {
				players.add(p);
			}
			else {
				for(int i = 0; i < players.size(); i++) {
					if(players.get(i).getName().equals(name)) {
						players.remove(i);
					}
				}		
				players.add(p);
			}
		}
		public void savePlayers() throws IOException {
				deletePlayersFile();
				String filePath = absFilePath.concat(playerNames);
				File playersFile = new File(filePath);
				playersFile.createNewFile();
	            BufferedWriter output = new BufferedWriter(new FileWriter(filePath));
	            for(Player player: players) {
	    			String username = player.getName();
	    			double acc = player.getAccuracy();
	    			int tg = player.getTotalGuesses();
	    			int cg = player.getCorrectGuesses();
	    			int cp = player.getNumCryptogramsPlayed();
	    			int cc = player.getNumCryptogramsWon();
	    			output.write(username + " " + acc + " " + tg + " " + cg + " " + cp + " " + cc);
	    			output.newLine();
	    		}
	            output.close();
		}
		
		public Player loadPlayer(Player p) {
			String name = p.getName();
			for(int i = 0; i < players.size(); i++) {
				if(players.get(i).getName().equals(name)) {
					p = players.get(i);
				}
			}
			return p;
		}
		
		private void deletePlayersFile() {
			File file = new File(absFilePath.concat(playerNames));
			if (file.delete()) {
				System.out.println("Players.txt file has been modified.");
			}
			else {
				System.out.println("There doesn't appear to be a players.txt file, creating a new one.");
			}
		}
		

		public ArrayList<Player> loadAllPlayers() throws FileNotFoundException, IOException {
			readFile();
			ArrayList<Player> top10= new ArrayList<Player>();
			for(int i = 0; i < players.size(); i++) {
					top10.add(players.get(i));
			}
			Collections.sort(top10, Player.cWon);
			for(int i = 0;i<10;i++){
				if(top10.size()>=i+1) {
					System.out.println((i+1)+" "+top10.get(i).getName()+ " have won: "+ top10.get(i).getNumCryptogramsWon()+".");
				}
				else {
					for(int j = i;j<10;j++){
						System.out.println((j+1)+".");
					}
					break;
				}
		   }
			return top10;
		}

		
	}
	
