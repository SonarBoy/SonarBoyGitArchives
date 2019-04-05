import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of SonarPingModel class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_SonarPingModel {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		SonarPingModel themodel;
		
		themodel = new SonarPingModel("COM4");
	}
}
