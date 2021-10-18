package model.Cards;

import model.Player.Player;

/**
 * The class that controls the Deal Cards.
 * 
 * @author Thanasis Nakas
 *
 */
public class DealCards extends Card {

	/**
	 * Constructor
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
	 */

	public DealCards(Player owner, String type, String typeEN, String message, String cost, String euro, String choise1,
			String choise2, String icon) {
		super(owner, type, typeEN, message, cost, euro, choise1, choise2, icon);
	}

	@Override
	public void performAction() {
		this.getOwner().setCurrentMoney(this.getOwner().getCurrentMoney() - this.getCost());
		this.getOwner().addDealCard(this);
	}

}
