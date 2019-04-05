import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;

/**
 * This class tests the connection of Sensor
 * @author Joshua 
 * @version 2.0
 * @since 2016-02-18
 */
public class EntryPointFile {
/**
 * This is the main method for the EntryPointFile class
 * @param args
 */
	public static void main(String [] args){
		
		System.out.println("Testing With RXTX");


		try{

			SonarPingModel spm = new SonarPingModel("COM3");
			Scanner in = new Scanner(System.in);
			while(true){
				System.out.print("Enter 1 or 0:");
				String x = in.nextLine();
				spm.toggle(Integer.parseInt(x));
				
			}
		}

		catch(Exception ex){
			
			ex.printStackTrace();
		}



	}

}


