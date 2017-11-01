package math;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A class to hold a simple mathematical equation in the form of a string. It
 * can take an equation in string form and evaluate it for a number. Currently
 * only parenthesis, and the four basic operations are supported.
 * 
 * @author Daniel Ortyn
 * @version 1.1
 * @since Oct 29, 2017
 *
 *
 */
public class Equation {

	/**
	 * The equation to work with for this object
	 */
	private LinkedList<MathematicalTerm> equation;

	/**
	 * Produces and equation set to the value 0.
	 */
	public Equation() {
		LinkedList<MathematicalTerm> newEquationList = new LinkedList<MathematicalTerm>();
		newEquationList.add(new MathematicalTerm("0"));
		this.equation = newEquationList;
	}

	/**
	 * Parses a mathematical equation from the passed string
	 * 
	 * @param newEquation
	 *            the string containing the new equation
	 */
	public Equation(String newEquation) {
		this.processStringToEquation(newEquation);
	}

	/**
	 * Creates an equation from a list of mathematical terms
	 * 
	 * @param newEquation
	 *            the list of mathematical terms
	 */
	public Equation(LinkedList<MathematicalTerm> equationList) {
		this.equation = equationList;
		// equation parsed now process it for implicit items
		this.processEquationForImplicitItems();
	}

	/**
	 * Process the passed string into a list of MathematicalTerms and sets the
	 * equation list of this object to the newly created list.
	 * 
	 * @param newEquation
	 *            the string representation of the new equation
	 * 
	 * @deprecated an invalid last term could cause problems
	 */
	private void processStringToEquation(String newEquation) {
		try {
			LinkedList<MathematicalTerm> newEquationList = new LinkedList<MathematicalTerm>();
			// test if the string actually has anything in it
			if (newEquation.isEmpty()) {
				newEquationList.add(new MathematicalTerm("0"));
				this.equation = newEquationList;
				return;
			}
			// the index for the start of the term to be created, inclusive
			int startOfTerm = 0;
			// the index for the end of the term to be created, inclusive
			int endOfTerm = 0;
			boolean termFound = false;
			for (int i = 0; i < newEquation.length(); i++) {
				char currentChar = newEquation.charAt(i);
				// test if the current char is a term itself
				if (BasicMath.isOperation(currentChar) || BasicMath.isParen(currentChar)) {
					// test if there is a term currently being processed other
					// then
					// the current char
					if (startOfTerm == i) {
						// only the current character needs to be considered
						// set term found and the endOf the term to this char
						termFound = true;
						endOfTerm = i;
					} else {
						// the current term is larger then the current char
						/*
						 * currently the only option is that the current term is
						 * a number and the current char is an operation or
						 * parenthesis after that number
						 */
						// set term found
						termFound = true;
						/*
						 * set end of term to the char before as the current
						 * char is not part of the number
						 */
						endOfTerm = i - 1;
						/*
						 * decrement the index so that the next iteration of the
						 * for loop will point to this character again so that
						 * it can be analyzed as the current iterations has been
						 * used for the number preceding the current char.
						 */
						i--;
					}
				}
				if (termFound) {
					String termString = newEquation.substring(startOfTerm, endOfTerm + 1);
					newEquationList.add(new MathematicalTerm(termString));
					startOfTerm = i + 1;
					endOfTerm = i + 1;
					termFound = false;
				}
			}
			// if the last term was a number it will not have been added so deal
			// with that
			// test if there is anything remaining in the string
			if (startOfTerm <= newEquation.length()) {
				// anything left must be a number so treat it that way
				String termString = newEquation.substring(endOfTerm, newEquation.length());
				newEquationList.add(new MathematicalTerm(termString));
			}
			this.equation = newEquationList;
			// equation parsed now process it for implicit items
			this.processEquationForImplicitItems();
		} catch (IllegalArgumentException e) {
			// equation was invalid make it default to 0
			LinkedList<MathematicalTerm> newEquationList = new LinkedList<MathematicalTerm>();
			newEquationList.add(new MathematicalTerm("+"));
			this.equation = newEquationList;
		}
	}

	/**
	 * Processes the equation of this for
	 */
	private void processEquationForImplicitItems() {
		// process equation for negative numbers
		processEquationForIntialSubtraction();
		processEquationForImplicitMultiplication();
		processEquationForSubtractions();
	}

	/**
	 * Check if the first part of the equation is subtraction and alter the
	 * equation to make that valid
	 */
	private void processEquationForIntialSubtraction() {
		// ensure equation has contents
		if (this.equation.isEmpty()) {
			return;
		}
		// check if the first term is subtraction
		if (this.equation.get(0).isSubtraction()) {
			// replace the subtraction with multiplication by negative 1
			equation.set(0, new MathematicalTerm("*"));
			equation.add(0, new MathematicalTerm("-1"));
		}
	}

