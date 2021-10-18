package model.Cards;

import model.Player.Player;

/**
 * The class that controlls the Bills
 * 
 * @author Thanasis Nakas
 *
 */
public class Bills extends MailCards {

	/**
	 * <b>Constructor</b><br>
	 * 
	 * Bill Cards Constructor
	 * 
	 * @param owner   The owner of the Card
	 * @param message The message of the card
	 * @param choise  The choice of the card
	 * @param euro    The euro value of the card
	 * @param icon    The icon of the card
	 */
	public Bills(Player owner, String message, String choise, String euro, String icon) {
		super(owner, "BIll", "Bill", message, choise, euro, icon);
	}

	@Override
	public void performAction() {
		Player p = this.getOwner();
		p.setCurrentMoney(p.getCurrentMoney() - this.getEuro());
	}

}
