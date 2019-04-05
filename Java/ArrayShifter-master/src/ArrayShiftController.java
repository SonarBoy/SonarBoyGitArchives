import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import org.apache.log4j.Logger;

/**
 * Array Shift Controller 
 * + connection between the model and view
 * @author Jupiter
 */
public class ArrayShiftController {

	private ArrayShiftModel model;
	private ArrayShiftView view;
	//Logger log;

	/**
	 * Constructor
	 * @param inputModel model
	 * @param inputView view
	 */
	public ArrayShiftController(ArrayShiftModel inputModel,
			ArrayShiftView inputView) {
		this.model = inputModel;
		this.view = inputView;
	//	log = Logger.getLogger(ArrayShiftController.class);

		view.addLeftSwitchListener(new DirectionListener());
		view.addRightSwitchListener(new DirectionListener());

		this.initialize();
	}
	
	/**
	 * Initializer for the view
	 */
	public void initialize() {
		view.display(model.printOutShifted(model.objectArray));
	}
	
	/**
	 * Direction listener for the buttons
	 * @author Jupiter
	 */
	public class DirectionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == view.leftButton) {

				model.shiftLeft();
				view.display(model.printOutArray());
		//		log.debug("Left Button Clicked");

			} else if (e.getSource() == view.rightButton) {

				model.shiftRight();
				view.display(model.printOutArray());
		//		log.debug("Right Button Clicked");

			}

		}

	}
}
