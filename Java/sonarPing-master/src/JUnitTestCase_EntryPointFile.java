import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of EntryPointFile class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_EntryPointFile {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		EntryPointFile thepoint;
		String args[] = {"",""};
		
		thepoint = new EntryPointFile();
		/**
		 * Test main method 
		 */
		thepoint.main(args);
	}

}
