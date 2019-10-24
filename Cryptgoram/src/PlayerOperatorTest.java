import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class PlayerOperatorTest {
	PlayerOperator pO = new PlayerOperator();
	Player p = new Player("dummy");
	
	@Test
	public void testTop10() throws Throwable {
		ArrayList<Player> top10 = new ArrayList<Player>(pO.loadAllPlayers());
		Assert.assertNotNull("Top10 List loaded succesfully", top10);
	}
	
	@Test
	public void testFindPlayer() {
		pO.addPlayer(p);
		Assert.assertTrue(pO.findPlayer(p));
	}
	
	@Test
	public void testLoadPlayer() {
		pO.addPlayer(p);
		Assert.assertEquals(p, pO.loadPlayer(p));
	}
	
	@Test
	public void testReadFile() throws Throwable {
		pO.readFile();
		Assert.assertNotNull("File was read succesfully", pO.players);
	}
}
