package model.Position;

import model.Player.Player;

/**
 * The class that controls the first Position int the table.
 * 
 * @author Thanasis Nakas
 *
 */
public class StartPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * StartPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public StartPosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		/* Nothing needed to be done here */}

}
