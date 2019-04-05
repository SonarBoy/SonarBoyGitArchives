import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import gnu.io.*;


/**
 * Controller Class
 * 
 * @author Jupiter
 */
public class MorseController {
	
	private MorseModel model;
	private MorseView view;
	private SerialPort port;
	private CommPortIdentifier basicPort;
	private OutputStream output;
	private BufferedReader input;
	
	/**
	 * Constructor
	 * 
	 * @param morseModel
	 * @param morseView
	 */
	public MorseController(MorseModel m,MorseView v){
		
		this.model = m;
		this.view = v;
		
		view.addGenerateDataListener(new Generator());
		view.addSoundOffDataListener(new Generator());
		
		try {
			
			basicPort = CommPortIdentifier.getPortIdentifier("COM4");
			port = (SerialPort) basicPort.open("", 1);
			
			port.setSerialPortParams(9600,
					 SerialPort.DATABITS_8,
					 SerialPort.STOPBITS_1,
					 SerialPort.PARITY_NONE);
			
			output = port.getOutputStream();
			input = new BufferedReader(new InputStreamReader(port.getInputStream()));
			port.addEventListener(new Serial());
			port.notifyOnDataAvailable(true);
		} catch (NullPointerException e) {

			e.printStackTrace();
		} catch (PortInUseException e){
			
			e.printStackTrace();
		} catch(NoSuchPortException e){
			
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Generator
	 * Inner class for the SerialPort Listener
	 * 
	 * @author Jupiter
	 */
	
	public class Generator implements ActionListener,SerialPortEventListener{
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == view.generateMorse){
				
				try{
					String messageBack = model.getMorseData(view.getPlainText());
					view.setPlainText(messageBack);
				}
				
				catch(Exception ex){
					ex.printStackTrace();
				}
			}else if(event.getSource() == view.soundOff){
				
				try{
					String messageBack = view.getMorseText();
					output.write(messageBack.getBytes());
				}
				
				catch(Exception ex){
					
				}
			}
			
		}

		@Override
		public void serialEvent(SerialPortEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * SerialClass 
	 * Gets the Serial port data
	 * 
	 * @author Jupiter
	 */
	public class Serial implements SerialPortEventListener{

		
		public void serialEvent(SerialPortEvent event){
			try{
				if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
					JOptionPane.showMessageDialog(null, input.readLine());
					
				}
			}
			
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			
		}
		
	}
	
	
}
