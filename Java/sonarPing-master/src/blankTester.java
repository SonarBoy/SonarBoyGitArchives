
/**
 * This class test the email functionality
 * @author Joshua 
 * @version 1.0
 * @since 2016-02-18
 */

public class blankTester {
/**
 * This method is the driver to test email functionality
 * @param args
 */
	public static void main(String[] args) {
		SonarPingEmailModel runner = new SonarPingEmailModel("joshua.l99.c@gmail.com,john.orion.ray@gmail.com", 
				"john.orion.ray@gmail.com", "john.orion.ray@gmail.com", "phantom1237");
		runner.sessionInitialize();
		runner.run();
	}
	
}
