import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is for the Junit Integration Test
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-08
 */
@RunWith(Suite.class)
@SuiteClasses({ JUnitTestCase_blankTester.class, JUnitTestCase_EmailTest.class, 
		JUnitTestCase_MongoControl.class, JUnitTestCase_MongoModel.class, JUnitTestCase_MongoMVC.class,
		JUnitTestCase_MongoView.class, JUnitTestCase_MyLoginDialog.class, JUnitTestCase_SonarPingController.class,
		JUnitTestCase_SonarPingEmailModel.class, JUnitTestCase_SonarPingModel.class, JUnitTestCase_SonarPingView.class,
		JUnitTestCase_StartingPoing.class })

public class JUnitTestSuite_IntegrationTest {
		
}
