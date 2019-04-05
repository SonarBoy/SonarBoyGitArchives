
public class ConsoleTester {
	public static void main(String[] args) {

		ArrayShiftModel x = new ArrayShiftModel(3);

		// int[][] seed = x.objectArray;

		System.out.println(x.printOutArray());
		System.out.println();

		// System.out.println("Shift Left");
		// System.out.println(x.printOutShifted(x.shiftLeft()));

		System.out.println("Shift Right 1");
		x.shiftRight();
		System.out.println(x.printOutShifted(x.shiftedArray));

		System.out.println("Shift Right 2");
		x.shiftRight();
		System.out.println(x.printOutShifted(x.shiftedArray));

		/*
		 * System.out.println("Right Two 1"); seed = x.shiftRightTwo(seed);
		 * System.out.println(x.printOutShifted(seed));
		 * 
		 * System.out.println("Right Two 2"); seed = x.shiftRightTwo(seed);
		 * System.out.println(x.printOutShifted(seed));
		 */
	}
}