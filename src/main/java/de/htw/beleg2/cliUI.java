package de.htw.beleg2;

import java.io.IOException;
import java.util.Scanner; 				//input
import static java.lang.System.out;		//output 

public class cliUI {
	/**
	 * 
	 */
	Game game;

	public cliUI(){
		/**
		 * 
		 */
		// Init a Game-Object
		game = new Game(6, 6, 3);
		// Start the Gameloop
		runloop(); 
	}
	
	private void runloop(){
		/**
		 * 
		 */
		while (true){
			// Output
			printPlayground();
			// wait on user input 
			fetchUserInput();
		}
	}
	
	private void printPlayground(){
		/**
		 * 
		 */
		for (int i = 0; i < game.board.getWidth(); i++){
			for (int j = 0; j < game.board.getHeight(); j++){
				// Print the square
				printPinch(i,j);
			}
			// New Line
			System.out.println();
		
		}
	}
	
	private void printPinch(int x, int y){
		System.out.printf("[%s]", game.board.getValAt(x, y));
		
	}
	
	private void fetchUserInput(){
		/**
		 * 
		 */
		try{
			readInt(">>> ");
		}
		catch (IOException e){
			errOutput(e.getMessage());
		}
	}
	
	private int readInt(String outPt) throws IOException{
		/**
		 * 
		 */
		int usrInput;
		
		out.printf("%s", outPt);
		Scanner scanint = new Scanner(System.in);
		try{
			usrInput = Integer.parseInt(scanint.nextLine());
		}
		catch (NumberFormatException e){
			throw new IOException("Misentry! Want Integer!");
		}
		return usrInput;
	}
	
	private String readString(String outPt) throws IOException{
		/**
		 * 
		 */
		out.printf("%s", outPt);
		Scanner scanstr = new Scanner(System.in);
		String usrInput = scanstr.nextLine();
		return usrInput;
	}
	
	private void stdOutput(String text){
		/*
		 * Prints a standard message on cli.
		 */
		out.printf("\t\t%s\n" , text);
	}
	
	private void errOutput(String text){
		/*
		 * Prints a error message on cli.
		 */
		System.out.printf("Error:\t%s!\n", text);
	}
	
	
}

