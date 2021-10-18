
package model.Position;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Buyer Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class BuyerPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * BuyerPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public BuyerPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		Controller.getInstance().setStateOfTheGame(STATE.PICK_CARD_TO_SELL);
	}

}
