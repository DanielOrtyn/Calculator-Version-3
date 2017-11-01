package math;

/**
 * A class of to handle common mathematics. Only contains static methods.
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 29, 2017
 *
 */
public class BasicMath {

	/**
	 * An array of all numerical digits digits
	 */
	private static final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * The open parenthesis character
	 */
	private static final char openParen = '(';

	/**
	 * The close parenthesis character
	 */
	private static final char closeParen = ')';

	/**
	 * Check if the passed char is a digit or not
	 * 
	 * @param characterToTest
	 * @return true if a digit and false otherwise
	 */
	public static boolean isDigit(char characterToTest) {
		for (int i = 0; i < digits.length; i++) {
			if (characterToTest == digits[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the passed char is an operation or not
	 * 
	 * @param characterToTest
	 * @return true if an operation and false otherwise
	 */
	public static boolean isOperation(char characterToTest) {
		if (BasicMath.getAdditionOperation() == characterToTest) {
			return true;
		}
		if (BasicMath.getSubtractOperation() == characterToTest) {
			return true;
		}
		if (BasicMath.getMultiplyOperation() == characterToTest) {
			return true;
		}
		if (BasicMath.getDivideOperation() == characterToTest) {
			return true;
		}
		if (BasicMath.getExponentOperation() == characterToTest) {
			return true;
		}
		return false;
	}

	/**
	 * Tests if the passed character is a parenthesis.
	 * 
	 * @param charToTest
	 *            the character to test
	 * @return true if the character is a parenthesis, false otherwise
	 */
	public static boolean isParen(char charToTest) {
		if (charToTest == getOpenParen() || charToTest == getCloseParen()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the open parenthesis character
	 * 
	 * @return open parenthesis character
	 */
	public static char getOpenParen() {
		return openParen;
	}

	/**
	 * Gets the close parenthesis character
	 * 
	 * @return close parenthesis character
	 */
	public static char getCloseParen() {
		return closeParen;
	}

	/**
	 * Gets the multiply character
	 * 
	 * @return multiply character
	 */
	public static char getMultiplyOperation() {
		return '*';
	}

	/**
	 * Gets the divide character
	 * 
	 * @return divide character
	 */
	public static char getDivideOperation() {
		return '/';
	}

	/**
	 * Gets the subtract character
	 * 
	 * @return subtract character
	 */
	public static char getSubtractOperation() {
		return '-';
	}

	/**
	 * Gets the addition character
	 * 
	 * @return addition character
	 */
	public static char getAdditionOperation() {
		return '+';
	}

	public static char getExponentOperation() {
		return '^';
	}

	/**
	 * Checks if the passed string is a number or not
	 * 
	 * @param stringToTest
	 *            the string to test
	 * @return true if a number false otherwise
	 */
	public static boolean isNumber(String stringToTest) {
		// test for an int
		if (BasicMath.isInteger(stringToTest) != null) {
			return true;
		}
		// test for a double
		else if (BasicMath.isDouble(stringToTest) != null) {
			return true;
		}
		return false;
	}

	/**
	 * This method tests a string to see if it is an integer and returns the
	 * integer if the string is an integer and returns null if the string is not
	 * an integer
	 * 
	 * @param stringToTest
	 *            the string being checked for being an integer
	 * @return the integer if the string is one and null if the string is not
	 */
	public static Integer isInteger(String stringToTest) {
		if (stringToTest == null) {
			return null;
		}
		if (stringToTest.length() == 0) {
			return null;
		}
		int startIndex = 0;
		if (stringToTest.charAt(0) == '-') {
			// move start index past negative
			startIndex++;
		}
		for (int i = startIndex; i < stringToTest.length(); i++) {
			char currentChar = stringToTest.charAt(i);
			if (isDigit(currentChar) == false) {
				// one character is not a digit so this String is not an integer
				return null;
			}
		}
		// check if integer is to big for the int primative
		if (stringToTest.length() > 10) {
			// string has too many digits return null
			return null;
		}
		// check if the number is to big for an integer
		long integerLong = Long.parseLong(stringToTest);
		if (integerLong > (2147483648l - 1) || integerLong < -2147483648l) {
			return null;
		}
		// parse long into integer
		int integer = (int) integerLong;
		// the string has been fully tested and passed return true
		return integer;
	}

	/**
	 * This method tests a string to see if it is a double and returns the
	 * double if the string is a double and returns null if the string is not a
	 * double
	 * 
	 * @param stringToTest
	 *            the string being checked for being a double
	 * @return the double if the string is one and null if the string is not
	 */
	public static Double isDouble(String stringToTest) {
		if (stringToTest == null) {
			return null;
		}
		if (stringToTest.length() == 0) {
			return null;
		}
		boolean hasDecimalPoint = false;
		int startIndex = 0;
		if (stringToTest.charAt(0) == '-') {
			// move start index past negative
			startIndex++;
		}
		for (int i = startIndex; i < stringToTest.length(); i++) {
			char currentChar = stringToTest.charAt(i);
			if (isDigit(currentChar) == false) {
				// character is not a digit check if it is the decimal point
				if (currentChar == '.') {
					if (hasDecimalPoint) {
						// already has a decimal point return true
						return null;
					}
					// set decimal point boolean, decimal points are valid, once
					hasDecimalPoint = true;
				} // check for exponential form
				else if (currentChar == 'e' || currentChar == 'E') {
					// it has an exponent check if the string after is an
					// integer
					if (i + 1 < stringToTest.length()) {
						if (BasicMath.isInteger(stringToTest.substring(i + 1)) == null) {
							return null;
						} else {
							// end loop and return the double
							i = stringToTest.length();
						}
					} else {
						return null;
					}
				}

				else {
					// one character is not a digit, decimal or exponent so this
					// String is not an double
					return null;
				}
			}
		}
		// the string has been fully tested and passed return the double
		return Double.parseDouble(stringToTest);
	}

}
