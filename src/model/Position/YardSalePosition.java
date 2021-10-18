package model.Position;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Cards.DealCards;
import model.Player.Player;

/**
 * The class that controls everything about a Yard Sale Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class YardSalePosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * YardSalePosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public YardSalePosition(int day, String imageName) {
		super(day, imageName);
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		Object[] options = { "Roll The Dice" };
		int n;
		do
			n = JOptionPane.showOptionDialog(Controller.getInstance().drt,
					"SUPER OFFER\nROLL THE DICE AND GET A DEAL FOR 1000*THE NUMBER YOU GET!", "Yard Sale",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		while (n == JOptionPane.CLOSED_OPTION);
		diceNumber = Controller.getInstance().rollDice();
		Controller.getInstance().drt.updateDice(p1.getID(), diceNumber);
		p1.setCurrentMoney(p1.getCurrentMoney() - (diceNumber * 1000));
		options[0] = "Show my DEAL.";
		JOptionPane.showOptionDialog(Controller.getInstance().drt, "You got a Deal for: " + diceNumber * 1000 + "Euro",
				"Yard Sale", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		DealCards card = Controller.getInstance().getDealCardsList().get(0);
		options[0] = "OK";
		Image image = new ImageIcon(card.getIcon()).getImage();
		image = image.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);

		card.setOwner(p1);
		card.getOwner().addDealCard(card);
		Controller.getInstance().getDealCardsList().remove(card);

		JOptionPane.showOptionDialog(Controller.getInstance().drt,
				card.getMessage() + "\nPrice: " + card.getCost() + " EURO \nSell Value: " + card.getEuro() + " EURO \n",
				card.getTypeEN(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}

}
