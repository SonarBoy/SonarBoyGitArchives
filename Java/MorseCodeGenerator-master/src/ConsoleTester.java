
public class ConsoleTester {
	public static void main(String [] args){
		System.out.println("Enter");
		
		MorseModel morse = new MorseModel();
		
		String[][] x = morse.morseSheet();
		
		for(int runner = 0; runner <= 25; runner++){
			System.out.println("Alpha "+x[runner][0]);
			System.out.println("Morse "+x[runner][1]);
			
		}
		
		System.out.println("Message ABC" + morse.getMorseData("Joshua is the best"));
		MorseView basic = new MorseView();
		basic.setVisible(true);
	}
}
