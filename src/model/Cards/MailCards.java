package model.Cards;

import java.util.ArrayList;

import controller.Controller;
import model.Other.Jackpot;
import model.Player.Player;
import model.Position.BuyerPosition;
import model.Position.DealPosition;
import model.Position.Position;

/**
 * The class that controls the Mail Cards.
 * 
 * @author Thanasis Nakas
 *
 */
public class MailCards extends Card {

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
	public MailCards(Player owner, String type, String typeEN, String message, String choise, String euro,
			String icon) {
		super(owner, type, typeEN, message, choise, euro, icon);
	}

	@Override
	public void performAction() {
		Player p = this.getOwner();
		switch (this.getTypeEN()) {
		case "Charity":
			Jackpot.addMoneyOnJackpot(this.getEuro());
			p.setCurrentMoney(p.getCurrentMoney() - this.getEuro());
			p.checkAndGetLoans();
			break;
		case "PayTheNeighbor":
			Player p2;
			if (this.getOwner().equals(Controller.getInstance().players[0]))
				p2 = Controller.getInstance().players[1];
			else
				p2 = Controller.getInstance().players[0];
			p2.setCurrentMoney(p2.getCurrentMoney() + this.getEuro());
			p.setCurrentMoney(p.getCurrentMoney() - this.getEuro());
			p.checkAndGetLoans();
			p2.checkAndGetLoans();
			break;
		case "MadMoney":
			Player player2;
			if (this.getOwner().equals(Controller.getInstance().players[0]))
				player2 = Controller.getInstance().players[1];
			else
				player2 = Controller.getInstance().players[0];
			player2.setCurrentMoney(player2.getCurrentMoney() - this.getEuro());
			p.setCurrentMoney(p.getCurrentMoney() + this.getEuro());

			p.checkAndGetLoans();
			player2.checkAndGetLoans();

			break;
		case "MoveToDealBuyer":
			ArrayList<Position> positions = Controller.getInstance().getPositionsInTable();
			for (int i = this.getOwner().getCurrentDay() + 1; i < positions.size(); i++) {
				if (positions.get(i) instanceof BuyerPosition || positions.get(i) instanceof DealPosition) {
					this.getOwner().setCurrentDay(i);
					positions.get(i).performAction(this.getOwner(), null, 0);
					break;
				}
			}
			break;
		default:
			break;
		}
	}
}
