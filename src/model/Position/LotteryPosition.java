package model.Position;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Lottery Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class LotteryPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * LotteryPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public LotteryPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		Object[] options = { 1, 2, 3, 4, 5, 6 };
		Object num1 = 0; // Number of player1
		do {
			num1 = JOptionPane.showInputDialog(Controller.getInstance().drt, p1.getName() + " please pick a number",
					"Lottery", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			System.out.println(num1);
		} while (num1 == null);

		Object num2 = 0; // Number of player2
		do
			num2 = JOptionPane.showInputDialog(Controller.getInstance().drt,
					p2.getName() + " please pick a number except " + num1, "Lottery", JOptionPane.INFORMATION_MESSAGE,
					null, options, options[0]);
		while (num2 == null || num2 == num1);

		while (diceNumber != (int) num1 && diceNumber != (int) num2) {
			diceNumber = Controller.getInstance().rollDice();
			Controller.getInstance().drt.updateDice(p1.getID(), diceNumber);
		}
		if (diceNumber == (int) num1) {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p1.getName() + " Won");
			p1.setCurrentMoney(p1.getCurrentMoney() + 1000);
		} else {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p2.getName() + " Won");
			p2.setCurrentMoney(p2.getCurrentMoney() + 1000);
		}
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}

}
