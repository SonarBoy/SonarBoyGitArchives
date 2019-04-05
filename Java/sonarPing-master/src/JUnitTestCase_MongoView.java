import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of MongoView
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_MongoView {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		MongoView theView;
		
		theView = new MongoView();			
		theView.setVisible(true);
	}

}
