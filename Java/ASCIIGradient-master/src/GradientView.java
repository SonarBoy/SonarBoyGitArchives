import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GradientView extends JFrame  {
	
	private JLabel firstX;
	private JLabel firstY;
	private JLabel secondX;
	private JLabel secondY;
	private JLabel firstLine;
	private JLabel secondLine;
	
	
	private JTextField firstPointsX;
	private JTextField firstPointsY;
	private JTextField secondPointsX;
	private JTextField secondPointsY;
	
	private JButton generate;
	private JButton clear;
	private JTextArea outputArea;
	
	
	public GradientView(){
		
		
		this.firstX = new JLabel("Point 1 X");
		this.firstY = new JLabel("Point 1 Y");
		this.secondX = new JLabel("Point 2 X");
		this.secondY = new JLabel("Point 2 Y");
		this.firstLine = new JLabel();
		this.secondLine = new JLabel();
		
		this.firstPointsX = new JTextField();
		this.firstPointsY = new JTextField();
		this.secondPointsX = new JTextField();
		this.secondPointsY = new JTextField();
		
		this.generate = new JButton("Generate");
		this.clear = new JButton("Clear");
		this.outputArea = new JTextArea();	
		this.outputArea.setColumns(40);
		this.outputArea.setRows(52);
		this.outputArea.setFont(new Font("Serif", Font.BOLD, 15));
		
		JPanel inputPannel = new JPanel(new GridLayout(4,2));
		inputPannel.add(this.firstX);
		inputPannel.add(this.firstPointsX);
		inputPannel.add(this.firstY);
		inputPannel.add(this.firstPointsY);
		inputPannel.add(this.secondX);
		inputPannel.add(this.secondPointsX);
		inputPannel.add(this.secondY);
		inputPannel.add(this.secondPointsY);
		
		JScrollPane scrollPane = new JScrollPane(this.outputArea);
		
		JPanel buttonPannel = new JPanel(new GridLayout(4,1));
		buttonPannel.add(this.generate);
		buttonPannel.add(this.clear);
		
		JPanel infoPanel = new JPanel(new GridLayout(1,2));
		infoPanel.add(this.firstLine);
		infoPanel.add(this.secondLine);
		
		buttonPannel.add(infoPanel);
		
		JPanel topPanel = new JPanel(new GridLayout(3,1));
		topPanel.add(inputPannel);
		topPanel.add(buttonPannel);
		
		JPanel outputPanel = new JPanel(new GridLayout(1,1));
		outputPanel.add(scrollPane);
		
		JPanel superPannel = new JPanel(new GridLayout(1,2));
		superPannel.add(topPanel);
		superPannel.add(outputPanel);
		
		
		this.setSize(1500, 700);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Gradient Maker");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(superPannel);
		
	}
	
	public String getFirstX(){
		return this.firstPointsX.getText();
	}
	
	public String getFirstY(){
		return this.firstPointsY.getText();
	}
	
	public String getSecondX(){
		return this.secondPointsX.getText();
	}
	
	public String getSecondY(){
		return this.secondPointsY.getText();
	}
	
	public void clearField(){
		this.outputArea.setText(null);
		this.outputArea.setText("");
	}
	
	public void drawOnCanvas(String value){
		this.outputArea.setText(null);
		this.outputArea.setText(value);
	}
	
	public JButton getButton(){
		return generate;
	}
	
	public JButton getClearButton(){
		return clear;
	}
	
	public void clearNumberFields(){
		this.firstPointsX.setText("");
		this.firstPointsY.setText("");
		this.secondPointsX.setText("");
		this.secondPointsY.setText("");
	}
	
	public void setFirstLine(String value){
		this.firstLine.setText(value);
	}
	
	public void setSecondLine(String value){
		this.secondLine.setText(value);
	}
	
	public void addDrawingListener(ActionListener listener){
		this.generate.addActionListener(listener);
	}
	
	public void clearDrawingTable(ActionListener listener){
		this.clear.addActionListener(listener);
	}
}
