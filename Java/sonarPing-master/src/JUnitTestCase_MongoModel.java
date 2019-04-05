import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for the unit test of MongoModel class
 * @author Zhaoduan
 * @version 1.0
 * @since 2016-04-06
 *
 */
public class JUnitTestCase_MongoModel {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void creation() {
		MongoModel theModel;
		
		theModel = new MongoModel();
		
		/**
		 * Test insert/update/delete method 
		 */
		theModel.insertMongoDB("JUnitTest1", "MongoModel", "JUnitTest1@email.com", "123123", "Homeowner");
		theModel.insertMongoDB("JUnitTest2", "MongoModel", "JUnitTest2@email.com", "456456", "Independent");
		theModel.updateMongoDB("JUnitTest1", "MongoModel", "JUnitTest1@email.com", "111111", "Homeowner");
		theModel.updateMongoDB("JUnitTest2", "MongoModel", "JUnitTest2@email.com", "222222", "Independent");
		theModel.deleteMongoDB("JUnitTest1");
		theModel.deleteMongoDB("JUnitTest2");
	}
}
