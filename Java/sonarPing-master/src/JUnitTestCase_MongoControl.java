import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of MongoControl class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_MongoControl {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		MongoControl theControl;
		
		MongoView theView = new MongoView();
		MongoModel theModel = new MongoModel();;
		
		theControl = new MongoControl(theView, theModel);
		/**
		 * Test clearFields method 
		 */
		theControl.clearFields();
		
		/**
		 * Test validate method 
		 */
		assertFalse(theControl.validate());
	}

}
