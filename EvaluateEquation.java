package actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import graphics.EquationInterface;

/**
 * An action that evaluates the equation created from the display in the
 * equation interface held by this
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 30, 2017
 *
 */
public class EvaluateEquation implements Action {
	/**
	 * The equation interface hodling the equation to evaluate
	 */
	private EquationInterface equationInterface;
	/**
	 * Whether the action is enabled or not
	 */
	private boolean enabled = false;

	/**
	 * Creates the action
	 * 
	 * @param equationHolder
	 *            the interface to retrieve the equation from and send the
	 *            result to
	 */
	public EvaluateEquation(EquationInterface equationHolder) {
		this.equationInterface = equationHolder;
	}

	/**
	 * Evaluate the equation in the interface
	 * 
	 * @param e
	 *            does nothing
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.equationInterface.evaluateEquation();
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
