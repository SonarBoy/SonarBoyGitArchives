import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class GradientController {

	private GradientModel gradient;
	private GradientView canvasView;
	
	public GradientController(GradientModel model, GradientView view){
		this.gradient = model;
		this.canvasView = view;
		
		this.canvasView.addDrawingListener(new BasicHandler());
		this.canvasView.clearDrawingTable(new BasicHandler());
	}
	
	public class BasicHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource() == canvasView.getButton()){
				
				if(canvasView.getFirstX() == null || canvasView.getFirstY() == null ||
						canvasView.getSecondX() == null || canvasView.getSecondY() == null){
					JOptionPane.showMessageDialog(null, "All Points are not set");
				}else{
					
					try{
						gradient.initialize(51, 51);
						double x1 = Double.parseDouble(canvasView.getFirstX());
						double y1 = Double.parseDouble(canvasView.getFirstY());
						double x2 = Double.parseDouble(canvasView.getSecondX());
						double y2 = Double.parseDouble(canvasView.getSecondY());
						
						gradient.drawPointsAt(x1, y1, x2, y2);
						
						String[] outputLines = gradient.lines();
						
						canvasView.setFirstLine("<html>First Line: " +"<br>"+ outputLines[0]+"</html>");
						canvasView.setSecondLine("<html>Second Line: " +"<br>"+ outputLines[1]+"</html>");
						
						canvasView.clearField();
						canvasView.drawOnCanvas("");
						canvasView.drawOnCanvas(gradient.showCanvas());
					}
					
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex.toString());
					}
					
				}
				
			}else if(arg0.getSource() == canvasView.getClearButton()){
				canvasView.clearField();
				canvasView.setFirstLine("");
				canvasView.setSecondLine("");
				canvasView.clearNumberFields();
			}
			
		}
		
	}
	
	
	
}
