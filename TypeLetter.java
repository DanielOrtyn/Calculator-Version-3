package actions;

import java.awt.event.ActionEvent;

import java.beans.PropertyChangeListener;

import javax.swing.Action;

import graphics.EquationInterface;

/**
 * An action that types a char in the passed equation editor
 * 
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 30, 2017
 *
 */
public class TypeLetter implements Action {
	/**
	 * The equation interface to type the letter to
	 */
	private EquationInterface equationHolder;
	/**
	 * The letter to type when this action is executed
	 */
	private char letterToType;
	/**
	 * Whether the action is enabled or not
	 */
	private boolean enabled = false;

	/**
	 * Creates the action
	 * 
	 * @param display
	 *            the text field to append letters to
	 * @param letterToType
	 *            the letter to append
	 */
	public TypeLetter(EquationInterface equationHolder, char letterToType) {
		this.equationHolder = equationHolder;
		this.letterToType = letterToType;
	}

	/**
	 * Performs the action
	 * 
	 * @param e
	 *            does nothing
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.equationHolder.addChar(this.letterToType);
		this.setEnabled(false);
	}

	/**
	 * Does nothing
	 * 
	 * @deprecated Does nothing
	 */
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Does nothing
	 * 
	 * @deprecated Does nothing
	 */
	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the action to be enabled if the passed argument is true and disabled
	 * if false
	 * 
	 * @param enabled
	 *            whether to enable the action or not
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns whether the action enabled
	 * 
	 * @return true if enabled and false otherwise
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Does nothing
	 * 
	 * @deprecated Does nothing
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	/**
	 * Does nothing
	 * 
	 * @deprecated Does nothing
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
