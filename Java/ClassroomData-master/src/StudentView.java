import java.awt.GridLayout;
import java.awt.event.ActionListener;
//import org.apache.log4j.Logger;
import javax.swing.*;


/**
 * Student View
 * @author Jupiter
 *
 */
public class StudentView extends JFrame {

	JButton retriveData;
	JTextArea retrivedPannel;
	private JTextField selectionField;
	private JScrollPane selectionFieldScrollPannel;
	private JPanel selectionPanel;
	private JPanel mainPanel;
	//Logger log;
	
	/**
	 * Constructor
	 */
	public StudentView(){
		
		super("Simple Student Getter");
		
		//Main Panel
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new GridLayout(1, 2, 2, 2));
		//Main Panel
		
		//Retrived Area
		this.retrivedPannel = new JTextArea();
		this.selectionFieldScrollPannel = new JScrollPane(this.retrivedPannel);
		//Retrived Area
		
		//Data Panel
		this.selectionPanel = new JPanel();
		this.selectionPanel.setLayout(new GridLayout(3,1,2,2));
		
		this.retriveData = new JButton("Retrived Data!");
		this.selectionField = new JTextField();
		
		this.selectionPanel.add(this.selectionField);
		this.selectionPanel.add(this.retriveData);
		//Data Panel
		
		//this.log = Logger.getLogger(StudentView.class);
		//log.debug("View Created");
		
		mainPanel.add(selectionPanel);
		mainPanel.add(selectionFieldScrollPannel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(700, 200);
		this.setResizable(false);
		this.add(mainPanel);
	}
	
	/**
	 * Main Listener
	 * @param listener
	 */
	public void addRetrivalListener(ActionListener listener){
		this.retriveData.addActionListener(listener);
	}
	
	/**
	 * Display Retrived Data
	 * @param insertedData
	 */
	public void DisplayData(String insertedData){
		this.retrivedPannel.setText(insertedData);
	}
	
	/**
	 * Button
	 * @return Main Data
	 */
	public JButton returnButton(){
		return this.retriveData;
	}
	
	
}
