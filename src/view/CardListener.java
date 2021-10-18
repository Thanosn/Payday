package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Controller;
import controller.STATE;
import model.Cards.MailCards;

/**
 * A custom actionListener for the card buttons.
 * 
 * @author Thanasis Nakas
 *
 */

class CardListener implements ActionListener {

	ShowCards show;

	int mailCardCount = 0, dealCardCount = 0;

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Checks the kind of event and perform the necessary
	 * tasks Checks the kind of event and perform the necessary tasks
	 * 
	 * @param event The event that triggered
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		show = new ShowCards();
		if (button.getName().equals("Mail")) {
			if (Controller.getInstance().getStateOfTheGame() == STATE.PICK_1_MAIL) {
				int x = 0;
				MailCards m = Controller.getInstance().getMailCardsList().get(x);
				show.showMailCard(m);
				Controller.getInstance().playerPicksMail(x);
				if (!m.getTypeEN().equals("MoveToDealBuyer"))
					Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
			} else if (Controller.getInstance().getStateOfTheGame() == STATE.PICK_2_MAILS) {
				int x = 0;
				MailCards m = Controller.getInstance().getMailCardsList().get(x);
				show.showMailCard(m);
				if (!m.getTypeEN().equals("MoveToDealBuyer")) {
					Controller.getInstance().playerPicksMail(x);
					Controller.getInstance().setStateOfTheGame(STATE.PICK_1_MAIL);
				} else {
					if (Controller.getInstance().getMailCardsList().size() < 2)
						Controller.getInstance().refillCardStack(0);
					Controller.getInstance().setStateOfTheGame(STATE.PICK_1_MAIL_OWN);
				}
			} else if (Controller.getInstance().getStateOfTheGame() == STATE.PICK_1_MAIL_OWN) {
				int x = 1;
				MailCards m = Controller.getInstance().getMailCardsList().get(x);
				if (!m.getTypeEN().equals("MoveToDealBuyer")) {
					show.showMailCard(m);
					Controller.getInstance().playerPicksMail(x);
					x = 0;
					m = Controller.getInstance().getMailCardsList().get(x);
					show.showMailCard(m);
					Controller.getInstance().playerPicksMail(x);
				} else {
					show.showMailCard(m);
					Controller.getInstance().playerPicksMail(x);
				}

			}
		} else if (button.getName().equals("Deal")
				&& Controller.getInstance().getStateOfTheGame() == STATE.PICK_1_DEAL) {
			int x = 0;
			int choice = show.showDealCard(Controller.getInstance().getDealCardsList().get(x));
			Controller.getInstance().playerPicksDeal(x, choice);
			Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
		}
	}
}