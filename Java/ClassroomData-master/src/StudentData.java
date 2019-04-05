/**
 * Student Data Object
 * @author Jupiter
 */
public class StudentData {
	
	private String firstName;
	private String lastName;
	private int [] studentTestData;
	private double average;
	
	public StudentData(){
		
	}
	
	public StudentData(String first,String last, int[] data){
		this.firstName = first;
		this.lastName = last;
		this.studentTestData = data;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public int[] getStudentTestData(){
		return this.studentTestData;
	}
	
	public void setFirstName(String value){
		this.firstName = value;
	}
	
	public void setLastName(String value){
		this.lastName = value;
	}
	
	public void setStudentTestData(int [] value){
		this.studentTestData = value;
	}
	
	public double getAverage(){
		return this.average;
	}

	
	public void calculateAverage(int[] data){
		
		int summed = 0;
		int numberOfTests = data.length - 1;
		
		for(int runner = 0, basicValue = data.length - 1; runner < basicValue;runner++){
			summed += data[runner];
		}

		 this.average = (double)(summed/numberOfTests);
	}
	
	
	

}
