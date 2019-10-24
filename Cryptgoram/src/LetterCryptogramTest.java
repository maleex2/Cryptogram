import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class LetterCryptogramTest {
	LetterCryptogram l = new LetterCryptogram("aaa bbb");
	@Test
	public void testGetFrequencies() {
		/*initialises test case*/
		String s = "aaa bbb";
		char[] characters = s.toCharArray();		
		int[] a = {50, 50};
		List<Integer> percentages = new ArrayList<Integer>();
		for (int i : a) {
			percentages.add(i);
		}
		for (int i = 2; i < characters.length; i++ ){
 			percentages.add(0);
 		}
		/*test*/
		Assert.assertEquals(percentages, l.getFrequencies());
	}
}
