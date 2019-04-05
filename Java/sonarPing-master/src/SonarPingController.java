import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;

/**
 * This class implements SonarPingController which links the Model, EmailModel, and View
 * @author Joshua, Keitha, Pyojoon
 * @version 2.0
 * @since 2016-01-30
 */
public class SonarPingController {
	SonarPingModel sonarPingModel;
	SonarPingEmailModel sonarPingEmailModel;
	SonarPingView sonarPingView;
	int toggle;
	
	/**
	 * This constructs SonarPingController with specified view, emailmodel, and view
	 * @param model mode which deals with the sensor
	 * @param view  view which deals with the GUI
	 * @param emailModel deals with the email
	 */
	SonarPingController(SonarPingModel model, SonarPingView view, SonarPingEmailModel emailModel){
		this.sonarPingModel = model;
		this.sonarPingView = view;
		this.sonarPingEmailModel = emailModel;
		
		toggle = 0;
		sonarPingView.armListener(new SystemArmingListener());
		sonarPingView.disarmListener(new SystemArmingListener());
		
		this.sonarPingModel.toggle(0);
	}
	
	/**
	 * This class deals with the user activities like click
	 * @author Joshua 
	 * @version 2.0
	 * @since 2016-01-30
	 */
	public class SystemArmingListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == sonarPingView.ArmJButton){

				sonarPingModel.toggle(1);
				sonarPingView.colorPanel.setBackground(Color.RED);
				sonarPingView.ArmJButton.setEnabled(false);
				sonarPingView.DisarmJButton.setEnabled(true);
				sonarPingModel.disarmButtonPresses = false;
				sonarPingModel.sensorList = sonarPingView.sensorList;
				
			}else if(arg0.getSource() == sonarPingView.DisarmJButton){
				
				sonarPingView.colorPanel.setBackground(Color.GREEN);
				sonarPingModel.toggle(0);
				sonarPingView.ArmJButton.setEnabled(true);
				sonarPingView.DisarmJButton.setEnabled(false);
				sonarPingModel.disarmButtonPresses = true;
				sonarPingModel.sensorList = sonarPingView.sensorList;
			}
		}
	}
}
