package de.htw.beleg2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



/**
 * 
 * @author simon GUI Class
 * 
 */

public class cliGUI extends JFrame {

	/**
	 * Mainloop for the GUI
	 */
	Game game = new Game(4, 4, 2);

	public void runGUI() {
		runloop();

	}

	/**
	 * runloop method
	 */
	public void runloop() {
		window();
	}

	/**
	 * Window method combines the menu and board
	 */

	public void window() {


	}
	public void menu (){
		// Definiere Fenster
		final JFrame frame = new JFrame("Klickibunti");
		// schliße fenster
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create MainMenu
		JMenuBar Datei = new JMenuBar();

		// add to Frame
		frame.setJMenuBar(Datei);

		// create Submenu
		JMenu game = new JMenu("Spiel");
		JMenu ChoseCol = new JMenu("Farben wählen");
		JMenu board = new JMenu("Feld..");
		// Link submenu to Menu

		Datei.add(game);
		game.add(ChoseCol);
		game.add(board);
		// subsubmenu

		JMenuItem Red = new JMenuItem("Rot");
		JMenuItem Yellow = new JMenuItem("Gelb");
		JMenuItem blue = new JMenuItem("BLau");
		JMenuItem purpel = new JMenuItem("Lila");
		JMenuItem green = new JMenuItem("Grün");
		// Link subsub to sub
		ChoseCol.add(Red);
		ChoseCol.add(Yellow);
		ChoseCol.add(blue);
		ChoseCol.add(purpel);
		ChoseCol.add(green);

		// einsetzen der größer aus anderem objekt
		frame.setSize(410, 200);

		frame.setVisible(true);
		
	}
	
	

	/**
	 * ButtonBoard creat's the GUI Board
	 * 
	 * @param hight
	 * @param width
	 * @param boardUI
	 */

	public void ButtonBoard() {
		int hight = game.board.getHeight();
		int width = game.board.getWidth();
		
		JFrame BoardFrame = new JFrame("Klickibunti");

		BoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton[][] board = new JButton[hight][width];

		BoardFrame.setLayout(new GridLayout(hight, width));

		for (int i = 0; i < hight; i++) {

			for (int j = 0; j < width; j++) {
				// Farben: Blau(24,0,243);Rot(255,17,0);
				// gelb(255,249.43);Lila(247,16,217);grün(7,249,55);
				// Schwarz(0,0,0)
				BoardFrame.add(board[i][j] = new JButton("     "));
				switch (game.board.getValAt(i, j)) {
				case 0:
					board[i][j].setBackground(new Color(0, 0, 0));
					break;
				case 1:
					board[i][j].setBackground(new Color(24, 0, 243));
					break;
				case 2:
					board[i][j].setBackground(new Color(255, 249, 43));
					break;
				case 3:
					board[i][j].setBackground(new Color(7, 249, 55));
					break;
				case 4:
					board[i][j].setBackground(new Color(255, 17, 0));
					break;
				case 5:
					board[i][j].setBackground(new Color(255, 17, 0));
					break;
				}

				board[i][j].setPreferredSize(new Dimension(60, 40));
				// board[i][j].addActionListener(new );
			}
		}

		BoardFrame.setSize(410, 200);
		BoardFrame.setVisible(true);

	}

}