	/**
	 * Processes the equation of this for back to back parentheses ")(", which
	 * imply multiplication and empty parentheses "()" which are empty and
	 * should be removed.
	 */
	private void processEquationForImplicitMultiplication() {
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm leftTerm = null;
		MathematicalTerm rightTerm = null;
		while (equationIterator.hasNext()) {
			leftTerm = rightTerm;
			rightTerm = equationIterator.next();
			// check if two terms have been found
			if (leftTerm != null && rightTerm != null) {
				// test for dual parentheses
				if (leftTerm.isParenthesis() != null && rightTerm.isParenthesis() != null) {
					// test for empty parentheses
					if (leftTerm.isParenthesis() == BasicMath.getOpenParen()
							&& rightTerm.isParenthesis() == BasicMath.getCloseParen()) {
						// empty parentheses are meaningless and must be removed
						equationIterator.remove();
						equationIterator.previous();
					}
					// test for back to back parentheses, which imply
					// multiplication
					else if (leftTerm.isParenthesis() == BasicMath.getCloseParen()
							&& rightTerm.isParenthesis() == BasicMath.getOpenParen()) {
						// back to back parentheses imply multiplication so add
						// that operation between them
						equationIterator.previous();
						equationIterator.add(new MathematicalTerm("*"));
					}
				}
			}
		}
	}

	/**
	 * Check all subtractions in the equation and process then into addition or
	 * multiplication operations
	 */
	private void processEquationForSubtractions() {
		// deal with "#-" instances
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm leftTerm = null;
		MathematicalTerm rightTerm = null;
		while (equationIterator.hasNext()) {
			leftTerm = rightTerm;
			rightTerm = equationIterator.next();
			// check if two terms have been found
			if (leftTerm != null && rightTerm != null) {
				// check if there is a number on the left
				// either '#' or ')' counts as a number
				boolean numberOnLeft = false;
				if (leftTerm.isNumber() != null) {
					numberOnLeft = true;
				} else if (leftTerm.isParenthesis() != null) {
					if (leftTerm.isParenthesis().charValue() == BasicMath.getCloseParen()) {
						numberOnLeft = true;
					}
				}
				// check if a replacement should be made
				if (numberOnLeft && rightTerm.isSubtraction()) {
					// replace the subtraction
					equationIterator.set(new MathematicalTerm("+"));
					equationIterator.add(new MathematicalTerm("-1"));
					equationIterator.add(new MathematicalTerm("*"));
				}
			}
		}
		// deal with "(-" instances
		equationIterator = equation.listIterator();
		leftTerm = null;
		rightTerm = null;
		while (equationIterator.hasNext()) {
			leftTerm = rightTerm;
			rightTerm = equationIterator.next();
			// check if two terms have been found
			if (leftTerm != null && rightTerm != null) {
				// check if there is a number on the left
				// either '#' or ')' counts as a number
				// check if there is a subtraction on the right
				boolean subtractionOnRight = false;
				if (rightTerm.isSubtraction()) {
					subtractionOnRight = true;
				}
				// check if there is an open parethesis on the left
				boolean openParenOnRight = false;
				if (leftTerm.isParenthesis() != null) {
					if (leftTerm.isParenthesis().charValue() == BasicMath.getOpenParen()) {
						openParenOnRight = true;
					}
				}
				// check if a replacement should be made
				if (subtractionOnRight && openParenOnRight) {
					// replace the subtraction
					equationIterator.add(new MathematicalTerm("-1"));
					equationIterator.add(new MathematicalTerm("*"));
				}
			}
		}
	}

	/**
	 * Solves the equation for its result and returns that result. The equation
	 * is set to the result number. If the equation is detected to be invalid
	 * during this then null will be returned and the equation set to an NaN
	 * double.
	 * 
	 * @return return the result of the equation and null if the equation was
	 *         invalidated during solving
	 */
	public Double solveEquationAndSetToResult() {
		Double result = this.solveEquation();
		LinkedList<MathematicalTerm> newEquation = new LinkedList<MathematicalTerm>();
		// test if in evaluation equation was found to be invalid
		if (result == null) {
			// equation was invalid, so make the new equation contain NaN for
			// its 'value'
			newEquation.add(new MathematicalTerm(Double.toString(Double.NaN)));
		} else {
			// equation was valid so set the new equation to the result
			newEquation.add(new MathematicalTerm(Double.toString(result)));
		}
		// set the equation to the new equation
		this.equation = newEquation;
		// return result
		return result;
	}

