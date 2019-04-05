import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of SonarPingController class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_SonarPingController {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		SonarPingModel model;
		SonarPingView view;
		SonarPingController controller;
		
		model = new SonarPingModel("COM4");	
		view = new SonarPingView();
		controller = new SonarPingController(model, view,null);
	}

}
