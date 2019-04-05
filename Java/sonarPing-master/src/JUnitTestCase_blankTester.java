import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of blankTester class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_blankTester {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		blankTester theblanktester;
		String args[] = {"",""};
		
		theblanktester = new blankTester();
		
		/**
		 * Test main method 
		 */
		theblanktester.main(args);
	}

}
