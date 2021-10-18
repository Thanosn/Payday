package model.Player;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Controller;
import model.Cards.Bills;
import model.Cards.DealCards;
import model.Other.Jackpot;

/**
 * The class that controls everything about a player.
 * 
 * @author Thanasis Nakas
 *
 */
public class Player {

	/**
	 * Players name
	 */
	private final String name;

	/**
	 * The id of the player
	 */
	private final int ID;
	/**
	 * Players Money
	 */
	private int currentMoney;

	/**
	 * The value of players loans
	 */
	private int loans;

	/**
	 * The value of players bills
	 */
	private int billsValue;

	/**
	 * The month the player travels in
	 */
	private int currentMonth;

	/**
	 * The day the player is now
	 */
	private int currentDay;

	/**
	 * The list with the dealCards the player owns
	 */
	private ArrayList<DealCards> deals;

	/**
	 * The lists with the bills
	 */
	private ArrayList<Bills> bills;

	/**
	 * <b>Constructor</b><br>
	 * Players Constructor<br>
	 * 
	 * @param name Players name
	 * @param ID   Players ID
	 */
	public Player(String name, int ID) {
		this.name = name;
		this.ID = ID;
		this.currentMoney = 2500;
		this.loans = 0;
		this.billsValue = 0;
		this.currentMonth = 1;
		this.currentDay = 0;
		this.deals = new ArrayList<DealCards>();
		this.bills = new ArrayList<Bills>();
	}

	/**
	 * <b>Transformer</b><br>
	 * Adds a BillCard in players collection<br>
	 * <b>PostCondition</b>:Bill card added<br>
	 * 
	 * @param bill The bill card
	 */
	public void addBillCard(Bills bill) {
		bills.add(bill);
		setBillsValue(getBillsValue() + bill.getEuro());
		Controller.getInstance().drt.updatePlayerBoxInformations(getID());
	}

	/**
	 * <b>Transformer</b><br>
	 * Pays all the bills and discard all the bill cards<br>
	 * <b>PostCondition</b>:Bills paid<br>
	 */
	public void payBills() {
		for (Bills bill : bills) {
			bill.performAction();
			this.bills.remove(bill);
			Controller.getInstance().getUsedMailCardsList().add(bill);
		}
		setBillsValue(0);
	}

