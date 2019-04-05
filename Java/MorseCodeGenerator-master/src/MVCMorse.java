/**
 * Main Runner Class
 * @author Jupiter
 */
public class MVCMorse {
	public static void main(String [] args){
		MorseModel model = new MorseModel();
		MorseView view = new MorseView();
		MorseController controller = new MorseController(model, view);
		view.setVisible(true);
	}
	
}
