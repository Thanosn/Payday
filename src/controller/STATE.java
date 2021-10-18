package controller;

/**
 * The enum with the STATES of the game.
 * 
 * @author Thanasis Nakas
 *
 */
public enum STATE {
	ROLL_DICE("Please roll the dice."), END_TURN("Please end your turn."), PICK_1_DEAL("Please pick a deal card."),
	PICK_1_MAIL("Please draw one mail card."), PICK_2_MAILS("Please draw 2 mail cards."),
	PICK_CARD_TO_SELL("Please pick a deal card from you collection to sell"),
	PICK_1_MAIL_OWN("You will move in the position after you draw one more mail card.\nPlease draw one mail card.");

	/**
	 * The Command that will be shown in the infoBox
	 */
	private final String stateCommand;

	/**
	 * <b>Constructor</b><br>
	 * Creates a new STATE
	 * 
	 * @param command The Command that will be shown in the infoBox
	 */
	STATE(String command) {
		stateCommand = command;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>Postcondition: </b>The command of the state has been returned.<br>
	 * Returns the command of the current STATE
	 * 
	 * @return The command of the STATE
	 */
	public String getStateCommand() {
		return stateCommand;
	}
}
