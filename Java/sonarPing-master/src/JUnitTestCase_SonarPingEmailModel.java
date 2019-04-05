import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of SonarPingEmaiModel class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_SonarPingEmailModel {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		SonarPingEmailModel theEmailModel;
		
		theEmailModel = new SonarPingEmailModel("zwang136@my.centennialcollege.ca", "dannywang0619@gmail.com", "Danny", "123456");
	}

}
