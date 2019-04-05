import org.junit.Test;
//import static org.junit.Assert.assertEquals;

/**
 * This is the class which puts View, Model and Control Class together
 * @author Zhaoduan, Akanimoh, Keitha
 * @version 2.0
 * @since 2016-03-10
 */
public class MongoMVC {
	static MongoView theView;
	static MongoModel theModel;
	static MongoControl theControl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		theView = new MongoView();
		theModel = new MongoModel();
		theControl = new MongoControl(theView, theModel);
		
		theView.setVisible(true);
	}
}
