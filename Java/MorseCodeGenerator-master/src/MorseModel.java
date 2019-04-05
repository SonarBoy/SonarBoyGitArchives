import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * MorseCode
 * 
 * @author Jupiter
 */
public class MorseModel {
	
	 private String [][] morseSheet;
	 private Properties prop;
	 
	 
	 /**
	  * Constructor
	  */
	 public MorseModel(){
		 initialize();
	 }
	 
	 
	 /**
	  * Main Initializer
	  * Initializers for all the variables
	  * 
	  * + Morse Sheet 
	  * + Properties
	  */
	 public void initialize(){
			
		 try{
			 morseSheet = new String[26][2];
			 prop = new Properties();
			 prop.load(new FileInputStream("src/morse.properties"));
			 
			 for(int runner = 0; runner <= 25; runner++){
				 morseSheet[runner][0] = prop.getProperty("alpha-"+runner);
				 morseSheet[runner][1] = prop.getProperty("morse-"+runner);
			 }
		 }
		 
		 catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex.getMessage());
		 }
	 }
	 
	 /**
	  * 
	  * @return theMorseSheet
	  */
	 public String[][] morseSheet(){
		 return this.morseSheet;
	 }
	
	 /**
	  * Get the Morse Data
	  * 
	  * @param message
	  * @return the morseMessage
	  */
	 public String getMorseData(String message){
		 
		 String morseMessage = "";
		 int leng = message.length();
		 String charAt = "";
		 
		 try{
			 for(int runner = 0; runner <= leng - 1; runner++){
				 
				 charAt = ""+message.toLowerCase().charAt(runner);
				 
				 for(int runnerTwo = 0; runnerTwo <= morseSheet.length - 1;runnerTwo++){
					 
					 if(charAt.equals(" ")){
						 break;
					 }
					 
					 if(charAt.equals(morseSheet[runnerTwo][0])){
						 morseMessage += morseSheet[runnerTwo][1] + " ";
					 }
				 }
			 }
		 }
		 
		 catch(Exception ex){
			 JOptionPane.showMessageDialog(null, ex.getMessage());
		 }
		 return morseMessage;
	 }
	 
	 
}
