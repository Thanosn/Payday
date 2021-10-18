package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controller.Controller;
import controller.STATE;
import model.Cards.DealCards;
import model.Other.Jackpot;
import model.Player.Player;
import model.Position.Position;

/**
 * The class that represents the main Window of the game.
 * 
 * @author Thanasis Nakas
 *
 */
public class DrawTable extends JFrame {

	private static final long serialVersionUID = 1L;

	private JDesktopPane PlayerPanel[];
	private JLabel lblPlayer[];
	private JLabel lblMoneyEyro[];
	private JLabel lblLoanEuro[];
	private JLabel lblBillsEuro[];
	private JButton RollDiceBtn[];
	private JButton MyDealCardsBtn[];
	private JButton GetLoanBtn[];
	private JButton EndTurnBtn[];
	private JLabel Dice[];

	private JLabel logoLabel;
	private JLabel BgLabel;
	private JDesktopPane positionPanel[];
	private JLabel lblDayName[];
	private JLabel positionIcon[];
	private JLabel JackpotLabelImage;
	private JLabel lblJackpotText;
	private JButton btnDealcardsIcon;
	private JButton btnMailcardsIcon;
	private JLabel lblInfoBox;

	private JLabel lblPlayerPawn[];

	private final Dimension minimunSize = new Dimension(1100, 850);

	/**
	 * <b>Constructor</b><br>
	 * DrawTable Constructor
	 */
	public DrawTable() {
		this.getContentPane().setLayout(null);

		this.getContentPane().setBackground(Color.WHITE);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setMinimumSize(minimunSize);
		init_components();
	}

	/**
	 * <b>Transformer</b><br>
	 * Initializes the main graphic components
	 * 
	 */
	private void init_components() {

		PlayerPanel = new JDesktopPane[2];
		lblPlayer = new JLabel[2];
		lblMoneyEyro = new JLabel[2];
		lblLoanEuro = new JLabel[2];
		lblBillsEuro = new JLabel[2];
		RollDiceBtn = new JButton[2];
		MyDealCardsBtn = new JButton[2];
		GetLoanBtn = new JButton[2];
		EndTurnBtn = new JButton[2];
		Dice = new JLabel[2];

		logoLabel = new JLabel("New label");
		BgLabel = new JLabel("Background");
		positionPanel = new JDesktopPane[32];
		lblDayName = new JLabel[32];
		positionIcon = new JLabel[32];
		JackpotLabelImage = new JLabel("New label");
		btnDealcardsIcon = new JButton();
		btnMailcardsIcon = new JButton();
		lblInfoBox = new JLabel();

		lblPlayerPawn = new JLabel[2];

	}

	/**
	 * <b>Transformer</b><br>
	 * Draws The background of the main table
	 */
	public void drawBackground() {

		logoLabel.setBounds(0, 0, 749, 125);
		logoLabel.setIcon(new ImageIcon(new ImageIcon("images/logo.png").getImage()
				.getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH)));
		this.getContentPane().add(logoLabel);

