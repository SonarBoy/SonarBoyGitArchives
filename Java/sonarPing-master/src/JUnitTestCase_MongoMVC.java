import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of MongoMVC class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_MongoMVC {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		MongoMVC theMongoMVC;
		String args[] = {"",""};
		
		theMongoMVC = new MongoMVC();
		
		/**
		 * Test main method 
		 */
		theMongoMVC.main(args);
	}

}
