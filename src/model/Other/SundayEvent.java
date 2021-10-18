package model.Other;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.Controller;
import model.Player.Player;

/**
 * The class that creates a new Sunday Match Event.
 * 
 * @author Thanasis Nakas
 *
 */
public class SundayEvent {

	/**
	 * Players choice<br>
	 * 0:No Bet<br>
	 * 1:Team1<br>
	 * 2:Team2<br>
	 * 3:Draw<br>
	 */
	private int choice;

	/**
	 * <b>Constructor</b><br>
	 */
	public SundayEvent() {
		Object[] options = { "Barcelona Wins", "Draw", "Real Wins", "I don't want to bet" };

		Image image = new ImageIcon("images/Barcelona_Real.jpg").getImage();
		image = image.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		int n;
		do
			n = JOptionPane.showOptionDialog(Controller.getInstance().drt,
					"Please bet 500 Euro for the match Barcelona-Real.", "Sunday footbal match", JOptionPane.OK_OPTION,
					0, new ImageIcon(image), options, options[0]);
		while (n == JOptionPane.CLOSED_OPTION);
		this.setChoise(n);
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets the choice of the player<br>
	 * <b>PostCondition</b>:Choice has been set<br>
	 * 
	 * @param choice The Choice of the player
	 */
	public void setChoise(int choice) {
		this.choice = choice;
	}

	/**
	 * <b>Transformer</b><br>
	 * Checks the choice of the player and makes the Transaction with the player<br>
	 * <b>PostCondition</b>:Makes the Transaction with the player<br>
	 * 
	 * @param result The result of the match
	 * @param player The Player that bets
	 */
	public void makeTransaction(int result, Player player) {
		if (choice == 3)
			return;
		else if ((choice == 0 && result == 1) || (choice == 1 && result == 3) || (choice == 2 && result == 2)) {
			Object[] options = { "You win 500 Euro" };
			Image image = new ImageIcon("images/Barcelona_Real.jpg").getImage();
			image = image.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			JOptionPane.showOptionDialog(Controller.getInstance().drt, "Right bet!!!!Barcelona-Real 1-0",
					"Sunday footbal match", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon(image), options, options[0]);
			player.setCurrentMoney(player.getCurrentMoney() + 500);
		} else {
			Object[] options = { "You lost 500 Euro" };
			Image image = new ImageIcon("images/Barcelona_Real.jpg").getImage();
			image = image.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			JOptionPane.showOptionDialog(Controller.getInstance().drt, "Wrong Bet!!!", "Sunday footbal match",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image), options, options[0]);
			player.setCurrentMoney(player.getCurrentMoney() - 500);
		}

	}
}
