package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Cards.Bills;
import model.Cards.DealCards;
import model.Cards.MailCards;
import model.Other.SundayEvent;
import model.Player.Player;
import model.Position.BuyerPosition;
import model.Position.CardPosition;
import model.Position.DealPosition;
import model.Position.FamilyCasinoPosition;
import model.Position.LotteryPosition;
import model.Position.PayDayPosition;
import model.Position.Position;
import model.Position.RadioPosition;
import model.Position.StartPosition;
import model.Position.SweepstackesPosition;
import model.Position.YardSalePosition;
import view.DrawTable;

/**
 * Controller is the master of the game and controls all of the operations
 * executed
 * 
 * @version 1.0
 * @author Thanasis Nakas
 */

public class Controller {

	/** The current STATE of the game */
	private STATE stateOfTheGame;
	/**
	 * The array with the players (2)
	 */
	public Player[] players = new Player[2];

	/**
	 * The months left to end the game(used for the info box)
	 */
	private int monthsLeft;

	/**
	 * the array with the paths of the images for each position
	 */
	private final String[] positionImages = new String[11];

	/**
	 * The id of the player that plays now
	 */
	private int playsNowId;

	/**
	 * The number of the months that the game will last
	 */
	private int monthsToBePlayed;

	/**
	 * The arrayList with the Position objects of each position in the table
	 */
	private ArrayList<Position> positionsInTable;

	/**
	 * String array with the information of each mail card
	 */
	String[][] mailCards = new String[48][4];

	/**
	 * String array with the information of each deal card
	 */
	String[][] dealCards = new String[20][8];

	/**
	 * An arrayList containing the MailCards objects of each mail card
	 */
	private ArrayList<MailCards> mailCardsList;

	/**
	 * An arrayList containing the DealCards objects of each deal card
	 */
	private ArrayList<DealCards> dealCardsList;

	/**
	 * An arrayList containing the MalCards objects of each used mail card
	 */
	private ArrayList<MailCards> usedMailCardsList;

	/**
	 * An arrayList containing the DealCards objects of each used deal card
	 */
	private ArrayList<DealCards> usedDealCardsList;

	/**
	 * An object to the DrawTable that will be the game window.
	 */
	public DrawTable drt;

	/**
	 * An instance of the Controller.<br>
	 * The controller now can be used from any Class(Something like the static way).
	 */
	private static Controller ctrl = new Controller();

	/**
	 * The number of the last dice a player rolled for his round.
	 */
	private int lastDice;

	/**
	 * <b>Accessor</b><br>
	 * Return the instance of the main controller<br>
	 * <b>PostCondition</b>: Returns the instance of the main controller
	 * 
	 * @return The instance of the main controller
	 */
	public static Controller getInstance() {
		return ctrl;
	}

	/**
	 * <b>Transformer</b> Decides which player plays first Sets the months And
	 * initializes everything that is needed
	 */

	public void START() {

		getInstance().playsNowId = (rollDice() % 2 == 0 ? 0 : 1);

		do
			getInstance().monthsToBePlayed = Integer
					.parseInt(JOptionPane.showInputDialog("Enter the number of months you want to play(1-3)"));
		while (getInstance().monthsToBePlayed < 1 || getInstance().monthsToBePlayed > 3);

		setMonthsLeft(monthsToBePlayed);

		getInstance().drt = new DrawTable();

		getInstance().init_players();
		getInstance().setStateOfTheGame(STATE.ROLL_DICE);
		getInstance().init_positions();
		getInstance().init_cards();
		getInstance().init_graphics_table();

		getInstance().drt.pack();
		getInstance().drt.setResizable(false);
		getInstance().drt.setVisible(true);

		JOptionPane.showMessageDialog(drt,
				getInstance().players[getInstance().getWhoPlaysNow()].getName() + " plays first.\nLet the game begin.");
	}

	// -----------INITS---------------------
	/**
	 * <b>Transformer</b><br>
	 * Initializes the player objects and draws the necessary panels
	 * 
	 */
	public void init_players() {
		getInstance().players[0] = new Player(JOptionPane.showInputDialog("Enter the name of the first player."), 0);

		getInstance().players[1] = new Player(JOptionPane.showInputDialog("Enter the name of the second player."), 1);

		for (int i = 0; i <= 1; i++) {
			drt.drawPlayerBox(i);
			drt.drawDice(i);
		}
		drt.drawPlayerPawn();

	}

