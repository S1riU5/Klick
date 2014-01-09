package de.htw.beleg2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
//sebsch use the Gameclass!
import de.htw.beleg2.Game;



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
	int Hight = 16;
	int Width = 16;
	int AnzColor = 2;

	public void runGUI() {
		runloop();

	}

	/**
	 * runloop method
	 */
	public void runloop() {
		menu();
	}

	/**
	 * Window method combines the menu and board
	 */

	public void HightWidthselection() {
		//define window
		JFrame hws =new JFrame("Auswahl Feldgr��e");
		
		//define Closeoperation
		hws.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set Layout of the windwo
		hws.setLayout(new FlowLayout());
		
		//creat mainpart
		JPanel selection = new JPanel();
		
		//set Panel Layout
		selection.setLayout(new BorderLayout());
		
		//create internal Panal
		JPanel internSel = new JPanel();
		
		//set internal Layout
		internSel.setLayout(new BoxLayout(internSel, BoxLayout.X_AXIS));
		
		//draw cover for Panel and set Border
		internSel.setBorder(BorderFactory.createTitledBorder("Selection"));
		
		//create textfields
		JTextField highttext = new JTextField();
		JTextField widthtext = new JTextField();
		
		//create JLables for internal Panel
		JLabel hight = new JLabel("H�hen");
		JLabel width = new JLabel("Breite");
		
		// set size of textfield
		highttext.setPreferredSize(new Dimension(100,20));
		widthtext.setPreferredSize(new Dimension(100,20));
		
		// add Lables to internal Panel and add Textfield to internal Panal
		internSel.add(hight);
		internSel.add(highttext);
		internSel.add(width);
		internSel.add(widthtext);
	
		//insert internal panale to covering panel
		selection.add(internSel,BorderLayout.CENTER);
		
		//add panels to frame
		hws.add(selection);
		
		
		//set size of windwo
		hws.setSize(410,100);
		
		//set windwo visible
		hws.setVisible(true);
		
	}
	
		public void menu (){
			// Definiere Fenster
			final JFrame frameMenu = new JFrame("Klickibunti Menu");
			
			// schli�e fenster
			frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			//set Layout window
			frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(),BoxLayout.Y_AXIS));
			
			//Set menu Buttons
			JButton Colors = new JButton("Farben");
			JButton Board = new JButton("Spielfeld");
			JButton Play = new JButton("Spiele");
			JButton Close = new JButton("Schlie�en");
			
			
			// set buttonsize
			Colors.setMaximumSize(new Dimension(250,300));
			Board.setMaximumSize(new Dimension(250,300));
			Play.setMaximumSize(new Dimension(250,300));
			Close.setMaximumSize(new Dimension(250,300));
			
			//add Buttons
			
			frameMenu.add(Colors,Component.CENTER_ALIGNMENT);
			frameMenu.add(Board,Component.CENTER_ALIGNMENT);
			frameMenu.add(Play,Component.CENTER_ALIGNMENT);
			frameMenu.add(Close,Component.CENTER_ALIGNMENT);
			
			// set window
			
			frameMenu.setSize(250,250);
			
			//Display window
			frameMenu.setVisible(true);
			
			
			//add Action Listener
			
			
			
		
	}
		
		public void ColorSelection (){
			// Definiere Fenster
			final JFrame frameMenu = new JFrame("Farbenanzahl");
			
			// schli�e fenster
			frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			//set Layout window
			frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(),BoxLayout.Y_AXIS));
			
			//Set menu Buttons
			JButton tow = new JButton("Farben 2");
			JButton three = new JButton("Farben 3");
			JButton four = new JButton("Farben 4");
			JButton five= new JButton("Farben 5");
			
			
			// set buttonsize
			tow.setMaximumSize(new Dimension(250,300));
			three.setMaximumSize(new Dimension(250,300));
			four.setMaximumSize(new Dimension(250,300));
			five.setMaximumSize(new Dimension(250,300));
			
			//add Buttons
			
			frameMenu.add(tow,Component.CENTER_ALIGNMENT);
			frameMenu.add(three,Component.CENTER_ALIGNMENT);
			frameMenu.add(four,Component.CENTER_ALIGNMENT);
			frameMenu.add(five,Component.CENTER_ALIGNMENT);
			
			// set window
			
			frameMenu.setSize(250,250);
			
			//Display window
			frameMenu.setVisible(true);
			
			
			//add Action Listener
			
			
			
		
	}
	
	

	/**
	 * ButtonBoard creat's the GUI Board
	 * 
	 * @param hight
	 * @param width
	 * @param boardUI
	 */

	public void ButtonBoard() {
		int hight = game.getBoardHeight();
		int width = game.getBoardWidth();
		
		JFrame BoardFrame = new JFrame("Klickibunti");

		BoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton[][] board = new JButton[hight][width];

		BoardFrame.setLayout(new GridLayout(hight, width));

		for (int i = 0; i < hight; i++) {

			for (int j = 0; j < width; j++) {
				// Farben: Blau(24,0,243);Rot(255,17,0);
				// gelb(255,249.43);Lila(247,16,217);gr�n(7,249,55);
				// Schwarz(0,0,0)
				BoardFrame.add(board[i][j] = new JButton("     "));
				switch (game.getValue(i, j)) {
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
