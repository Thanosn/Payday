package model.Other;

import controller.Controller;
import model.Player.Player;

/**
 * The class that controlls the Jackpot.
 * 
 * @author Thanasis Nakas
 *
 */
public class Jackpot {

	/**
	 * Money on the jackpot.
	 */
	private static int moneyOnJackpot;

	/**
	 * <b>Transformer</b><br>
	 * Adds money to the Jackpot<br>
	 * <b>PostCondition</b>: Money have been added to the jackpot<br>
	 * 
	 * @param moneyToAdd Amount of money to add
	 */
	public static void addMoneyOnJackpot(int moneyToAdd) {
		moneyOnJackpot += moneyToAdd;
		Controller.getInstance().drt.updateJackpotTextBox();
	}

	/**
	 * <b>Transformer</b><br>
	 * Gives all the money to the Player from the jackpot<br>
	 * <b>PostCondition</b>: Money have been given to the Player and the jackpot is
	 * empty<br>
	 * 
	 * @param p Player who won the Jackpot
	 */
	public static void giveMoneyToPlayer(Player p) {
		p.setCurrentMoney(p.getCurrentMoney() + moneyOnJackpot);
		moneyOnJackpot = 0;
		Controller.getInstance().drt.updateJackpotTextBox();
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the amount of money that exists in the jackpot<br>
	 * 
	 * @return Money inside the jackpot
	 */
	public static int getMoneyOnJackpot() {
		return moneyOnJackpot;
	}

}
