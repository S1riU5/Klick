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
		masterloop();
	}
	
	private void masterloop(){
		int h,w,c, p;
		System.out.printf("Daten eingeben:\n\n");
		System.out.printf("\t Höhe des Spielfelds:\n");
		h = inputInt(10,40);
		System.out.printf("\t Breite des Spielfelds:\n");
		w = inputInt(10,40);
		System.out.printf("\tAnzahl der Farben:\n");
		c = inputInt(2,5);
		
		game = new Game(h,w,c);
		p = runloop();
	}	
	
	private Integer inputInt(int from , int to){
		/**
		 * 
		 */
		Integer  val = null;
		
		while (true){ 
			try{
				val = readInt(">>> ");
			}
			catch (IOException e){
				System.out.printf("Error: %s\n", e.getMessage());
				continue;
			}
			if (val < from || val > to){
				System.out.printf("Error: %s\n", "Wertebereich überschritten!");
				continue;
			}			
			break;
		};
		return val;
	}
	


	private int runloop() {
		/**
		 * 
		 */
		boolean initRun = true;
		boolean gameover = false;
		
		while (!gameover) {
			int[] deleteAt;
			printPlayground();
			if (!initRun)
				askUndo();
			initRun = false;			
			game.saveBoard();		
			try{
				deleteAt = fetchUserInput();
			} 
			catch (IllegalArgumentException e){
				System.out.printf("Error: %s", e);
				continue;
			}		
			deleteElement(deleteAt[0], deleteAt[1]);
			game.cleanBoard();			
			if (game.status() != 0){
				gameover = true;
			}
		}
		return game.getPoints();
	}
	
	private void askUndo(){
		String answer = "";
		try{
			answer = readString("Undo? y[n] >>> ");
		} 
		catch (IOException e) {
			errOutput(e.getMessage());
		}
		if (answer.equals("y") || answer.equals("Y")){
			game.tardis();
			printPlayground();
		}
	}

	private void printPlayground() {
		/**
		 * 
		 */
		System.out.printf("\n \tPunkte: %08d \n" , game.getPoints());
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


	private String readString(String outPt) throws IOException {
		/**
		 * 
		 */
		String usrInput;

		out.printf("%s", outPt);
		Scanner scanstr = new Scanner(System.in);
		usrInput = scanstr.nextLine();
		//scanstr.close();
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
