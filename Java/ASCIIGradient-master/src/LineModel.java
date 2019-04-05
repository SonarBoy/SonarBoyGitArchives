
public class LineModel {
	
	private double slope;
	private double bValue;
	
	public LineModel(double sl,double b){
		this.slope = sl;
		this.bValue = b;
	}
	
	public void setSlope(double s){
		this.slope = s;
	}
	
	public double getSlope(){
		return this.slope;
	}
	
	public void setBValue(double b){
		this.bValue = b;
	}
	
	public double getBValue(){
		return this.bValue;
	}
	
	public String toString(){
		return "Slope: " + this.slope + "\n" + "B Value: "+this.bValue;
	}
}
