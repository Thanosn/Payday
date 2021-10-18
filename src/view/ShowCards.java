package view;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.Controller;
import model.Cards.DealCards;
import model.Cards.MailCards;

/**
 * A class that is necessary for the visual representation of a card.
 * 
 * @author Thanasis Nakas
 *
 */
public class ShowCards {

	/**
	 * <b>Constructor</b>
	 * 
	 */
	public ShowCards() {
		javax.swing.UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		javax.swing.UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.PLAIN, 20));
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>:Shows a mail card. <br>
	 * Shows a mail card to the player
	 * 
	 * @param card A Reference of the card.
	 */
	public void showMailCard(MailCards card) {
		Object[] options = { card.getChoise1() };

		Image image = new ImageIcon(card.getIcon()).getImage();
		image = image.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);

		int n;
		do
			n = JOptionPane.showOptionDialog(Controller.getInstance().drt, card.getMessage(), card.getTypeEN(),
					JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
		while (n == JOptionPane.CLOSED_OPTION);
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>:Shows a deal card.<br>
	 * Shows a deal card to the player
	 * 
	 * @param card A Reference of the card.
	 * @return The choice of the player
	 */
	public int showDealCard(DealCards card) {
		Object[] options = { card.getChoise1(), card.getChoise2() };

		Image image = new ImageIcon(card.getIcon()).getImage();
		image = image.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);

		int n;
		do
			n = JOptionPane.showOptionDialog(Controller.getInstance().drt,
					card.getMessage() + "\nPrice: " + card.getCost() + " EURO \nSell Value: " + card.getEuro()
							+ " EURO \n",
					card.getTypeEN(), JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
		while (n == JOptionPane.CLOSED_OPTION);
		return n;
	}

}
