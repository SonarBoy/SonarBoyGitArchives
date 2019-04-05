import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of EmailTest class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_EmailTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		EmailTest emailtest;
		String args[] = {"",""};
		
		emailtest = new EmailTest();
		/**
		 * Test main method 
		 */
		emailtest.main(args);
	}

}