	/**
	 * <b>Transformer</b><br>
	 * Initializes every position and draws it in the panel
	 */
	public void init_positions() {
		getInstance().positionsInTable = new ArrayList<Position>();

		getInstance().positionImages[0] = "images/mc1.png";
		getInstance().positionImages[1] = "images/mc2.png";
		getInstance().positionImages[2] = "images/deal.png";
		getInstance().positionImages[3] = "images/sweep.png";
		getInstance().positionImages[4] = "images/lottery.png";
		getInstance().positionImages[5] = "images/radio.png";
		getInstance().positionImages[6] = "images/buyer.png";
		getInstance().positionImages[7] = "images/casino.png";
		getInstance().positionImages[8] = "images/yard.png";
		getInstance().positionImages[9] = "images/pay.png";
		getInstance().positionImages[10] = "images/start.png";

		for (int i = 1; i < 31; i++) {
			if (i < 5)
				getInstance().positionsInTable.add(new CardPosition(i, getInstance().positionImages[0], 1));
			else if (i < 9)
				getInstance().positionsInTable.add(new CardPosition(i, getInstance().positionImages[1], 2));
			else if (i < 14)
				getInstance().positionsInTable.add(new DealPosition(i, getInstance().positionImages[2]));
			else if (i < 16)
				getInstance().positionsInTable.add(new SweepstackesPosition(i, getInstance().positionImages[3]));
			else if (i < 19)
				getInstance().positionsInTable.add(new LotteryPosition(i, getInstance().positionImages[4]));
			else if (i < 21)
				getInstance().positionsInTable.add(new RadioPosition(i, getInstance().positionImages[5]));
			else if (i < 27)
				getInstance().positionsInTable.add(new BuyerPosition(i, getInstance().positionImages[6]));
			else if (i < 29)
				getInstance().positionsInTable.add(new FamilyCasinoPosition(i, getInstance().positionImages[7]));
			else
				getInstance().positionsInTable.add(new YardSalePosition(i, getInstance().positionImages[8]));
		}
		Collections.shuffle(getInstance().positionsInTable);
		for (int i = 0; i < getInstance().positionsInTable.size(); i++) {
			getInstance().positionsInTable.get(i).setDayPosition(i + 1);
		}
		getInstance().positionsInTable.add(new PayDayPosition(31, getInstance().positionImages[9]));
		getInstance().positionsInTable.add(0, new StartPosition(0, getInstance().positionImages[10]));
		drt.drawPosition(getInstance().positionsInTable);

	}

	/**
	 * <b>Transformer</b><br>
	 * Initializes the cards
	 */
	public void init_cards() {
		getInstance().readFile("input/mail.csv", "Mail");
		getInstance().readFile("input/deal.csv", "Deal");

		getInstance().mailCardsList = new ArrayList<MailCards>(48);
		getInstance().dealCardsList = new ArrayList<DealCards>(20);

		getInstance().usedMailCardsList = new ArrayList<MailCards>(48);
		getInstance().usedDealCardsList = new ArrayList<DealCards>(20);

		for (int i = 0; i < getInstance().mailCards.length; i++) {
			if (getInstance().mailCards[i][1].equals("Bill"))
				getInstance().mailCardsList
						.add(new Bills(null, getInstance().mailCards[i][2], getInstance().mailCards[i][3],
								getInstance().mailCards[i][4], "input/images/" + getInstance().mailCards[i][5]));
			else {
				getInstance().mailCardsList.add(new MailCards(null, getInstance().mailCards[i][0],
						getInstance().mailCards[i][1], getInstance().mailCards[i][2], getInstance().mailCards[i][3],
						getInstance().mailCards[i][4], "input/images/" + getInstance().mailCards[i][5]));
			}
		}

		for (int i = 0; i < getInstance().dealCards.length; i++)
			getInstance().dealCardsList.add(new DealCards(null, getInstance().dealCards[i][0],
					getInstance().dealCards[i][1], getInstance().dealCards[i][2], getInstance().dealCards[i][3],
					getInstance().dealCards[i][4], getInstance().dealCards[i][6], getInstance().dealCards[i][7],
					"input/images/" + getInstance().dealCards[i][5]));

		Collections.shuffle(getInstance().dealCardsList);
		Collections.shuffle(getInstance().mailCardsList);

		getInstance().drt.drawDealCardsInTable(false);
		getInstance().drt.drawMailCardsInTable(false);
	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the background table and the jackpot
	 */
	public void init_graphics_table() {
		drt.drawJackpotImage();
		drt.drawJackpotTextBox();

		drt.drawInfoBox();
		drt.drawBackground();
	}

	// -------------------------------------

	/**
	 * <b>Accessor</b><br>
	 * Return a random number representing the number a dice could have returned<br>
	 * <b>PostConndition</b>: Returns a random number [1-6]
	 * 
	 * @return A random number
	 **/
	public int rollDice() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}

