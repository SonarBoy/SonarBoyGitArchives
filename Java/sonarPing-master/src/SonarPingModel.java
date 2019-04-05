import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;

/**
 * This class deals with the sensor
 * @author Joshua, Keitha, Pyojoon
 * @version 2.0
 * @since 2016-01-30
 *
 */
public class SonarPingModel {
	SerialPort port;
	CommPortIdentifier basicPort;
	static BufferedReader input;
	static OutputStream output;
	public static boolean  disarmButtonPresses;
	public static String[] sensorList;

	/**
	 * This constructs a SonarPingModel
	 * @param comPortName port which the sensor is pluged in
	 */
	SonarPingModel(String comPortName){
		try{
			//Identifying the port
			this.basicPort = CommPortIdentifier.getPortIdentifier(comPortName);
			this.port = (SerialPort) this.basicPort.open("", 1);

			//Port Settings
			port.setSerialPortParams(9600, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1, 
					SerialPort.PARITY_NONE);

			//Input and Output Streams to write to the port
			output = port.getOutputStream();
			input = new BufferedReader(new InputStreamReader(this.port.getInputStream()));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * This is method deals with connection
	 * @param x
	 */
	//Switching between the added event listener
	public void toggle(int x){

		if(x == 1){
			this.createConnection();
		}else if(x == 0){
			this.breakConnection();
		}
	}

	/**
	 * This method adds the event listener
	 */
	private void createConnection(){
		try{
			this.port.addEventListener(new Serial());
			this.port.notifyOnDataAvailable(true);
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * This method removes the event listener
	 */
	private void breakConnection(){
		try{
			this.port.removeEventListener();
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * This class deals with the alarm, when the alarm happens, it will send a email 
	 * @author Joshua 
	 *
	 */
	public static class Serial implements SerialPortEventListener{

		public synchronized void serialEvent(SerialPortEvent event) {
			try{

				// On the Arrivial of a new Value
				// Create a new SonarPingEmailModel as a new Thread and run it 
				if(event.getNewValue()){


					try{
						String x = input.readLine();
						//Please Remove after testing
						if(!disarmButtonPresses){

							for(int runner = 0; runner < sensorList.length - 1;runner++){
								if(x.equals(sensorList[runner])){
									MongoModel model = new MongoModel();
									String emails = model.getAllEmails();
									//model.detached();
									model = null;

									SonarPingEmailModel shoutOutClass = new SonarPingEmailModel(emails, 
											"john.orion.ray@gmail.com", "john.orion.ray@gmail.com", "phantom1237");
									shoutOutClass.sessionInitialize();
									shoutOutClass.run();
								}
							}

//							if(x.equals("Sensor 0")){
//								
//
//							}
						}else{
							//Please Remove after testing
							disarmButtonPresses = false;
						}
					}



					catch(Exception ex){
						ex.printStackTrace();
					}

				}

			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
