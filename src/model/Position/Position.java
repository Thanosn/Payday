package model.Position;

import model.Player.Player;

/**
 * The abstract class that controls every Position.
 * 
 * @author Thanasis Nakas
 *
 */
public abstract class Position {

	/**
	 * The day of this position in the table
	 */
	private int dayPosition;

	/**
	 * The path of the positions image
	 */
	private String imageName;

	/**
	 * <b>Constructor</b><br>
	 * Position constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public Position(int day, String imageName) {
		this.dayPosition = day;
		this.imageName = imageName;
	}

	/**
	 * <b>Accessor</b><br>
	 * Return the day of the position in the table.<br>
	 * <b>PostCondition</b>: dayPosition returned<br>
	 * 
	 * @return day of the position in the table
	 */
	public int getDayPosition() {
		return dayPosition;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets the day of the position in the table.<br>
	 * <b>PostCondition</b>: dayPosition set<br>
	 * 
	 * @param dayPosition day of the position in the table
	 */
	public void setDayPosition(int dayPosition) {
		this.dayPosition = dayPosition;
	}

	/**
	 * <b>Accessor</b><br>
	 * Return the Image name Path of the position.<br>
	 * <b>PostCondition</b>: dayPosition returned<br>
	 * 
	 * @return Image name Path of the position
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>Performs the instructions that this position has given.</b>
	 * 
	 * @param p1         The player that went in the position
	 * @param p2         The second player the gets part (null if is not needed)
	 * @param diceNumber The number of the dice
	 */
	public abstract void performAction(Player p1, Player p2, int diceNumber);

}
