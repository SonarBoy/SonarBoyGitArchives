import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of MyLoginDialog class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_MyLoginDialog {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		MyLoginDialog theLoginDialog;
		final JFrame frame = new JFrame("");
		
		theLoginDialog = new MyLoginDialog(frame);		
	}

}
