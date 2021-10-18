package model.Position;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Other.Jackpot;
import model.Player.Player;

/**
 * The class that controls everything about a Family Casino Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class FamilyCasinoPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * FamilyCasinoPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public FamilyCasinoPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		if (diceNumber % 2 == 0) {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p1.getName() + " gets 500 EURO");
			p1.setCurrentMoney(p1.getCurrentMoney() + 500);
		} else {
			JOptionPane.showMessageDialog(Controller.getInstance().drt, p1.getName() + " pays 500 EURO in the Jackpot");
			p1.setCurrentMoney(p1.getCurrentMoney() - 500);
			Jackpot.addMoneyOnJackpot(500);
		}
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}

}