	/**
	 * Solves the equation for its result and returns that result. The equation
	 * is not altered. If the equation is detected to be invalid during this
	 * then null will be returned
	 * 
	 * @return return the result of the equation and null if the equation was
	 *         invalidated during solving
	 */
	public Double solveEquation() {
		LinkedList<LinkedList<MathematicalTerm>> parenBlocks = new LinkedList<LinkedList<MathematicalTerm>>();
		// add an empty list to hold to result of the equation once it is
		// evaluated
		parenBlocks.add(new LinkedList<MathematicalTerm>());
		// // add parens at start and end of equation to make method evaluate it
		// this.equation.addFirst(new
		// MathematicalTerm(String.valueOf(BasicMath.getOpenParen())));
		// this.equation.addLast(new
		// MathematicalTerm(String.valueOf(BasicMath.getCloseParen())));

		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm currentTerm = null;
		// iterate through terms and evaluate parenthesis blocks
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			if (currentTerm.isParenthesis() != null) {
				char parenChar = currentTerm.isParenthesis().charValue();
				if (parenChar == BasicMath.getOpenParen()) {
					// new paren block, add new block to list
					parenBlocks.add(new LinkedList<MathematicalTerm>());
				}
				if (parenChar == BasicMath.getCloseParen()) {
					// last paren block closed, evaluate it
					Equation currentParenBlockEquation = new Equation(parenBlocks.removeLast());
					double resultOfParenBlock = currentParenBlockEquation.solveEquation();
					/*
					 * add result of paren block to the end of the previous
					 * paren block(whatever operation would have been done on it
					 * is already in place so no operation needs to be added)
					 */
					parenBlocks.getLast().add(new MathematicalTerm(Double.toString(resultOfParenBlock)));

				}
			} else {
				parenBlocks.getLast().add(currentTerm);
			}
		}
		return this.solveEquationNoParen();
	}

	/**
	 * Simplifies the equation be evaluating its operations. Requires that the
	 * equation already have its parenthesis removed(by solving the equation
	 * inside them). If the equation is detected to be invalid during this then
	 * null will be returned
	 * 
	 * @return return the result of the equation and null if the equation was
	 *         invalidated during solving
	 */
	public Double solveEquationNoParen() {
		// return 0 if equation is empty
		if (this.equation.isEmpty()) {
			return 0.0;
		}
		// perform the evaluation of the operations, in PEMDAS order
		this.evaluateExponentNoParen();
		// now that division will be evaluated the equation can be tested for
		// divide by 0
		if (this.isValid() == false) {
			return null;
		}
		this.evaluateMultiplicationDivisionNoParen();
		this.evaluateAdditionSubtractionNoParen();

		return this.equation.getFirst().isNumber().doubleValue();
	}

	/**
	 * Evaluates the exponent operations within the equation cannot handle
	 * parenthesis
	 */
	private void evaluateExponentNoParen() {
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm currentTerm = null;
		// division and multiplication pass through
		// iterate through terms and evaluate division and multiplication
		// operations
		double leftNumber = 0;
		double rightNumber = equationIterator.next().isNumber().doubleValue();
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			// check if the currentTerm is a number
			if (currentTerm.isNumber() != null) {
				rightNumber = currentTerm.isNumber();
			}

			else if (currentTerm.isExponent()) {
				// get the operation char
				char operation;
				if (currentTerm.isMultiplication()) {
					operation = BasicMath.getMultiplyOperation();
				} else {
					operation = BasicMath.getDivideOperation();
				}
				// get the numbers for the operation
				leftNumber = rightNumber;
				rightNumber = equationIterator.next().isNumber();

				// find the result of the operation
				double result = MathematicalTerm.performOperation(leftNumber, operation, rightNumber);

				// replace the numbers and operation with the result
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.set(new MathematicalTerm(Double.toString(result)));
			}
		}
	}

	/**
	 * Evaulates the multiplication and addition operations within the equation
	 * cannot handle parenthesis
	 */
	private void evaluateMultiplicationDivisionNoParen() {
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm currentTerm = null;
		// division and multiplication pass through
		// iterate through terms and evaluate division and multiplication
		// operations
		double leftNumber = 0;
		double rightNumber = equationIterator.next().isNumber().doubleValue();
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			// check if the currentTerm is a number
			if (currentTerm.isNumber() != null) {
				rightNumber = currentTerm.isNumber();
			}

			else if (currentTerm.isMultiplication() || currentTerm.isDivision()) {
				// get the operation char
				char operation;
				if (currentTerm.isMultiplication()) {
					operation = BasicMath.getMultiplyOperation();
				} else {
					operation = BasicMath.getDivideOperation();
				}
				// get the numbers for the operation
				leftNumber = rightNumber;
				rightNumber = equationIterator.next().isNumber();

				// find the result of the operation
				double result = MathematicalTerm.performOperation(leftNumber, operation, rightNumber);

				// replace the numbers and operation with the result
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.set(new MathematicalTerm(Double.toString(result)));

			}
		}
	}

	/**
	 * Evaulates the addition and subtraction operations within the equation
	 * cannot handle parenthesis
	 */
	private void evaluateAdditionSubtractionNoParen() {
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm currentTerm = null;
		// addition and subtraction pass through
		// iterate through terms and evaluate addition and subtraction
		// operations
		double leftNumber = 0;
		double rightNumber = equationIterator.next().isNumber().doubleValue();
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			// check if the currentTerm is a number
			if (currentTerm.isNumber() != null) {
				rightNumber = currentTerm.isNumber();
			}

			else if (currentTerm.isAddition() || currentTerm.isSubtraction()) {
				// get the operation char
				char operation;
				if (currentTerm.isAddition()) {
					operation = BasicMath.getAdditionOperation();
				} else {
					operation = BasicMath.getSubtractOperation();
				}
				// get the numbers for the operation
				leftNumber = rightNumber;
				rightNumber = equationIterator.next().isNumber();

				// find the result of the operation
				double result = MathematicalTerm.performOperation(leftNumber, operation, rightNumber);

				// replace the numbers and operation with the result
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.remove();
				equationIterator.previous();
				equationIterator.set(new MathematicalTerm(Double.toString(result)));

			}
		}
	}

	/**
	 * Check if the equation is valid and has no errors in it.
	 * 
	 * @return return true if the equation is valid and false otherwise
	 */
	public boolean isValid() {
		// check for small equation
		if (this.equation.size() < 3) {
			for (int i = 0; i < this.equation.size(); i++) {
				if (this.equation.get(i).isOperation() != null || this.equation.get(i).isParenthesis() != null) {
					return false;
				}
			}
			return true;
		}
		// check for NaN or infinite numbers
		ListIterator<MathematicalTerm> equationIterator = equation.listIterator();
		MathematicalTerm currentTerm = null;
		// iterate through terms
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			// check if the term is a number
			Double numberCurrentTerm = currentTerm.isNumber();
			if (numberCurrentTerm != null) {
				// term is a number test it for validity
				if (numberCurrentTerm.isNaN() || numberCurrentTerm.isInfinite()) {
					// number is invalid, fail test
					return false;
				}
			}
		}

		// check for double operations and fail test if there are any
		equationIterator = equation.listIterator();
		MathematicalTerm leftTerm = null;
		MathematicalTerm rightTerm = null;
		// iterate through terms
		while (equationIterator.hasNext()) {
			leftTerm = rightTerm;
			rightTerm = equationIterator.next();
			// check if two terms have been found
			if (leftTerm != null && rightTerm != null) {
				// check if terms are operations
				if (leftTerm.isOperation() != null && rightTerm.isOperation() != null) {
					return false;
				}
			}
		}
		// check for invalid parens
		equationIterator = equation.listIterator();
		currentTerm = null;
		// iterate through terms
		int parenCount = 0;
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			Character currentTermCharacterRepresentation = currentTerm.isParenthesis();
			if (currentTermCharacterRepresentation != null) {
				// increment the paren count for every open paren and decrement
				// for close paren
				// incrementing indicates there is a new parenthesis block open
				// and decrement removes that block
				if (currentTermCharacterRepresentation.charValue() == BasicMath.getOpenParen()) {
					parenCount++;
				} else if (currentTermCharacterRepresentation.charValue() == BasicMath.getCloseParen()) {
					parenCount--;
					// check that there is a parenthesis block to close
					if (parenCount < 0) {
						return false;
					}
				}
			}
		}
		// check that all paren blocks were closed
		if (parenCount > 0) {
			return false;
		}

		// check for divide by 0
		equationIterator = equation.listIterator();
		currentTerm = null;
		boolean divideFound = false;
		// iterate through terms
		while (equationIterator.hasNext()) {
			currentTerm = equationIterator.next();
			// check if the term is a division
			if (currentTerm.isDivision()) {
				// term is a division, mark that so that the next term can be
				// tested
				divideFound = true;
			} else if (divideFound) {
				// division was found now test the term after it
				Double currentTermDouble = currentTerm.isNumber();
				// check if the term is a number otherwise an evaluation must
				// performed before it can be known whether their is a divide by
				// 0
				if (currentTermDouble != null) {
					// current term is a number check if it is invalid for
					// division
					if (currentTermDouble.isNaN() || Double.compare(0.0, currentTermDouble.doubleValue()) == 0) {
						// term is invalid, fail test
						return false;
					}
				}
				// divide tested unmark so that the next divide can be found
				divideFound = false;
			}
		}

		// default return true
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.equation.toString();
	}
}