	/**
	 * <b>Transformer</b><br>
	 * Discards all the deal cards in the collection<br>
	 * <b>PostCondition</b>:All deal cards has been discarded<br>
	 */
	public void discardDeals() {
		for (DealCards deal : deals) {
			this.deals.remove(deal);
			Controller.getInstance().getUsedDealCardsList().add(deal);
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * Adds a DealCard in players collection<br>
	 * <b>PostCondition</b>:Deal card added<br>
	 * 
	 * @param deal The Deal Card
	 */
	public void addDealCard(DealCards deal) {
		deals.add(deal);
	}

	/**
	 * <b>Transformer</b><br>
	 * The player sells a deal card from his collection<br>
	 * <b>PostCondition</b>:Deal card removed and money added<br>
	 * 
	 * @param choice The number of the card to sell
	 */
	public void sellDealCard(int choice) {
		this.currentMoney += this.deals.get(choice).getEuro();
		this.deals.remove(this.deals.get(choice));
	}

	/**
	 * <b>Transformer</b><br>
	 * Rolls the dice and checks the Jackpot<br>
	 * <b>PostCondition</b>:Returns a random number and checks the jackpot<br>
	 * 
	 * @see controller.Controller#rollDice()
	 * @see controller.Controller#setLastDice(int)
	 * @see model.Other.Jackpot#giveMoneyToPlayer(Player)
	 * 
	 * @return A Random number [1-6]
	 */
	public int rollDice() {
		int d = Controller.getInstance().rollDice();
		Controller.getInstance().setLastDice(d);
		if (d == 6) {
			Jackpot.giveMoneyToPlayer(this);
		}
		return d;
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: Player moved<br>
	 * Move the player<br>
	 * 
	 * @param steps The steps the the player will be moved
	 */
	public void move(int steps) {
		this.setCurrentDay(this.getCurrentDay() + steps);
	}

	/**
	 * <b>Accessor</b><br>
	 * The player gets a new loan<br>
	 * <b>PostCondition</b>: A loan added in the player<br>
	 * 
	 * @param numOfLoans Number of loans the player took
	 */
	public void getNewLoan(int numOfLoans) {
		this.setLoans(this.getLoans() + (numOfLoans * 1000));
		currentMoney += numOfLoans * 1000;
		Controller.getInstance().drt.updatePlayerBoxInformations(getID());
	}

	/**
	 * <b>Transformer</b><br>
	 * <b>PostCondition</b>: The player gets any loans if needed<br>
	 * The player gets the loans that needs after check how many must take.<br>
	 * 
	 * @return True if the player needed to get an loans.<br>
	 *         False otherwise.
	 */
	public boolean checkAndGetLoans() {
		if (this.getCurrentMoney() < 0) {
			int loans = Math.abs(this.getCurrentMoney() / 1000);
			if (this.getCurrentMoney() % 1000 != 0)
				loans++;
			this.getNewLoan(loans);
			JOptionPane.showMessageDialog(Controller.getInstance().drt,
					this.getName() + " had to take " + loans + " loans.");
			return true;
		}
		return false;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns player name<br>
	 * <b>PostCondition</b>: Name returned<br>
	 * 
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players Current money<br>
	 * <b>PostCondition</b>: CurrentMoney returned<br>
	 * 
	 * @return Players CurrentMoney
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets players Current money<br>
	 * <b>PostCondition</b>: CurrentMoney set<br>
	 * 
	 * @param currentMoney Amount of money
	 */
	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
		if (!this.checkAndGetLoans())
			Controller.getInstance().drt.updatePlayerBoxInformations(getID());
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players loans value<br>
	 * <b>PostCondition</b>: Loans returned<br>
	 * 
	 * @return Players Loans
	 */
	public int getLoans() {
		return loans;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets players loans value<br>
	 * <b>PostCondition</b>: Loans set<br>
	 * 
	 * @param loans Amount of loans to set
	 */
	public void setLoans(int loans) {
		this.loans = loans;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players Bills value<br>
	 * <b>PostCondition</b>: Bills Value returned<br>
	 * 
	 * @return Players BillsValue
	 */
	public int getBillsValue() {
		return billsValue;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets players Bills value<br>
	 * <b>PostCondition</b>: Bills Value set<br>
	 * 
	 * @param bills Amount of bills to set
	 */
	public void setBillsValue(int bills) {
		this.billsValue = bills;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players Current month<br>
	 * <b>PostCondition</b>: CurrentMonth returned<br>
	 * 
	 * @return Players CurrentMonth
	 */
	public int getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets players Current month<br>
	 * <b>PostCondition</b>: CurrentMonth set<br>
	 * 
	 * @param currentMonth CurrentMonth of the player
	 */
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players Current day<br>
	 * <b>PostCondition</b>: CurrentDay returned<br>
	 * 
	 * @return Players CurrentDay
	 */
	public int getCurrentDay() {
		return currentDay;
	}

	/**
	 * <b>Transformer</b><br>
	 * Sets players Current day (MAX 31)<br>
	 * <b>PreCondition</b>: currentDay bigger than (-1)<br>
	 * <b>PostCondition</b>: CurrentDay set<br>
	 * 
	 * @param currentDay CurrentDay of the player
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
		if (this.currentDay >= 31) {
			this.currentDay = 31;
		}
		Controller.getInstance().drt.updatePlayerPeon(getID());
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players ID<br>
	 * <b>PostCondition</b>: Players ID returned<br>
	 * 
	 * @return Players ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * <b>Accessor</b><br>
	 * Returns players Deal Cards<br>
	 * <b>PostCondition</b>: Players Deal Cards returned<br>
	 * 
	 * @return the deal cards
	 */
	public ArrayList<DealCards> getDeals() {
		return deals;
	}

}
