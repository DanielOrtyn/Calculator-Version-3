package math;

/**
 * A wrapper class for mathematical terms(numbers, operations and parenthesis).
 * Which holds them as strings and can provide information about the terms. For
 * any number contained in the class they can only be returned as Double
 * objects.
 * 
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 29, 2017
 *
 */
public class MathematicalTerm {

	/**
	 * The mathematical term contained in this object
	 */
	private String term;
	/**
	 * The type of term contained
	 */
	private TermType type;

	/**
	 * The type of term
	 *
	 */
	public enum TermType {
		NUMBER, OPERATION, PARENTHESIS
	}

	/**
	 * Creates the term from the passed string. If the string is not a valid
	 * term an illegal argument exception is thrown.
	 * 
	 * @param stringTerm
	 *            the string representation of the term
	 * @throws IllegalArgumentException
	 *             throws if the passed string is not a valid term
	 */
	public MathematicalTerm(String stringTerm) {
		if (stringTerm.length() == 1) {
			char charTerm = stringTerm.charAt(0);
			if (BasicMath.isOperation(charTerm)) {
				term = stringTerm;
				type = TermType.OPERATION;
				return;
			} else if (BasicMath.isDigit(charTerm)) {
				term = stringTerm;
				type = TermType.NUMBER;
				return;
			} else if (BasicMath.getOpenParen() == charTerm || BasicMath.getCloseParen() == charTerm) {
				term = stringTerm;
				type = TermType.PARENTHESIS;
				return;
			}
//		} else if (BasicMath.isInteger(stringTerm) != null) {
//			term = stringTerm;
//			type = TermType.NUMBER;
//			return;
		} else if (BasicMath.isDouble(stringTerm) != null) {
			term = stringTerm;
			type = TermType.NUMBER;
			return;
		}
		throw new IllegalArgumentException("Passed term is not implemented or is not a term");
	}

	/**
	 * Returns the string representation of the term
	 */
	public String toString() {
		return this.term;
	}

	/**
	 * Gets the type of the term
	 * 
	 * @return returns the type of the term
	 */
	public TermType getType() {
		return this.type;
	}

	/**
	 * Tests if the term is an operation. Returns null if not an operation and
	 * the operation character if it is.
	 * 
	 * @return Returns null if not an operation and the operation character if
	 *         it is.
	 */
	public Character isOperation() {
		if (this.getType() == TermType.OPERATION) {
			return new Character(this.term.charAt(0));
		}
		return null;
	}

	/**
	 * Test if term is an addition operation
	 * 
	 * @return true if an addition operation and false otherwise
	 */
	public boolean isAddition() {
		if (this.term.charAt(0) == BasicMath.getAdditionOperation()) {
			return true;
		}
		return false;
	}

	/**
	 * Test if term is a subtraction operation
	 * 
	 * @return true if an subtraction operation and false otherwise
	 */
	public boolean isSubtraction() {
		if (this.term.charAt(0) == BasicMath.getSubtractOperation()) {
			return true;
		}
		return false;
	}

	/**
	 * Test if term is a multiplication operation
	 * 
	 * @return true if an multiplication operation and false otherwise
	 */
	public boolean isMultiplication() {
		if (this.term.charAt(0) == BasicMath.getMultiplyOperation()) {
			return true;
		}
		return false;
	}

	/**
	 * Test if term is an division operation
	 * 
	 * @return true if an division operation and false otherwise
	 */
	public boolean isDivision() {
		if (this.term.charAt(0) == BasicMath.getDivideOperation()) {
			return true;
		}
		return false;
	}

	/**
	 * Test if term is an exponent operation
	 * 
	 * @return true if an exponent operation and false otherwise
	 */
	public boolean isExponent() {
		if (this.term.charAt(0) == BasicMath.getDivideOperation()) {
			return true;
		}
		return false;
	}

	/**
	 * Tests if the term is a parenthesis. Returns null if not a parenthesis and
	 * the parenthesis character if it is.
	 * 
	 * @return Returns null if not a parenthesis and the parenthesis character
	 *         if it is.
	 */
	public Character isParenthesis() {
		if (this.getType() == TermType.PARENTHESIS) {
			return new Character(this.term.charAt(0));
		}
		return null;
	}

	/**
	 * Returns the Double represented by the term if the term is a number and
	 * null otherwise.
	 * 
	 * @return Returns the Double represented by the term if the term is a
	 *         number and null otherwise.
	 */
	public Double isNumber() {
		return BasicMath.isDouble(this.term);
	}

	/**
	 * Takes two doubles and an operation character and performs that
	 * mathematical operation and returns the result.
	 * 
	 * @param left
	 *            the number on the left of the operation
	 * @param operation
	 *            the operation character
	 * @param right
	 *            the number on the right of the operation
	 * @return the result of the operation
	 */
	public static double performOperation(double left, char operation, double right) {
		if (operation == BasicMath.getAdditionOperation()) {
			return left + right;
		}
		if (operation == BasicMath.getSubtractOperation()) {
			return left - right;
		}
		if (operation == BasicMath.getMultiplyOperation()) {
			return left * right;
		}
		if (operation == BasicMath.getDivideOperation()) {
			return left / right;
		}
		throw new IllegalArgumentException("Operation passed not implemented");
	}
}
