package de.htw.beleg2;
import static java.lang.Math.random;


public class Game {
/**
 * 
 */
	Board board;
	
	public Game(int height, int width, int cols){
		/**
		 * Game
		 * 
		 * Game-engine for the logical stuff of this game.
		 * 
		 * @param int height	Height of the board
		 * @param int width		WIdth of the board
		 * @param int cols		Amount of the Colors 
		 */
		board = new Board(width, cols);
		// Never build an empty board!
		fillBoard(cols);
	}
	
	private  void fillBoard(int colors){
		for (int i = 0; i < board.getWidth(); i++){
			for (int j = 0; j < board.getHeight(); j++){
				// Integer randomized value 
				// from 1 to the value of colors
				board.area[i][j] = randInt(1, colors);
			}
		}	
	}
	
	private int randInt( int low, int high ){
		/**
		 * randInt
		 * 
		 * Simple Method to get randomized Integer inside 
		 * freely selectable range.
		 * 
		 * @param int low	lowest possible Value
		 * @param int high	highest possible Value
		 * @return int Randomized Integer
		 */
		return low + (int)(random() * ((high - low) + 1));
	}
	
	
	
	public int getColor(int x, int y){
		/**
		 * getColor
		 * 
		 * getter for the Colorvalue of a space of the board
		 * 
		 * @param int x		value of columns 
		 * @param int y		value of rows
		 * @returns int 	value refers to a color
		 */
		if (board.getHeight() == 0 || board.getWidth() == 0)
				throw new IllegalStateException(
						"Spielfeld noch nicht initialisiert!");
		else
			return  board.getValAt(x, y);
	}
	

	
	public class Board{
		/**
		 * Boardclass
		 */
		private int [][] area;
		private int colors; // 0 = empty 
		
		public Board(int width, int height, int cols){
			area = new int[width][height];
			initField(cols);
		}
		
		public Board(int size, int cols) {
			area = new int[size][size];
			initField(cols);
		}
		
		public int getWidth(){
			return area.length;
		}
		
		public int getHeight(){
			return area[0].length;
		}
		
		private void initField(int cols){
			/*
			 * initialize the values of the fields
			 */
			colors = cols;
			for (int i = 0; i<getWidth(); i++){
				for(int j = 0; j<getWidth(); j++ ){
					area[i][j] = randInt(1, cols);
				}
			}
		}
		
		public int getValAt(int x, int y){
			return area[x][y];
		}
		
		public int howManyEqualNeighbors(int x, int y){
			int value = 0;
			String[] nbrs = {"top", "right", "bottom", "left"};
			
			for (int i = 0; i < nbrs.length; i++){
				if (sameValue(x,y, nbrs[i]))
					value += 1;
			}
			return value;
		}
		
		private boolean sameValue(int x, int y, String direction){
			// If the earth a disc, be aware of the abyss!
			if (	(x == 0 				&& direction == "left")  || 
					(x == this.getWidth() 	&& direction == "right") ||
					(y == 0					&& direction == "bottom")||
					(y == this.getHeight()  && direction == "top"))
				return false;
			
			int valueHere = getValAt(x,y);
			int valueThere;
			
			switch (direction){
			//Are the relative coordinates right this way??
			case "top":
				valueThere = getValAt(x  , y+1);
				break;
			case "right":
				valueThere = getValAt(x+1, y);
				break;
			case "bottom":
				valueThere = getValAt(x  , y-1);
				break;
			default:
				valueThere = getValAt(x-1, y);			
			}
			//return true if equal
			return (valueHere == valueThere);
			
		}
	}
	
	
}
