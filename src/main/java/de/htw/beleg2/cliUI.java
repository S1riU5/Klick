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
		game = new Game(10, 10, 3); //(height, width, colors)

		// Start the Gameloop
		// TODO Exception needed? If yes, which?
		runloop();
	}

	private void runloop() {
		/**
		 * 
		 */
		while (true) {
			int[] deleteAt;
			printPlayground();
			try{
				deleteAt = fetchUserInput();
			} catch (IllegalArgumentException e){
				System.out.printf("Error: %s", e);
				continue;
			}
			
			deleteElement(deleteAt[0], deleteAt[1]);
			game.cleanBoard();
		}
	}

	private void printPlayground() {
		/**
		 * 
		 */
		System.out.printf("\n \n");
		System.out.printf("\t");
		for (int i = 0; i < game.getBoardWidth(); i++)
			System.out.printf("%2d ", i);
		System.out.printf("\n \n");
		for (int i = 0; i < game.getBoardHeight(); i++) {
			System.out.printf("-%s-\t", i);
			for (int j = 0; j < game.getBoardWidth(); j++) {
				// Print the square
				printPinch(i, j);
			}
			// New Line
			System.out.println();

		}
	}

	private void printPinch(int x, int y) {
		String[] syms = {" ", "A", "B", "C", "D", "E" };
		System.out.printf("[%s]", syms[game.getValue(x, y)]);

	}
	
	private void deleteElement(int x,int y){
		game.delete(x, y);
	}

	private int[] fetchUserInput() {
		/**
		 * 
		 */
		int x = -1; 
		int y = -1;
		try {
			x = readInt("Vertical- - >>> ");
			if (x > game.getBoardHeight()){
				throw new IllegalArgumentException("Wertebereich überschritten");
			}
			y = readInt("Horizontal- >>> ");
			if (y > game.getBoardWidth()){
				throw new IllegalArgumentException("Wertebereich überschritten");
			}
		} catch (IOException e) {
			errOutput(e.getMessage());
		}
		int[] values = {x,y};
		
		return values;
		
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
		
		
		
		// Do we have to save the BufferedReader?
		// And if yes, what will be the return value ??
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
