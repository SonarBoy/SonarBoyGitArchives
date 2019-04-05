import java.util.Random;

/**ArrayShiftModel:
 * + Data object for Example 
 * + Used for Example Purposes
 * 
 * @author Jupiter
 */

public class ArrayShiftModel {

	int[][] objectArray;
	int[][] shiftedArray;
	Random generator;
	
	/**
	 * Constructor
	 * @param value Used to setup the size of the 2d Array.
	 */
	public ArrayShiftModel(int value) {

		objectArray = new int[value][value];
		shiftedArray = new int[value][value];
		generator = new Random();

		for (int runner = 0; runner < objectArray.length; runner++) {
			for (int runnerTwo = 0; runnerTwo < objectArray[runner].length; runnerTwo++) {
				objectArray[runner][runnerTwo] = generator.nextInt(10 - 0 + 1) + 0;
				shiftedArray[runner][runnerTwo] = objectArray[runner][runnerTwo];
			}
		}
	}

	/**
	 * String out
	 * @return String representation of the ArrayShiftModel
	 */
	public String printOutArray() {

		String objectArrayString = "";

		for (int runner = 0; runner < objectArray.length; runner++) {

			for (int runnerTwo = 0; runnerTwo < objectArray[runner].length; runnerTwo++) {

				if (objectArray[runner][runnerTwo] < 10) {
					objectArrayString += "0" + objectArray[runner][runnerTwo]
							+ " ";
				} else {
					objectArrayString += objectArray[runner][runnerTwo] + " ";
				}

			}

			objectArrayString += "\n";
		}

		return objectArrayString;
	}
	
	/**
	 * shiftLeft() tilts the array counter counter-clockwise
	 */
	public void shiftLeft() {

		int indexOne = objectArray.length - 1;
		int indexTwo = 0;

		int[][] returnerArray = new int[indexOne + 1][indexOne + 1];

		for (int runner = 0; runner < objectArray.length; runner++) {
			for (int runnerTwo = 0; runnerTwo < objectArray[runner].length; runnerTwo++) {
				// shiftedArray[indexOne][indexTwo] =
				// objectArray[runner][runnerTwo];
				// System.out.print(objectArray[indexOne][indexTwo] + " ");

				returnerArray[runner][runnerTwo] = objectArray[indexTwo][indexOne];

				indexTwo++;
			}

			indexOne--;
			indexTwo = 0;
		}

		this.shiftedArray = returnerArray;
		this.objectArray = returnerArray;
	}
	
	/**
	 * shiftRight() tilts the array clockwise
	 */
	public void shiftRight() {

		int indexOne = 0;
		int indexTwo = objectArray.length - 1;

		int[][] returnerArray = new int[indexTwo + 1][indexTwo + 1];

		for (int runner = 0; runner < objectArray.length; runner++) {
			for (int runnerTwo = 0; runnerTwo < objectArray[runner].length; runnerTwo++) {
				// shiftedArray[indexOne][indexTwo] =
				// objectArray[runner][runnerTwo];
				// System.out.print(objectArray[indexOne][indexTwo] + " ");

				returnerArray[indexOne][indexTwo] = objectArray[runner][runnerTwo];

				indexOne++;
			}

			indexTwo--;
			indexOne = 0;
		}

		this.shiftedArray = returnerArray;
		this.objectArray = returnerArray;
	}
	
	/**
	 * printOutShifted
	 * @param inputArray input array for conversion
	 * @return string of input array
	 */
	public String printOutShifted(int[][] inputArray) {

		String objectArrayString = "";

		for (int runner = 0; runner < inputArray.length; runner++) {

			for (int runnerTwo = 0; runnerTwo < inputArray[runner].length; runnerTwo++) {

				if (inputArray[runner][runnerTwo] < 10) {
					objectArrayString += "0" + inputArray[runner][runnerTwo]
							+ " ";
				} else {
					objectArrayString += inputArray[runner][runnerTwo] + " ";
				}

			}

			objectArrayString += "\n";
		}

		return objectArrayString;

	}
}