import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.junit.Test;





/**
 * This is the driver class for the system
 * @author Joshua, Zhaoduan
 * @version 2.0
 * @since 2016-01-30
 */
public class StartingPoint 
{
	/**
	 * This is the main method for the driver class 
	 * @param args
	 */
	public static void main(String [] args)
	{
        final JFrame frame = new JFrame("");
        final JButton btnLogin = new JButton("");        

//        MyLoginDialog loginDlg = new MyLoginDialog(frame);
//        loginDlg.setVisible(true);
//        // if logon successfully
//        if(loginDlg.isSucceeded())
//        {
//        	 btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
//        }
//        else
//        {
//        	 btnLogin.setText("Sorry your user/password are not match!");
//        	 System.exit(0);
//        }
//		
        
        SonarPingModel model = new SonarPingModel("COM11");	
		SonarPingView view = new SonarPingView();
		SonarPingController controller = new SonarPingController(model, view,null);
			
	}
}
