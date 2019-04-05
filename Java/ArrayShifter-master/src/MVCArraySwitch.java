
/**
 * Main Starter class
 * @author Jupiter
 */
public class MVCArraySwitch {
	//Test Commit
	public static void main(String[] args) {
		ArrayShiftModel model = new ArrayShiftModel(8);
		ArrayShiftView view = new ArrayShiftView();

		@SuppressWarnings("unused")
		ArrayShiftController control = new ArrayShiftController(model, view);

		view.setVisible(true);
	}
}
