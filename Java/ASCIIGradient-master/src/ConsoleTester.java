import java.util.Random;
import java.util.Scanner;


public class ConsoleTester {
	public static void main(String [] args){
		
		GradientModel basic = new GradientModel(51, 51);
		GradientView view = new GradientView();
		GradientController controller = new GradientController(basic, view);
		view.setVisible(true);
		
	}
}
