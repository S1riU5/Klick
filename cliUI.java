package klickibunti;

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
		runloop(); 
	}
	
	private void runloop(){
		/**
		 * 
		 */
		game = new Game(3, 3, 2);
		
		while (true){
			printPlayground();
			fetchUserInput();
		}
	}
	
	private void printPlayground(){
		/**
		 * 
		 */
		for (int i = 0; i < game.board.getWidth(); i++){
			for (int j = 0; j < game.board.getHeight(); j++){
				// [ Output i, j ]
				printPinch(i,j);
				// if % n >> newline
			}
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
		readInt("test");
	}
	
	private void readInt(String outPt){
		stdOutput(outPt);
		int usrInput;
		try{
			usrInput = System.in.read();
		}
		catch (IOException e){
			errOutput(e.getMessage());
		}
	}
	
	private void stdOutput(String text){
		System.out.printf("\t\t%s\n" , text);
	}
	
	private void errOutput(String text){
		System.out.printf("Error:\t%s!\n", text);
	}
	
}

