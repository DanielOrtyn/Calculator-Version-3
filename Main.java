package mains;

import java.awt.EventQueue;

import graphics.EquationInterface;


/**
 * A simple class to run the software from
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 31, 2017
 *
 */
public class Main {

	/**
	 * A main method that creates and runs an equation interface frame
	 * 
	 * @param args does nothing
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquationInterface frame = new EquationInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
