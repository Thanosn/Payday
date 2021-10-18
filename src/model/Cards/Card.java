package model.Cards;

import model.Player.Player;

/**
 * The abstract class the controls all the cards.
 * 
 * @author Thanasis Nakas
 *
 */
public abstract class Card {

	private Player Owner;

	private String Type;
	private String TypeEN;
	private String Message;
	private String Choise1;

	/** Only for DealCards */
	private String Choise2;

	/** Only for DealCards---0:For Mail cards */
	private int Cost;

	/** Euro:For Mail cards---Value:For DealCards */
	private int Euro;
	private String Icon;

	/**
	 * <b>Constructor</b><br>
	 * 
	 * Mail Cards Constructor
	 * 
	 * @param owner   The owner of the Card
	 * @param type    The type of the card
	 * @param typeEN  The English type of the card
	 * @param message The message of the card
	 * @param choise  The choice of the card
	 * @param euro    The euro value of the card
	 * @param icon    The icon of the card
	 */
	public Card(Player owner, String type, String typeEN, String message, String choise, String euro, String icon) {
		Type = type;
		Owner = owner;
		TypeEN = typeEN;
		Message = message;
		Choise1 = choise;
		Euro = Integer.parseInt(euro);
		Icon = icon;
	}

	/**
	 * <b>Constructor</b><br>
	 * DealCards Constructor
	 * 
	 * @param owner   The owner of the Card
	 * @param type    The type of the card
	 * @param typeEN  The English type of the card
	 * @param message The message of the card
	 * @param cost    The Cost Value of the card
	 * @param euro    The euro value of the card
	 * @param choise1 The first available choice of the card
	 * @param choise2 The second available choice of the card
	 * @param icon    The icon of the card
	 *
	 * 
	 */
	public Card(Player owner, String type, String typeEN, String message, String cost, String euro, String choise1,
			String choise2, String icon) {
		Type = type;
		Owner = owner;
		TypeEN = typeEN;
		Message = message;
		Choise1 = choise1;
		Choise2 = choise2;
		Cost = Integer.parseInt(cost);
		Euro = Integer.parseInt(euro);
		Icon = icon;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>:Performs the instructions that this card has given.<br>
	 * Performs the instructions that this card has given.
	 */
	public abstract void performAction();

	/**
	 * <b>Accessor</b><br>
	 * Returns the owner of this card<br>
	 * 
	 * @return The Owner of the card
	 */
	public Player getOwner() {
		return Owner;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets a new owner
	 * 
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		Owner = owner;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the Type of this card<br>
	 * 
	 * @return Type of the card
	 */
	public String getType() {
		return Type;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the English type of this card<br>
	 * 
	 * @return TypeEN of the card
	 */
	public String getTypeEN() {
		return TypeEN;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the message of this card<br>
	 * 
	 * @return Message of the card
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the Choise1 of this card<br>
	 * 
	 * @return Choise1 of the card
	 */
	public String getChoise1() {
		return Choise1;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the Choise2 of this card<br>
	 * 
	 * @return Choise2 of the card
	 */
	public String getChoise2() {
		return Choise2;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns the Cost of this card<br>
	 * 
	 * @return Cost value of the card
	 */
	public int getCost() {
		return Cost;
	}

	/**
	 * <b>Accessor</b><br>
	 * 
	 * Returns the Value in Euro of this card<br>
	 * 
	 * @return Euro value of the card
	 */
	public int getEuro() {
		return Euro;
	}

	/**
	 * <b>Accessor</b><br>
	 * 
	 * Returns the Icon of this card<br>
	 * 
	 * @return Icon of the card
	 */
	public String getIcon() {
		return Icon;
	}

}
