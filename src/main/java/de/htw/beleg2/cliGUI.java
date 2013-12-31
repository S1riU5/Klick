package de.htw.beleg2;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 
public class cliGUI extends JFrame  {
	JButton [][]  board = new JButton[4][4];
	int intBoard [][]   = new int [4][4];

	
	
	
	public void runGUI()
	{
		runloop();
		
	}
	public void runloop()
	{	
		window();
	}
	
	public void window(){
	// Definiere Fenster
	final JFrame frame = new JFrame("Klickibunti");
	// schliße fenster
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		//  create MainMenu 
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
		JMenuItem blue  = new  JMenuItem("BLau");
		JMenuItem purpel = new JMenuItem("Lila");
		JMenuItem green = new JMenuItem("Grün");
		// Link subsub to sub
		ChoseCol.add(Red);		ChoseCol.add(Yellow);
		ChoseCol.add(blue);		ChoseCol.add(purpel);
		ChoseCol.add(green);
	
			

	// einsetzen der größer aus anderem objekt
	frame.setSize (410,200);
	
	frame.setVisible(true);
	
	
		
	}

}
