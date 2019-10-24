import java.util.Comparator;

public class Player{
	String username;
	double percentageAccuracy;
	int correctGuesses;
	int totalGuesses;
	int cryptogramsPlayed;
	int cryptogramsWon;


	//constructor
	public Player(String username) {
		this.username = username;
		percentageAccuracy = 100;
		totalGuesses = 0;
		correctGuesses = 0;
		cryptogramsPlayed = 0;
		cryptogramsWon = 0;
	}

	//updaters
	private void decrementTotalGuesses(){this.totalGuesses--;}
	private void decrementCryptogramsWon(){this.cryptogramsWon--;}
	private void decrementCryptogramsPlayed(){this.cryptogramsPlayed--;}
	private void decrementCorrectGuesses(){this.correctGuesses--;}
	
	public void incrementTotalGuesses(){this.totalGuesses++;}
	public void incrementCryptogramsWon(){this.cryptogramsWon++;}
	public void incrementCryptogramsPlayed(){this.cryptogramsPlayed++;}
	public void incrementCorrectGuesses(){this.correctGuesses++;}

	public void updateAccurracy(){
		Integer temp = new Integer(correctGuesses);
		double cG = temp.doubleValue();
		
		Integer temp2 = new Integer(totalGuesses);
		double tG = temp2.doubleValue();
		
		this.percentageAccuracy = (((cG/tG))*100);
		}
	
	public void setStats(double acc, int tg, int cg, int cp, int cw) {
		percentageAccuracy = acc;
		totalGuesses = tg;
		correctGuesses = cg;
		cryptogramsPlayed = cp;
		cryptogramsWon = cw;	
	}

	//getters
	public int getCorrectGuesses(){return this.correctGuesses;}
	public double getAccuracy(){return this.percentageAccuracy;}
	public int getNumCryptogramsWon(){return this.cryptogramsWon;}
	public int getNumCryptogramsPlayed(){return this.cryptogramsPlayed;}
	public int getTotalGuesses() {return this.totalGuesses;}
	public String getName() {return this.username;}
	
	public static Comparator<Player> cWon = new Comparator<Player>() {
		public int compare(Player a, Player b) {
			return b.getNumCryptogramsWon()-a.getNumCryptogramsWon();
		}
	};
}