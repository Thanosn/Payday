package model.Position;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Deal Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class DealPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * DealPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public DealPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		Controller.getInstance().setStateOfTheGame(STATE.PICK_1_DEAL);
	}

}
