import java.io.IOException;


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
		game = new Game(3, 3, 2);
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
		//@FIXME clear buffer
		System.out.printf("%s ", outPt);
		int usrInput;
		usrInput = System.in.read();
		return usrInput;
	}
	
	private void stdOutput(String text){
		/*
		 * Prints a standard message on cli.
		 */
		System.out.printf("\t\t%s\n" , text);
	}
	
	private void errOutput(String text){
		/*
		 * Prints a error message on cli.
		 */
		System.out.printf("Error:\t%s!\n", text);
	}
	
	
}

