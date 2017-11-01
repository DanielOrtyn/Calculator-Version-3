package graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.*;
import math.BasicMath;
import math.Equation;

import javax.swing.SpringLayout;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A graphical interface for equation objects allowing strings to be built
 * either through keyboard input or by clicking the provided buttons(not when
 * any button has focus pressing enter will not trigger that button, but rather
 * evaluate the equation in the display)
 * 
 * @author Daniel Ortyn
 * @version 1.0
 * @since Oct 31, 2017
 */
public class EquationInterface extends JFrame {

	private static final long serialVersionUID = 7377845556146286931L;
	private JPanel contentPane;
	private JTextField equationDisplay;
	private boolean justSolved = true;

	/**
	 * Create the frame.
	 */
	public EquationInterface() {
		// setup Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		// setup display panel
		JPanel display = new JPanel();
		display.setBorder(new EmptyBorder(5, 5, 5, 5));
		display.setLayout(new BorderLayout(0, 0));
		sl_contentPane.putConstraint(SpringLayout.NORTH, display, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, display, 75, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, display, 0, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, display, 0, SpringLayout.WEST, contentPane);
		contentPane.add(display);

		// setup display
		equationDisplay = new JTextField();
		equationDisplay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		equationDisplay.setHorizontalAlignment(SwingConstants.TRAILING);
		equationDisplay.setText("0");
		display.add(equationDisplay, BorderLayout.CENTER);
		equationDisplay.setColumns(10);

		// setup keyboard panel
		JPanel keyBoard = new JPanel();
		keyBoard.setBorder(new EmptyBorder(5, 5, 5, 5));
		sl_contentPane.putConstraint(SpringLayout.NORTH, keyBoard, 0, SpringLayout.SOUTH, display);
		sl_contentPane.putConstraint(SpringLayout.WEST, keyBoard, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, keyBoard, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, keyBoard, 0, SpringLayout.EAST, contentPane);
		contentPane.add(keyBoard);
		SpringLayout sl_keyBoard = new SpringLayout();
		keyBoard.setLayout(sl_keyBoard);

		// Create the Buttons
		// column 0
		JButton btn7 = new JButton("7");
		JButton btn4 = new JButton("4");
		JButton btn1 = new JButton("1");
		JButton btn0 = new JButton("0");
		// column 1
		JButton btn8 = new JButton("8");
		JButton btn5 = new JButton("5");
		JButton btn2 = new JButton("2");
		// column 2
		JButton btn9 = new JButton("9");
		JButton btn6 = new JButton("6");
		JButton btn3 = new JButton("3");
		JButton btnFullStop = new JButton(".");
		// column 3
		JButton btnDivide = new JButton("/");
		JButton btnMultiplication = new JButton("*");
		JButton btnSubtract = new JButton("-");
		JButton btnAddition = new JButton("+");
		// column 4
		JButton btnOpenParen = new JButton("(");
		JButton btnCloseParen = new JButton(")");
		JButton btnExponent = new JButton("^");
		// column 5
		JButton btnSolve = new JButton("=");

		// set up button fonts
		// column 0
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// column 1
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// column 2
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFullStop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// column 3
		btnDivide.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMultiplication.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubtract.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// column 4
		btnOpenParen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCloseParen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExponent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		// column 5
		btnSolve.setFont(new Font("Tahoma", Font.PLAIN, 11));

		// add buttons to keyboard panel
		// column 0
		keyBoard.add(btn7);
		keyBoard.add(btn4);
		keyBoard.add(btn1);
		keyBoard.add(btn0);
		// column 1
		keyBoard.add(btn8);
		keyBoard.add(btn5);
		keyBoard.add(btn2);
		// column 2
		keyBoard.add(btn9);
		keyBoard.add(btn6);
		keyBoard.add(btn3);
		keyBoard.add(btnFullStop);
		// column 3
		keyBoard.add(btnDivide);
		keyBoard.add(btnMultiplication);
		keyBoard.add(btnSubtract);
		keyBoard.add(btnAddition);
		// column 4
		keyBoard.add(btnOpenParen);
		keyBoard.add(btnCloseParen);
		keyBoard.add(btnExponent);
		// column 5
		keyBoard.add(btnSolve);

		// set up button boundaries
		// column 0
		// button 7
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn7, 0, SpringLayout.NORTH, keyBoard);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn7, 0, SpringLayout.WEST, keyBoard);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn7, 45, SpringLayout.NORTH, keyBoard);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn7, 45, SpringLayout.WEST, keyBoard);
		// button 4
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn4, 5, SpringLayout.SOUTH, btn7);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn4, 0, SpringLayout.WEST, btn7);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn4, 45 + 5, SpringLayout.SOUTH, btn7);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn4, 0, SpringLayout.EAST, btn7);
		// button 1
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn1, 5, SpringLayout.SOUTH, btn4);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn1, 0, SpringLayout.WEST, btn4);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn1, 45 + 5, SpringLayout.SOUTH, btn4);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn1, 0, SpringLayout.EAST, btn4);
		// button 0
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn0, 5, SpringLayout.SOUTH, btn1);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn0, 0, SpringLayout.WEST, keyBoard);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn0, 45 + 5, SpringLayout.SOUTH, btn1);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn0, (45 + 5) * 2 - 5, SpringLayout.WEST, keyBoard);
		// column 1
		// button 8
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn8, 0, SpringLayout.NORTH, btn7);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn8, 5, SpringLayout.EAST, btn7);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn8, 0, SpringLayout.SOUTH, btn7);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn8, 45 + 5, SpringLayout.EAST, btn7);
		// button 5
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn5, 0, SpringLayout.NORTH, btn4);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn5, 5, SpringLayout.EAST, btn4);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn5, 0, SpringLayout.SOUTH, btn4);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn5, 45 + 5, SpringLayout.EAST, btn4);
		// button 2
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn2, 0, SpringLayout.NORTH, btn1);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn2, 5, SpringLayout.EAST, btn1);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn2, 0, SpringLayout.SOUTH, btn1);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn2, 45 + 5, SpringLayout.EAST, btn1);
		// column 2
		// button 9
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn9, 0, SpringLayout.NORTH, btn8);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn9, 5, SpringLayout.EAST, btn8);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn9, 0, SpringLayout.SOUTH, btn8);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn9, 45 + 5, SpringLayout.EAST, btn8);
		// button 6
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn6, 0, SpringLayout.NORTH, btn5);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn6, 5, SpringLayout.EAST, btn5);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn6, 0, SpringLayout.SOUTH, btn5);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn6, 45 + 5, SpringLayout.EAST, btn5);
		// button 3
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btn3, 0, SpringLayout.NORTH, btn2);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btn3, 5, SpringLayout.EAST, btn2);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btn3, 0, SpringLayout.SOUTH, btn2);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btn3, 45 + 5, SpringLayout.EAST, btn2);
		// button .
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnFullStop, 0, SpringLayout.NORTH, btn0);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnFullStop, 5, SpringLayout.EAST, btn0);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnFullStop, 0, SpringLayout.SOUTH, btn0);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnFullStop, 45 + 5, SpringLayout.EAST, btn0);
		// column 3
		// button /
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnDivide, 0, SpringLayout.NORTH, btn9);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnDivide, 5, SpringLayout.EAST, btn9);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnDivide, 0, SpringLayout.SOUTH, btn9);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnDivide, 45 + 5, SpringLayout.EAST, btn9);
		// button *
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnMultiplication, 0, SpringLayout.NORTH, btn6);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnMultiplication, 5, SpringLayout.EAST, btn6);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnMultiplication, 0, SpringLayout.SOUTH, btn6);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnMultiplication, 45 + 5, SpringLayout.EAST, btn6);
		// button -
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnSubtract, 0, SpringLayout.NORTH, btn3);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnSubtract, 5, SpringLayout.EAST, btn3);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnSubtract, 0, SpringLayout.SOUTH, btn3);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnSubtract, 45 + 5, SpringLayout.EAST, btn3);
		// button +
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnAddition, 0, SpringLayout.NORTH, btnFullStop);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnAddition, 5, SpringLayout.EAST, btnFullStop);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnAddition, 0, SpringLayout.SOUTH, btnFullStop);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnAddition, 45 + 5, SpringLayout.EAST, btnFullStop);
		// column 4
		// button (
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnOpenParen, 0, SpringLayout.NORTH, btnDivide);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnOpenParen, 5, SpringLayout.EAST, btnDivide);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnOpenParen, 0, SpringLayout.SOUTH, btnDivide);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnOpenParen, 45 + 5, SpringLayout.EAST, btnDivide);
		// button )
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnCloseParen, 0, SpringLayout.NORTH, btnMultiplication);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnCloseParen, 5, SpringLayout.EAST, btnMultiplication);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnCloseParen, 0, SpringLayout.SOUTH, btnMultiplication);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnCloseParen, 45 + 5, SpringLayout.EAST, btnMultiplication);
		// button ^
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnExponent, 0, SpringLayout.NORTH, btnSubtract);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnExponent, 5, SpringLayout.EAST, btnSubtract);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnExponent, 0, SpringLayout.SOUTH, btnSubtract);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnExponent, 45 + 5, SpringLayout.EAST, btnSubtract);
		// column 5
		// button =
		sl_keyBoard.putConstraint(SpringLayout.NORTH, btnSolve, 0, SpringLayout.NORTH, btnOpenParen);
		sl_keyBoard.putConstraint(SpringLayout.WEST, btnSolve, 5, SpringLayout.EAST, btnOpenParen);
		sl_keyBoard.putConstraint(SpringLayout.SOUTH, btnSolve, 0, SpringLayout.SOUTH, btnFullStop);
		sl_keyBoard.putConstraint(SpringLayout.EAST, btnSolve, 45 + 5, SpringLayout.EAST, btnOpenParen);

		// setup listeners and key bindings
		this.setupKeyboardBindings();
		EquationInterface thisThing = this;
		// 0 key
		// create the listener
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create the action to use
				Action typeAction = new TypeLetter(thisThing, '0');
				// enable the action
				typeAction.setEnabled(true);
				// perform the action
				typeAction.actionPerformed(null);
			}
		});
		// 1 key
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '1');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 2 key
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '2');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 3 key
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '3');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 4 key
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '4');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 5 key
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '5');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 6 key
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '6');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 7 key
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '7');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 8 key
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '8');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// 9 key
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '9');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// . key
		btnFullStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '.');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// / key
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '/');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// * key
		btnMultiplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '*');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// - key
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '-');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// + key
		btnAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '+');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});

		// ( key
		btnOpenParen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '(');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// ) key
		btnCloseParen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, ')');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// ^ key
		btnExponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new TypeLetter(thisThing, '^');
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});
		// = key
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action typeAction = new EvaluateEquation(thisThing);
				typeAction.setEnabled(true);
				typeAction.actionPerformed(null);
			}
		});

	}

	/**
	 * Set up the key bindings
	 */
	private void setupKeyboardBindings() {
		// bind keys
		// 0 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 0"), "pressed 0");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 0"), "released 0");
		// 1 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 1"), "pressed 1");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 1"), "released 1");
		// 2 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 2"), "pressed 2");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 2"), "released 2");
		// 3 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 3"), "pressed 3");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 3"), "released 3");
		// 4 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 4"), "pressed 4");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 4"), "released 4");
		// 5 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 5"), "pressed 5");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 5"), "released 5");
		// 6 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 6"), "pressed 6");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 6"), "released 6");
		// 7 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 7"), "pressed 7");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 7"), "released 7");
		// 8 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 8"), "pressed 8");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 8"), "released 8");
		// 9 key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed 9"), "pressed 9");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released 9"), "released 9");
		// + key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("shift pressed EQUALS"), "pressed PLUS");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("shift released EQUALS"), "released PLUS");
		// - key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed MINUS"), "pressed MINUS");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released MINUS"), "released MINUS");
		// * key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("shift pressed 8"), "pressed ASTERISK");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("shift released 8"), "released ASTERISK");
		// / key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed SLASH"), "pressed SLASH");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released SLASH"), "released SLASH");
		// = key
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed EQUALS");
		((JPanel) this.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released ENTER"), "released EQUALS");

		// set up actions for key bindings
		JPanel actionHolder = (JPanel) this.getContentPane();
		// // 0 key
		// Action typeAction = new TypeLetter(this, '0');
		// Action enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 0", enableTypeAction);
		// actionHolder.getActionMap().put("released 0", typeAction);
		// // 1 key
		// typeAction = new TypeLetter(this, '1');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 1", enableTypeAction);
		// actionHolder.getActionMap().put("released 1", typeAction);
		// // 2 key
		// typeAction = new TypeLetter(this, '2');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 2", enableTypeAction);
		// actionHolder.getActionMap().put("released 2", typeAction);
		// // 3 key
		// typeAction = new TypeLetter(this, '3');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 3", enableTypeAction);
		// actionHolder.getActionMap().put("released 3", typeAction);
		// // 4 key
		// typeAction = new TypeLetter(this, '4');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 4", enableTypeAction);
		// actionHolder.getActionMap().put("released 4", typeAction);
		// // 5 key
		// typeAction = new TypeLetter(this, '5');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 5", enableTypeAction);
		// actionHolder.getActionMap().put("released 5", typeAction);
		// // 6 key
		// typeAction = new TypeLetter(this, '6');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 6", enableTypeAction);
		// actionHolder.getActionMap().put("released 6", typeAction);
		// // 7 key
		// typeAction = new TypeLetter(this, '7');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 7", enableTypeAction);
		// actionHolder.getActionMap().put("released 7", typeAction);
		// // 8 key
		// typeAction = new TypeLetter(this, '8');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 8", enableTypeAction);
		// actionHolder.getActionMap().put("released 8", typeAction);
		// // 9 key
		// typeAction = new TypeLetter(this, '9');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed 9", enableTypeAction);
		// actionHolder.getActionMap().put("released 9", typeAction);
		// // + key
		// typeAction = new TypeLetter(this, '+');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed PLUS", enableTypeAction);
		// actionHolder.getActionMap().put("released PLUS", typeAction);
		// // - key
		// typeAction = new TypeLetter(this, '-');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed MINUS", enableTypeAction);
		// actionHolder.getActionMap().put("released MINUS", typeAction);
		// // * key
		// typeAction = new TypeLetter(this, '*');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed ASTERISK",
		// enableTypeAction);
		// actionHolder.getActionMap().put("released ASTERISK", typeAction);
		// // / key
		// typeAction = new TypeLetter(this, '/');
		// enableTypeAction = new SetActionEnable(typeAction, true);
		// actionHolder.getActionMap().put("pressed SLASH", enableTypeAction);
		// actionHolder.getActionMap().put("released SLASH", typeAction);
		// = key
		Action typeAction = new EvaluateEquation(this);
		Action enableTypeAction = new SetActionEnable(typeAction, true);
		actionHolder.getActionMap().put("pressed EQUALS", enableTypeAction);
		actionHolder.getActionMap().put("released EQUALS", typeAction);
	}

	/**
	 * Evaluate the equation shown in the display
	 * 
	 * @return the value found by the equation
	 */
	public synchronized String evaluateEquation() {
		Equation newEquation = new Equation(this.equationDisplay.getText());
		String returnVal;
		// find what the result of the equation should be displayed as
		// check if the equation is invalid
		if (newEquation.isValid() == false) {
			returnVal = "Invalid";
		} else {
			// equation is valid solve it
			Double equationResult = newEquation.solveEquationAndSetToResult();
			if (equationResult == null) {
				// equation was detected to be invalid during solving
				// so report that
				returnVal = "Invalid";
			} else {
				// equation is completely valid report its 'solution'
				returnVal = equationResult.toString();
			}
		}
		// set display to the string that has been determined
		this.equationDisplay.setText(returnVal);
		// mark this object as having recently solved an equation
		this.justSolved = true;
		// return the string that is being displayed
		return returnVal;
	}

	/**
	 * Checks if the display should be cleared before adding the next letter
	 * 
	 * @param charAdditionBeingConsidered
	 * @return true if the display should be clear and false otherwise
	 */
	public boolean isClearCurrentDisplay(char charAdditionBeingConsidered) {
		// determine if the display should be cleared
		boolean clearDisplay = false;
		if (this.getDisplay().getText().compareTo("Invalid") == 0) {
			clearDisplay = true;
		} else if (this.justSolved && BasicMath.isDigit(charAdditionBeingConsidered)) {
			clearDisplay = true;
		}
		return clearDisplay;
	}

	/**
	 * Get the text field where the equation is displayed
	 * 
	 * @return the equation display
	 */
	public JTextField getDisplay() {
		return this.equationDisplay;
	}

	/**
	 * Adds a char to the equation
	 * 
	 * @param charToAdd
	 */
	public synchronized void addChar(char charToAdd) {
		// check if the display should have the current text cleared
		if (this.isClearCurrentDisplay(charToAdd)) {
			if (BasicMath.isDigit(charToAdd)) {
				// new number entered set the display to that number
				this.getDisplay().setText(Character.toString(charToAdd));
				this.justSolved = false;
			} else {
				// cannot add a non number, so set display to 0
				// just like at startup
				this.getDisplay().setText("0");
			}
		}
		// display didn't need to be cleared so just place the char in like
		// normal
		else {
			this.getDisplay().setText(this.getDisplay().getText() + charToAdd);
			this.justSolved = false;
		}
		this.getDisplay().requestFocus();
		this.getDisplay().setCaretPosition(this.getDisplay().getText().length());
	}
}
