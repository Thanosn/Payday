package model.Position;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Radio Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class RadioPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * RadioPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public RadioPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		int num1;
		int num2;
		Object[] options = { "Roll The Dice" };
		do {
			do
				num1 = JOptionPane.showOptionDialog(Controller.getInstance().drt,
						p1.getName() + " please roll the dice.", "Radio Challenge", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			while (num1 == JOptionPane.CLOSED_OPTION);
			num1 = Controller.getInstance().rollDice();
			Controller.getInstance().drt.updateDice(p1.getID(), num1);
			do
				num2 = JOptionPane.showOptionDialog(Controller.getInstance().drt,
						p2.getName() + " please roll the dice.", "Sweepstackes", JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			while (num2 == JOptionPane.CLOSED_OPTION);
			num2 = Controller.getInstance().rollDice();
			Controller.getInstance().drt.updateDice(p2.getID(), num2);
		} while (num1 == num2);

		if (num1 > num2) {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p1.getName() + " Won");
			p1.setCurrentMoney(p1.getCurrentMoney() + 1000);
		} else {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p2.getName() + " Won");
			p2.setCurrentMoney(p2.getCurrentMoney() + 1000);
		}
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}

}
