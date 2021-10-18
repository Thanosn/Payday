package model.Position;

import javax.swing.JOptionPane;

import controller.Controller;
import controller.STATE;
import model.Player.Player;

/**
 * The class that controls everything about the PayDay Position.
 * 
 * @author Thanasis Nakas
 *
 */
public class PayDayPosition extends Position {

	/**
	 * <b>Constructor</b><br>
	 * PayDayPosition constructor<br>
	 * 
	 * @param day       Day of this position in the table.
	 * @param imageName Image pathName
	 */
	public PayDayPosition(int day, String imageName) {
		super(day, imageName);
	}

	/**
	 * <b>Transformer</b><br>
	 * The game pays the player 2500
	 * 
	 * @param player The player in the position
	 * 
	 */
	public void payPlayer(Player player) {
		player.setCurrentMoney(player.getCurrentMoney() + 2500);
	}

	/**
	 * <b>Transformer</b><br>
	 * Player pays his/her bills
	 * 
	 * @param player The player in the position
	 * 
	 */
	public void playerPaysBills(Player player) {
		player.payBills();
	}

	/**
	 * <b>Transformer</b><br>
	 * Player pays his/her loan taxes
	 * 
	 * @param player The player in the position
	 * 
	 */
	public void playersPaysLoanTaxes(Player player) {
		int taxes = (player.getLoans() * 10 / 100);
		JOptionPane.showMessageDialog(Controller.getInstance().drt,
				player.getName() + " must pay " + taxes + " EURO in taxes");
		player.setCurrentMoney(player.getCurrentMoney() - taxes);
	}

	/**
	 * <b>Transformer</b><br>
	 * Player pays his/her loans after get asked the amount
	 * 
	 * @param player The player in the position
	 * 
	 */
	public void playerPaysLoans(Player player) {
		int playersLoans = player.getLoans();
		int loansPaid = 0;
		if (player.getCurrentMoney() >= playersLoans) {
			loansPaid = playersLoans / 1000;
		} else {
			loansPaid = player.getCurrentMoney() / 1000;
		}
		player.setLoans(playersLoans - (loansPaid * 1000));
		player.setCurrentMoney(player.getCurrentMoney() - (loansPaid * 1000));
		JOptionPane.showMessageDialog(Controller.getInstance().drt,
				player.getName() + " paid " + loansPaid + " loans.");
	}

	/**
	 * <b>Transformer</b><br>
	 * Checks if it was the last month of the player
	 * 
	 * @param player The player in the position
	 * @return True if was the last month of the player false otherwise
	 * 
	 */
	public boolean checkIfWasLastMonth(Player player) {
		if (player.getCurrentMonth() > Controller.getInstance().getMonthsToBePlayed())
			return true;
		else
			return false;
	}

	@Override
	public void performAction(Player p1, Player p2, int diceNumber) {
		this.payPlayer(p1);
		this.playerPaysBills(p1);
		this.playersPaysLoanTaxes(p1);
		this.playerPaysLoans(p1);

		p1.setCurrentMonth(p1.getCurrentMonth() + 1);
		Controller.getInstance()
				.setMonthsLeft(Controller.getInstance().getMonthsToBePlayed() - p1.getCurrentMonth() + 1);
		if (this.checkIfWasLastMonth(p1)) {
			p1.discardDeals();
		} else {
			p1.setCurrentDay(0);
		}
		Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
	}
}
