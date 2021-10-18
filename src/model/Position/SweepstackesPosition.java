package model.Position;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Sweepstackes Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class SweepstackesPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * SweepstackesPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public SweepstackesPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		Object[] options = { "Roll The Dice" };
		int n;
		do
			n = JOptionPane.showOptionDialog(Controller.getInstance().drt,
					"Please roll the dice and the bank will give you 1000X the number of the dice\nGood Luck",
					"Sweepstackes", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		while (n == JOptionPane.CLOSED_OPTION);
		diceNumber = Controller.getInstance().rollDice();
		Controller.getInstance().drt.updateDice(p1.getID(), diceNumber);
		JOptionPane.showMessageDialog(Controller.getInstance().drt, "You got: " + diceNumber * 1000 + "Euro");
		p1.setCurrentMoney(p1.getCurrentMoney() + (1000 * diceNumber));
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}

}
