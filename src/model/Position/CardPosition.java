package model.Position;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about a Card Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class CardPosition extends Position {

	/**
	 * The number of the cards to be given to the player
	 */
	private int numOfCards;

	/**
	 * <b>Constructor</b><br>
	 * CardPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 * @param cards     The number of cards to draw
	 */
	public CardPosition(int day, String imageName, int cards) {
		super(day, imageName);
		this.numOfCards = cards;
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		if (numOfCards == 1) {
			Controller.getInstance().setStateOfTheGame(STATE.PICK_1_MAIL);
		} else if (numOfCards == 2) {
			Controller.getInstance().setStateOfTheGame(STATE.PICK_2_MAILS);
		}
	}

}
