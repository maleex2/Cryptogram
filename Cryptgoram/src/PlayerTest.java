import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

	@Test
	public void testCreatePlayer() {
		Player p = new Player("Tester");
		Assert.assertSame(new Player("Tester"), p);
	}
	
	@Test
	public void testIncrementTotalGuesses() {
		Player p = new Player("Tester");
		p.incrementTotalGuesses();
		Assert.assertEquals(1 , p.getTotalGuesses());
	}
	
	@Test
	public void testIncrementCorrectGuesses() {
		Player p = new Player("Tester");
		p.incrementCorrectGuesses();
		Assert.assertEquals(1 , p.getCorrectGuesses());
	}
	
	@Test
	public void testIncrementCryptogramsWon() {
		Player p = new Player("Tester");
		p.incrementCryptogramsWon();
		Assert.assertEquals(1 , p.getNumCryptogramsWon());
	}
	
	@Test
	public void testIncrementCryptogramsPlayed() {
		Player p = new Player("Tester");
		p.incrementCryptogramsPlayed();
		Assert.assertEquals(1 , p.getNumCryptogramsPlayed());
	}
	
	@Test
	public void testAccuraccy() {
		Player p = new Player("Tester");
		for (int i = 0; i < 10; i++) {
			p.incrementTotalGuesses();
		}
		for (int j = 0; j < 2; j++) {
			p.incrementCorrectGuesses();
		}
		p.updateAccurracy();
		Assert.assertEquals(20.0 , p.getAccuracy(), 0);
	}
}