		BgLabel.setBackground(Color.WHITE);
		BgLabel.setBounds(0, 0, 1203, 1031);
		BgLabel.setIcon(new ImageIcon(new ImageIcon("images/bg_green.png").getImage()
				.getScaledInstance(BgLabel.getWidth(), BgLabel.getHeight(), Image.SCALE_SMOOTH)));
		this.getContentPane().add(BgLabel);
	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the position on a position
	 * 
	 * @param positionsInTable The Positions to draw
	 */
	public void drawPosition(ArrayList<Position> positionsInTable) {

		for (int i = 0; i < 32; i++) {
			positionPanel[i] = new JDesktopPane();
			positionPanel[i].setLayout(null);
			positionPanel[i].setForeground(new Color(0, 204, 204));
			positionPanel[i].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			positionPanel[i].setBackground(Color.WHITE);

			lblDayName[i] = new JLabel();
			lblDayName[i].setOpaque(true);
			lblDayName[i].setBackground(Color.YELLOW);
			lblDayName[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblDayName[i].setFont(new Font("Dialog", Font.PLAIN, 18));
			positionPanel[i].add(lblDayName[i]);

			positionIcon[i] = new JLabel("Icon");
			positionPanel[i].add(positionIcon[i]);

			this.getContentPane().add(positionPanel[i]);
		}

		final int width = (int) (minimunSize.width / 10.28); // 10.28
		final int panelHeight = (int) (minimunSize.height / 6.29); // 6.29
		final int labelHeight = (int) (minimunSize.height / 28.33); // 28.33
		final int iconHeight = (int) (minimunSize.height / 8.09); // 8.09

//		final int xOffset;
		final int yOffset = 125;

		final String[] dayNames = new String[] { "Monday", "Tuesday", "Wednes.", "Thursday", "Friday", "Saturday",
				"Sunday" };

		for (int i = 0; i < positionPanel.length; i++) {
			positionPanel[i].setBounds((i % 7) * width, yOffset + ((i / 7) * panelHeight), width, panelHeight);

			lblDayName[i].setBounds(0, 0, width, labelHeight);
			lblDayName[i].setText(i == 0 ? "Start" : dayNames[i % 7] + i);

			positionIcon[i].setBounds(0, 31, width, iconHeight);
			positionIcon[i].setIcon(new ImageIcon(new ImageIcon(positionsInTable.get(i).getImageName()).getImage()
					.getScaledInstance(positionIcon[i].getWidth(), positionIcon[i].getHeight(), Image.SCALE_SMOOTH)));
		}

	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the jackpot Image
	 */
	public void drawJackpotImage() {
		JackpotLabelImage.setBounds(480, 672, 210, 107);
		JackpotLabelImage.setIcon(new ImageIcon(new ImageIcon("images/jackpot.png").getImage()
				.getScaledInstance(JackpotLabelImage.getWidth(), JackpotLabelImage.getHeight(), Image.SCALE_SMOOTH)));
		this.getContentPane().add(JackpotLabelImage);
	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the Jackpot text box
	 */
	public void drawJackpotTextBox() {
		lblJackpotText = new JLabel();
		lblJackpotText.setHorizontalAlignment(SwingConstants.CENTER);
		lblJackpotText.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblJackpotText.setBackground(new Color(51, 153, 51));
		lblJackpotText.setText("Jackpot: " + Jackpot.getMoneyOnJackpot() + " Euro");
		lblJackpotText.setBounds(480, 790, 210, 18);
		this.getContentPane().add(lblJackpotText);
	}

	/**
	 * <b>Transformer</b><br>
	 * Draw the Deal card stack
	 * 
	 * @param empty True if the stack is empty false if its not
	 */
	public void drawDealCardsInTable(boolean empty) {
		if (!empty) {
			btnDealcardsIcon.setBounds(900, 448, 115, 70);
			btnDealcardsIcon.setName("Deal");
			btnDealcardsIcon.setIcon(new ImageIcon(new ImageIcon("images/dealCard.png").getImage().getScaledInstance(
					btnDealcardsIcon.getWidth(), btnDealcardsIcon.getHeight(), Image.SCALE_DEFAULT)));
			btnDealcardsIcon.addActionListener(new CardListener());
			this.getContentPane().add(btnDealcardsIcon);
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * Draw the Mail card stack
	 * 
	 * @param empty True if the stack is empty false if its not
	 */
	public void drawMailCardsInTable(boolean empty) {
		if (!empty) {
			btnMailcardsIcon.setBounds(765, 448, 115, 70);
			btnMailcardsIcon.setName("Mail");
			btnMailcardsIcon.setIcon(new ImageIcon(new ImageIcon("images/mailCard.png").getImage().getScaledInstance(
					btnMailcardsIcon.getWidth(), btnMailcardsIcon.getHeight(), Image.SCALE_DEFAULT)));
			btnMailcardsIcon.addActionListener(new CardListener());
			this.getContentPane().add(btnMailcardsIcon);
		}
	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the player box
	 * 
	 * @param playerId The id of the player that will own this box InGame
	 */
	public void drawPlayerBox(int playerId) {

		PlayerPanel[playerId] = new JDesktopPane();
		RollDiceBtn[playerId] = new JButton("Roll Dice");
		RollDiceBtn[playerId].setName("RollDice" + playerId);
		MyDealCardsBtn[playerId] = new JButton("My Deal Cards");
		MyDealCardsBtn[playerId].setName("ShowMyDeal" + playerId);
		GetLoanBtn[playerId] = new JButton("Get Loan");
		GetLoanBtn[playerId].setName("GetLoan" + playerId);
		EndTurnBtn[playerId] = new JButton("End Turn");
		EndTurnBtn[playerId].setName("EndTurn" + playerId);

		PlayerPanel[playerId].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		PlayerPanel[playerId].setForeground(new Color(0, 204, 204));
		PlayerPanel[playerId].setBackground(new Color(255, 255, 255));
		if (playerId == 0)
			PlayerPanel[playerId].setBounds(765, 12, 250, 270);
		else
			PlayerPanel[playerId].setBounds(765, 540, 250, 270);
		this.getContentPane().add(PlayerPanel[playerId]);
		PlayerPanel[playerId].setLayout(null);

		lblPlayer[playerId] = new JLabel();
		lblPlayer[playerId].setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer[playerId].setFont(new Font("Dialog", Font.PLAIN, 18));
		lblPlayer[playerId].setText(Controller.getInstance().players[playerId].getName());
		lblPlayer[playerId].setBounds(67, 0, 114, 20);
		PlayerPanel[playerId].add(lblPlayer[playerId]);

		lblMoneyEyro[playerId] = new JLabel();
		lblMoneyEyro[playerId]
				.setText("Money: " + Controller.getInstance().players[playerId].getCurrentMoney() + " Euro");
		lblMoneyEyro[playerId].setBounds(12, 42, 132, 20);
		PlayerPanel[playerId].add(lblMoneyEyro[playerId]);

		lblLoanEuro[playerId] = new JLabel();
		lblLoanEuro[playerId].setText("Loan: " + Controller.getInstance().players[playerId].getLoans() + " Euro");
		lblLoanEuro[playerId].setBounds(12, 82, 132, 20);
		PlayerPanel[playerId].add(lblLoanEuro[playerId]);

		lblBillsEuro[playerId] = new JLabel();
		lblBillsEuro[playerId]
				.setText("Bills: " + Controller.getInstance().players[playerId].getBillsValue() + " Euro");
		lblBillsEuro[playerId].setBounds(12, 126, 132, 20);
		PlayerPanel[playerId].add(lblBillsEuro[playerId]);

		RollDiceBtn[playerId].setBounds(12, 158, 132, 26);
		RollDiceBtn[playerId].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dice;
				if (Controller.getInstance().getWhoPlaysNow() == playerId
						&& Controller.getInstance().getStateOfTheGame() == STATE.ROLL_DICE) {
					dice = Controller.getInstance().players[playerId].rollDice();
					updateDice(playerId, dice);
					Controller.getInstance().players[playerId].move(dice);

					if ((Controller.getInstance().players[playerId].getCurrentDay() + 1) % 7 == 0)
						Controller.getInstance().sundayEvent(playerId);

					Controller.getInstance().getPositionsInTable()
							.get(Controller.getInstance().players[playerId].getCurrentDay())
							.performAction(Controller.getInstance().players[playerId],
									Controller.getInstance().players[playerId == 0 ? 1 : 0],
									Controller.getInstance().getLastDice());
				}
			}
		});
		PlayerPanel[playerId].add(RollDiceBtn[playerId]);

		MyDealCardsBtn[playerId].setBounds(12, 196, 132, 26);
		MyDealCardsBtn[playerId].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Controller.getInstance().getStateOfTheGame() == STATE.PICK_CARD_TO_SELL
						&& Controller.getInstance().getWhoPlaysNow() == playerId) {
					Player player = Controller.getInstance().players[playerId];
					if (player.getDeals().size() != 0) {
						int choice = 0;// TODO Change this.
						Object[] options = new Object[player.getDeals().size()];
						int i = 0;
						for (DealCards deal : player.getDeals()) {
							if (deal != null)
								options[i] = "Name:" + deal.getMessage().substring(5) + "\nCost: " + deal.getCost()
										+ "\nSell Value: " + deal.getEuro();
							i++;
						}

						do
							choice = JOptionPane.showOptionDialog(Controller.getInstance().drt,
									player.getName() + " Choose a card to sell.", "Sell a card.", JOptionPane.OK_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						while (choice == JOptionPane.CLOSED_OPTION);
						Controller.getInstance().players[playerId].sellDealCard(choice);
					}
					Controller.getInstance().setStateOfTheGame(STATE.END_TURN);
					Controller.getInstance().drt.updatePlayerBoxInformations(playerId);
				}
			}
		});
		PlayerPanel[playerId].add(MyDealCardsBtn[playerId]);

		GetLoanBtn[playerId].setBounds(12, 234, 114, 26);
		GetLoanBtn[playerId].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (playerId == Controller.getInstance().getWhoPlaysNow())
					if (JOptionPane.showConfirmDialog(Controller.getInstance().drt, "Do you want to take 1 loan?",
							"Get Loan", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						Controller.getInstance().players[playerId].getNewLoan(1);
			}
		});
		PlayerPanel[playerId].add(GetLoanBtn[playerId]);

