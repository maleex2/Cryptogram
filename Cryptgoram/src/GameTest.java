import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {
	Game game = new Game("tester", "XUXU", "ABAB", "----", 4);
	
		@Test
		public void testGetUsername() {
			Assert.assertEquals("tester", game.getUsername());
		}
		
		@Test
		public void testGetSentence() {
			Assert.assertEquals("ABAB", game.getSentence());
		}
		
		@Test
		public void testGetEncryptedSentence() {
			Assert.assertEquals("XUXU", game.getEncryptedSentence());
		}
		
		@Test
		public void testGetDasges() {
			Assert.assertEquals("----" , game.getDashes());
		}
		
		@Test
		public void testGetGameTracker() {
			Assert.assertEquals(4 , game.getGameTracker());
		}
}