	/**
	 * <b>Transformer</b><br>
	 * Refills a card Stack<br>
	 * <b>PostCondition</b>: The cards stack will been refilled<br>
	 * 
	 * @param mailOrDeal The mail or deal cards stack <br>
	 *                   0:Mails<br>
	 *                   1:Deals
	 */
	public void refillCardStack(int mailOrDeal) {
		if (mailOrDeal == 1) {
			Collections.shuffle(usedDealCardsList);
			for (int i = 0; i < getInstance().usedDealCardsList.size(); i++) {
				getInstance().dealCardsList.add(getInstance().usedDealCardsList.get(i));
			}
			getInstance().usedDealCardsList.clear();
		} else {
			Collections.shuffle(usedMailCardsList);
			for (int i = 0; i < getInstance().usedMailCardsList.size(); i++) {
				getInstance().mailCardsList.add(getInstance().usedMailCardsList.get(i));
			}
			getInstance().usedMailCardsList.clear();
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * Starts the Sunday football match<br>
	 * <b>PostCondition</b>: Starts a Sunday match<br>
	 * 
	 * @param playerId The id of the player who will bet in the match
	 */
	public void sundayEvent(int playerId) {
		SundayEvent sEvent = new SundayEvent();
		switch (getInstance().rollDice()) {
		case 1:
		case 2:
			sEvent.makeTransaction(1, getInstance().players[playerId]);
			break;
		case 3:
		case 4:
			sEvent.makeTransaction(3, getInstance().players[playerId]);
			break;
		case 5:
		case 6:
			sEvent.makeTransaction(2, getInstance().players[playerId]);
			break;
		}
	}

	/**
	 * <b>Observer</b><br>
	 * <b>PostCondition</b>: Ends the game if both players has fished<br>
	 * 
	 * Check if both players have finished the months and ends the game. <br>
	 *
	 */
	public void isGameOver() {
		if (playerFinished(0) && playerFinished(1)) {
			JOptionPane.showMessageDialog(drt, "Player " + whoWon() + " WON");
			getInstance().drt.dispose();
			System.exit(0);
		}
	}

	/**
	 * <b>Observer</b><br>
	 * Checks if the player has finished all the months<br>
	 * <b>PostCondition</b>: Returns true if the player has finished all the
	 * months<br>
	 * 
	 * @param playerId The id of the player
	 * @return True if the player has finished all the months false otherwise
	 */
	public boolean playerFinished(int playerId) {
		return (getInstance().players[playerId].getCurrentMonth() > getInstance().monthsToBePlayed);
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the id of the player that plays now<br>
	 * Return the id of the player that plays now<br>
	 *
	 * 
	 * @return playsNowId The id of the player that plays now
	 */
	public int getWhoPlaysNow() {
		return playsNowId;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Sets the id of the player that plays now<br>
	 * Sets the id of the player that plays now<br>
	 * 
	 * 
	 * @param playerId The ID of the player that plays now.
	 */
	public void setWhoPlaysNow(int playerId) {
		playsNowId = playerId;
	}

	/**
	 * <b>Observer</b><br>
	 * Returns who won the game <br>
	 * <b>PreCondition</b>: The game has ended<br>
	 * <b>PostCondition</b>: Returns which player won or if it was draw<br>
	 * 
	 * @return 1 For Player 1 Won<br>
	 *         2 For player 2 Won<br>
	 *         0 For Draw
	 */
	private int whoWon() {
		int m0 = getInstance().players[0].getCurrentMoney() - getInstance().players[0].getLoans();
		int m1 = getInstance().players[1].getCurrentMoney() - getInstance().players[1].getLoans();

		if (m0 > m1)
			return 1;
		else if (m0 < m1)
			return 2;
		else
			return 0;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the mail cards list.<br>
	 * Return the stack of the mail cards<br>
	 * 
	 * 
	 * @return The mail cards list.
	 */
	public ArrayList<MailCards> getMailCardsList() {
		return mailCardsList;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the deal cards list.<br>
	 * Return the stack of the deal cards<br>
	 * 
	 * @return The deal cards list.
	 */
	public ArrayList<DealCards> getDealCardsList() {
		return dealCardsList;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Player performs the necessary actions for a mail
	 * card.<br>
	 * Player picks a card from the mail cards and performs the necessary
	 * actions.<br>
	 * 
	 * @param x The number of the card in the stack.
	 */
	public void playerPicksMail(int x) {
		MailCards m = getInstance().mailCardsList.get(x);
		m.setOwner(getInstance().players[getInstance().getWhoPlaysNow()]);
		getInstance().mailCardsList.remove(m);
		if (m instanceof Bills) {
			m.getOwner().addBillCard((Bills) m);
		} else {
			m.performAction();
			getInstance().usedMailCardsList.add(m);
		}
		if (getInstance().mailCardsList.size() == 0) {
			getInstance().refillCardStack(0);
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Player performs the necessary actions for a deal
	 * card.<br>
	 * Player picks a card from the deal cards and performs the necessary
	 * actions.<br>
	 * 
	 * @param x      The number of the card in the stack.
	 * @param choice The choice of the player to buy it or not
	 */
	public void playerPicksDeal(int x, int choice) {
		DealCards d = getInstance().dealCardsList.get(x);
		getInstance().dealCardsList.remove(d);
		if (choice == 0) {
			d.setOwner(getInstance().players[getInstance().getWhoPlaysNow()]);
			d.performAction();
		} else if (choice == 1) {
			getInstance().usedDealCardsList.add(d);
		}
		if (getInstance().dealCardsList.size() == 0) {
			getInstance().refillCardStack(1);
		}

	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the current STATE of the game.<br>
	 * Return the current STATE of the game.<br>
	 *
	 * @return The current STATE
	 */
	public STATE getStateOfTheGame() {
		return getInstance().stateOfTheGame;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Sets the current STATE of the game.<br>
	 * Sets the the current STATE of the game.<br>
	 * 
	 * @param stateOfTheGame The current STATE
	 */
	public void setStateOfTheGame(STATE stateOfTheGame) {
		getInstance().stateOfTheGame = stateOfTheGame;
		getInstance().drt.updateInfoBox();
	}

	/**
	 * <b>Accessor</b><br>
	 * Return the months that the players chose to play<br>
	 * <b>PostCondition</b>: Returns the months left to end the game
	 *
	 * @return monthsLeft The months that left to end the game.
	 */
	public int getMonthsLeft() {
		return monthsLeft;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Sets the months left to end the game.<br>
	 * Sets the months left to end the game.<br>
	 * 
	 * @param monthsLeft The months left to end the game
	 */
	public void setMonthsLeft(int monthsLeft) {
		getInstance().monthsLeft = monthsLeft;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the used mail cards list.<br>
	 * Return the stack of the used mail cards<br>
	 * 
	 *
	 * 
	 * @return the used mail card stack.
	 */
	public ArrayList<MailCards> getUsedMailCardsList() {
		return usedMailCardsList;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the used deal cards list.<br>
	 * Return the stack of the used deal cards<br>
	 * 
	 *
	 * 
	 * @return the used deal card stack.
	 */
	public ArrayList<DealCards> getUsedDealCardsList() {
		return usedDealCardsList;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the list of the position objects.<br>
	 * Return the list of the position objects.<br>
	 * 
	 * @return The list of the position objects.
	 */
	public ArrayList<Position> getPositionsInTable() {
		return positionsInTable;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the number of the last dice a player rolled for
	 * his round.<br>
	 * Return the number of the last dice a player rolled for his round.<br>
	 * 
	 * @return Number of the last dice a player rolled for his round.
	 */
	public int getLastDice() {
		return lastDice;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Sets the number of the last dice a player rolled for
	 * his round.<br>
	 * Sets the number of the last dice a player rolled for his round.<br>
	 * 
	 * 
	 * @param lastDice The number of the last dice a player rolled for his round.
	 */
	public void setLastDice(int lastDice) {
		this.lastDice = lastDice;
	}

	/**
	 * <b>Accessor</b><br>
	 * <b>PostCondition</b>: Returns the number of the months that the game will
	 * last.<br>
	 * Return the number of the months that the game will last<br>
	 * Return the number of the months that the game will last
	 * 
	 * @return The number of the months that the game will last.
	 */
	public int getMonthsToBePlayed() {
		return monthsToBePlayed;
	}

	/**
	 * <b>Transformer</b><br>
	 * Reads A file for the information of the images<br>
	 * <b>PostCondition</b>: Read fills the card strings with informations
	 * 
	 * @param path The path of the file
	 * @param type The type of the cards
	 */

	public void readFile(String path, String type) {
		BufferedReader br = null;
		String sCurrentLine;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
		int count = 0;
		try {
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				if (type.equals("Mail"))
					mailCards[count++] = sCurrentLine.split(",");
				else
					dealCards[count++] = sCurrentLine.split(",");
			}
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
