import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * MorseView
 * + View class 
 * @author Jupiter
 */
public class MorseView extends JFrame{
	
	private JTextField enteredText;
	private JTextArea morseArea;
	JScrollPane scrollPane;
	JButton generateMorse;
	JButton soundOff;
	
	/**
	 * Constructor
	 */
	public MorseView (){
		
		this.enteredText = new JTextField();
		this.generateMorse = new JButton("Get Morse!");
		this.soundOff = new JButton("Play Sound!");
		this.morseArea = new JTextArea();
		this.morseArea.setEditable(false);
		this.scrollPane = new JScrollPane(this.morseArea);
		
		JPanel sidePanel = new JPanel(new GridLayout(4,1));
		sidePanel.add(this.enteredText);
		sidePanel.add(this.generateMorse);
		sidePanel.add(this.soundOff);
		
		JPanel contentPanel = new JPanel(new GridLayout(1,1));
		contentPanel.add(scrollPane);
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(sidePanel);
		mainPanel.add(contentPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 200);
		this.setResizable(false);
		this.add(mainPanel);
		this.setTitle("Morse Code Generator");
	}
	
	/**
	 * Get Plain Text
	 * @return Entered Text
	 */
	public String getPlainText(){
		return this.enteredText.getText();
	}
	
	/**
	 * Get morse area text
	 * @return Morse Area
	 */
	public String getMorseText(){
		return this.morseArea.getText();
	}
	
	
	/**
	 * Sets the message
	 * @param message
	 */
	public void setPlainText(String message){
		this.morseArea.setEditable(true);
		this.morseArea.setText(message);
		this.morseArea.setEditable(false);
	}
	
	/**
	 * Adding the morseListener
	 * @param listener
	 */
	public void addGenerateDataListener(ActionListener listener){
		this.generateMorse.addActionListener(listener);
	}
	
	/**
	 * Sound off data listener
	 * @param listener
	 */
	public void addSoundOffDataListener(ActionListener listener){
		this.soundOff.addActionListener(listener);
	}
	
}
