package de.htw.beleg2;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.Scanner; //input
import static java.lang.System.out; //output 

public class cliUI {
	/**
	 * 
	 */
	Game game;

	public cliUI() {
		/**
		 * 
		 */
		// Init a Game-Object
		// TODO Method to set easy Playground
		// TODO Testobject for testing Playgrounds
		game = new Game(6, 6, 3); // Size: 6x6; Colors: 1,2,3

		// Start the Gameloop
		// TODO Exception needed? If yes, which?
		runloop();
	}

	private void runloop() {
		/**
		 * 
		 */
		while (true) {
			// Output
			printPlayground();
			// wait on user input
			fetchUserInput();
		}
	}

	private void printPlayground() {
		/**
		 * 
		 */
		for (int i = 0; i < game.board.getWidth(); i++) {
			for (int j = 0; j < game.board.getHeight(); j++) {
				// Print the square
				printPinch(i, j);
			}
			// New Line
			System.out.println();

		}
	}

	private void printPinch(int x, int y) {
		System.out.printf("[%s]", game.board.getValAt(x, y));

	}

	private void fetchUserInput() {
		/**
		 * 
		 */
		try {
			readInt(">>> ");
		} catch (IOException e) {
			errOutput(e.getMessage());
		}
	}

	private int readInt(String outPt) throws IOException {
		/**
		 * @param String
		 *            will be printed as description.
		 * @return Integer gives back integer from user input.
		 * @throws IOException
		 *             if user input can not be parsed to int.
		 */
		int usrInput;

		out.printf("%s", outPt);
		
		
		
	
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		try{
			usrInput=Integer.parseInt(br.readLine());
		}
		catch(NumberFormatException e){
			throw new IOException("Need Integer");
		}
		return usrInput;
	}

	@SuppressWarnings("unused")
	private String readString(String outPt) throws IOException {
		/**
		 * 
		 */
		String usrInput;

		out.printf("%s", outPt);
		Scanner scanstr = new Scanner(System.in);
		usrInput = scanstr.nextLine();
		scanstr.close();
		return usrInput;
	}

	/*
	 * Printing
	 */
	@SuppressWarnings("unused")
	private void stdOutput(String text) {
		/**
		 * Prints a standard message on cli.
		 */
		out.printf("\t\t%s\n", text);
	}

	private void errOutput(String text) {
		/**
		 * Prints a error message on cli.
		 */
		System.out.printf("Error:\t%s!\n", text);
	}

}
