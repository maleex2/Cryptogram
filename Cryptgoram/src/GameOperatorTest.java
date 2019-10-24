import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class GameOperatorTest {

	GameOperator gO = new GameOperator();
	Game g = new Game("username1", "encryptedSentence", "sentence", "A-B--", 3);
	
	@Test
	public void loadGameTest() throws IOException {
	    gO.saveGame(g);
		gO.loadGame("username1");
		Assert.assertNotNull(gO.games);
		
	}
}
