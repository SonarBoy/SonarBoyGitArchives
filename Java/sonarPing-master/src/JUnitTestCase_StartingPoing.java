import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of StartingPoint class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_StartingPoing {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		StartingPoint thepoint;
		String args[] = {"",""};
		
		thepoint = new StartingPoint();
		
		/**
		 * Test main method
		 */
		thepoint.main(args);
	}

}
