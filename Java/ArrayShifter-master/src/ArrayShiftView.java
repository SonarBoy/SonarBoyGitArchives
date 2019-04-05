import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * ArrayShiftView:
 * + Creation of the view to hold the object
 * @author Jupiter
 */
public class ArrayShiftView extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton leftButton = new JButton("Left Button");
	JButton rightButton = new JButton("Right Button");
	JTextArea commentTextArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(commentTextArea);

	public ArrayShiftView() {

		super("Array Shifter");
		JPanel mainPannel = new JPanel();
		mainPannel.setLayout(new GridLayout(1, 3, 1, 1));

		JPanel leftPannel = new JPanel();
		leftPannel.setLayout(new GridLayout(3, 1));
		leftPannel.add(leftButton);
		
		JPanel rightPannel = new JPanel();
		rightPannel.setLayout(new GridLayout(3, 1));
		rightPannel.add(rightButton);

		mainPannel.add(leftPannel);
		mainPannel.add(scrollPane);
		mainPannel.add(rightPannel);
		
		this.commentTextArea.setEditable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setSize(500, 200);
		this.setResizable(false);
		this.add(mainPannel);

	}
	
	/**
	 * Attach the Left Button to the correct listener
	 * @param listener
	 */
	void addLeftSwitchListener(ActionListener listener) {
		leftButton.addActionListener(listener);
	}
	
	/**
	 * Attach the Left Button to the correct listener
	 * @param listener
	 */
	void addRightSwitchListener(ActionListener listener) {
		rightButton.addActionListener(listener);
	}

	/**
	 * Display the input data
	 * @param data inputData
	 */
	void display(String data) {
		this.commentTextArea.setEditable(true);
		this.commentTextArea.setText(data);
		this.commentTextArea.setEditable(false);
	}

}
