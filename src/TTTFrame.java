import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TTTFrame extends JFrame implements ActionListener {

	private Button[] btnPlayButton;
	private Button btnRestart;
	private int buttonCounter;
	private int xScore;
	private int oScore;
	private Label Olabel, Xlabel;

	TTTFrame(int xScore, int oScore) {

		this.xScore = xScore;
		this.oScore = oScore;

		btnPlayButton = new Button[9];
		for (int i = 0; i < 9; i++) {
			btnPlayButton[i] = new Button("" + i);
			btnPlayButton[i].setBackground(Color.white);
			btnPlayButton[i].setForeground(Color.white);
			btnPlayButton[i].addActionListener(this);
			this.add(btnPlayButton[i]);
		}

		Xlabel = new Label("X: " + this.xScore);
		Xlabel.setFont(new Font("Arial", Font.BOLD, 24));
		Xlabel.setForeground(Color.white);
		Xlabel.setBackground(Color.black);
		this.add(Xlabel);

		btnRestart = new Button("Restart");
		btnRestart.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRestart.addActionListener(this);
		this.add(btnRestart);

		Olabel = new Label("O: " + this.oScore);
		Olabel.setFont(new Font("Arial", Font.BOLD, 24));
		Olabel.setForeground(Color.white);
		Olabel.setBackground(Color.black);
		this.add(Olabel);

		this.setLayout(new GridLayout(4, 3));
		this.pack();
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Tic Tac Toe");
		this.setSize(300, 400);
		this.getContentPane().setBackground(Color.black);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Restart")) {
			System.out.println("Restarted");
			deactivateButtons();
			for (int i = 0; i < 9; i++) {
				System.out.println(i);
				btnPlayButton[i].setLabel("" + i);
				btnPlayButton[i].setForeground(Color.white);
				btnPlayButton[i].setBackground(Color.white);
				btnPlayButton[i].addActionListener(this);

				this.buttonCounter = 0;
			}
		} else {

			((Button) e.getSource()).setFont(new Font("Arial", Font.BOLD, 48));
			((Button) e.getSource()).setForeground(Color.black);
			System.out.println(buttonCounter);
			if (buttonCounter % 2 == 0) {
				((Button) e.getSource()).setLabel("X");

			} else {
				((Button) e.getSource()).setLabel("O");

			}
			((Button) e.getSource()).removeActionListener(this);
			buttonCounter++;
			checkField();
		}

	}

	private void checkField() {

		if (buttonsWithIdenticalLabels(0, 1, 2)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(3, 4, 5)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(6, 7, 8)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(0, 3, 6)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(1, 4, 7)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(2, 5, 8)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(0, 4, 8)) {

			deactivateButtons();
		}
		if (buttonsWithIdenticalLabels(2, 4, 6)) {

			deactivateButtons();
		}

		//else: no winning combination detected
	}

	private boolean buttonsWithIdenticalLabels(int i, int j, int k) {
		if (btnPlayButton[i].getLabel() == btnPlayButton[j].getLabel()
				&& btnPlayButton[j].getLabel() == btnPlayButton[k].getLabel()) {

			btnPlayButton[i].setBackground(Color.red);
			btnPlayButton[j].setBackground(Color.red);
			btnPlayButton[k].setBackground(Color.red);

			if (btnPlayButton[i].getLabel().equals("X")) {
				xScore++;
				Xlabel.setText("X: " + xScore);
			} else {
				oScore++;
				Olabel.setText("O: " + oScore);
			}

			return true;
		} else {
			return false;
		}
	}

	private void deactivateButtons() {
		for (int i = 0; i < 9; i++) {
			btnPlayButton[i].removeActionListener(this);
		}
	}

}