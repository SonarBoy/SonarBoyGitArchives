
public class MVCClassroom {
	
	public static void main(String [] args){
		StudentModel students = new StudentModel("./data/classroomData.properties");
		StudentView classroomView = new StudentView();
		ClassroomController controller = new ClassroomController(students, classroomView);
	}
	
}