		EndTurnBtn[playerId].setBounds(138, 234, 98, 26);
		EndTurnBtn[playerId].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Controller.getInstance().getWhoPlaysNow() == playerId
						&& Controller.getInstance().getStateOfTheGame() == STATE.END_TURN) {

					int id = (playerId == 0 ? 1 : 0);
					if (Controller.getInstance().playerFinished(id))
						id = playerId;
					Controller.getInstance().setWhoPlaysNow(id);

					Controller.getInstance().isGameOver();
					Controller.getInstance().setStateOfTheGame(STATE.ROLL_DICE);
					if (Controller.getInstance().playerFinished(playerId))
						Controller.getInstance().drt.disableAPLayersButtons(playerId);

				}
			}
		});
		PlayerPanel[playerId].add(EndTurnBtn[playerId]);

	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the player pawns
	 *
	 */
	public void drawPlayerPawn() {
		lblPlayerPawn[0] = new JLabel();
		lblPlayerPawn[0].setBounds(0, 175, 50, 70);
		lblPlayerPawn[0].setIcon(new ImageIcon(new ImageIcon("images/pawn_blue.png").getImage()
				.getScaledInstance(lblPlayerPawn[0].getWidth(), lblPlayerPawn[0].getHeight(), Image.SCALE_FAST)));
		this.getContentPane().add(lblPlayerPawn[0]);

		lblPlayerPawn[1] = new JLabel();
		lblPlayerPawn[1].setBounds(47, 175, 50, 70);
		lblPlayerPawn[1].setIcon(new ImageIcon(new ImageIcon("images/pawn_yellow.png").getImage()
				.getScaledInstance(lblPlayerPawn[1].getWidth(), lblPlayerPawn[1].getHeight(), Image.SCALE_FAST)));
		this.getContentPane().add(lblPlayerPawn[1]);

	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the player dice
	 * 
	 * @param playerId The id of the player that will own this dice InGame
	 */
	public void drawDice(int playerId) {
		Dice[playerId] = new JLabel("Dice");

		Dice[playerId].setBounds(180, 158, 56, 64);
		Dice[playerId].setIcon(new ImageIcon(new ImageIcon("images/dice-1.jpg").getImage()
				.getScaledInstance(Dice[playerId].getWidth(), Dice[playerId].getHeight(), Image.SCALE_SMOOTH)));
		PlayerPanel[playerId].add(Dice[playerId]);
	}

	/**
	 * <b>Transformer</b><br>
	 * Draws the main info box
	 */
	public void drawInfoBox() {
		lblInfoBox.setText("<html>InfoBox<br>" + Controller.getInstance().getMonthsLeft() + " Month(s) Left<br>Turn: "
				+ Controller.getInstance().players[Controller.getInstance().getWhoPlaysNow()].getName() + "<br>-->"
				+ Controller.getInstance().getStateOfTheGame().getStateCommand() + "</html>");
		lblInfoBox.setOpaque(true);
		lblInfoBox.setBackground(Color.WHITE);
		lblInfoBox.setBounds(765, 294, 250, 126);
		this.getContentPane().add(lblInfoBox);
	}

	/**
	 * <b>Transformer</b><br>
	 * Updates the Jackpot text box
	 * 
	 */
	public void updateJackpotTextBox() {
		lblJackpotText.setText("Jackpot: " + Jackpot.getMoneyOnJackpot() + " Euro");
	}

	/**
	 * <b>Transformer</b><br>
	 * Updates the player box
	 * 
	 * @param playerId The id of the player that owns this box
	 */
	public void updatePlayerBoxInformations(int playerId) {
		lblMoneyEyro[playerId]
				.setText("Money: " + Controller.getInstance().players[playerId].getCurrentMoney() + " Euro");
		lblLoanEuro[playerId].setText("Loan: " + Controller.getInstance().players[playerId].getLoans() + " Euro");
		lblBillsEuro[playerId]
				.setText("Bills: " + Controller.getInstance().players[playerId].getBillsValue() + " Euro");
	}

	/**
	 * <b>Transformer</b><br>
	 * Updates the player box
	 * 
	 * @param playerId The id of the player that owns this pawn
	 */
	public void updatePlayerPeon(int playerId) {
		lblPlayerPawn[playerId].setBounds(
				positionPanel[Controller.getInstance().players[playerId].getCurrentDay()].getX() + (playerId * 47),
				positionPanel[Controller.getInstance().players[playerId].getCurrentDay()].getY() + 50,
				lblPlayerPawn[playerId].getWidth(), lblPlayerPawn[playerId].getHeight());
	}

	/**
	 * <b>Transformer</b><br>
	 * Updates the player Dice
	 * 
	 * @param playerId The id of the player that owns this dice
	 * @param dice     The number on the dice
	 * 
	 */
	public void updateDice(int playerId, int dice) {
		Dice[playerId].setIcon(new ImageIcon(new ImageIcon("images/dice-" + dice + ".jpg").getImage()
				.getScaledInstance(Dice[playerId].getWidth(), Dice[playerId].getHeight(), Image.SCALE_SMOOTH)));
	}

	/**
	 * <b>Transformer</b><br>
	 * Updates the main info box
	 */
	public void updateInfoBox() {
		lblInfoBox.setText("<html>InfoBox<br>" + (Controller.getInstance().getMonthsLeft()) + " Month(s) Left<br>Turn: "
				+ Controller.getInstance().players[Controller.getInstance().getWhoPlaysNow()].getName() + "<br>-->"
				+ Controller.getInstance().getStateOfTheGame().getStateCommand() + "</html>");
	}

	/**
	 * <b>Transformer</b><br>
	 * Disables the buttons of a player that has finished.
	 * 
	 * @param playerId The id of the player that owns this buttons.
	 */
	public void disableAPLayersButtons(int playerId) {
		RollDiceBtn[playerId].setEnabled(false);
		GetLoanBtn[playerId].setEnabled(false);
		EndTurnBtn[playerId].setEnabled(false);
		MyDealCardsBtn[playerId].setEnabled(false);
	}

}
