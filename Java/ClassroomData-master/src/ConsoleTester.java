import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;

public class ConsoleTester {

	public static void main(String[] args) {
		
		//Properties prop = new Properties();
		
		try{
			
			//prop.load(new FileInputStream("./data/classroomData.properties"));
			//System.out.println("First Name: " + prop.getProperty("Student1-firstName"));
			//System.out.println("Test: " + prop.getProperty("Student1-test1"));
			
			StudentData tester = new StudentData();
			
			StudentModel x = new StudentModel("./data/classroomData.properties");
			x.setStudentData();
			System.out.println(x.getStudentData()[0].getFirstName());
			System.out.println(x.getStudentData()[0].getLastName());
			System.out.println(x.getStudentData()[0].getStudentTestData()[0]);
			System.out.println(x.getStudentData()[0].getStudentTestData()[1]);
			System.out.println(x.getStudentData()[0].getStudentTestData()[2]);
			System.out.println(x.getStudentData()[0].getStudentTestData()[3]);
			System.out.println(x.getStudentData()[0].getAverage()+ "");
			
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			
		}

	}

}
