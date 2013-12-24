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
			setColors(cols);
			for (int i = 0; i<getWidth(); i++){
				for(int j = 0; j<getWidth(); j++ ){
					area[i][j] = randInt(1, cols);
				}
			}
		}
		
		public int getValAt(int x, int y){
			//FIXME IndexOutOfBoundsException
			return area[x][y];

			
		}
		
		
		
			
		public void deleteEqualNeighbors(int x, int y){
			
			//exit conditions first!
			if (valueOfEqualNeighbors(x,y) == 0)
				return;
			//TODO Recursive deleting of equal neighbors
			int currentValue = getValAt(x,y);
			// Delete here first 
			// if not philosophers don't eat the pasta.
			deleteElement(x,y);
			//TODO testing & debugging  
			if (sameValue(currentValue, x,y,'t'))
				deleteEqualNeighbors(x  , y+1);
			if (sameValue(currentValue, x,y,'r'))
				deleteEqualNeighbors(x+1, y);
			if (sameValue(currentValue, x,y,'b'))
				deleteEqualNeighbors(x  , y-1);
			if (sameValue(currentValue, x,y,'l'))
				deleteEqualNeighbors(x-1, y);
			
		}
				
		
		public int valueOfEqualNeighbors(int x, int y){
			/**
			 * valueOfEqualNeighbors
			 * 
			 * Get the amount of the equal Neighbors.
			 * @param int x		column
			 * @param int y		row
			 * @return int	number of equal Neighbors (possible: 0 - 4)
			 */
			int value = 0;
			char[] nbrs = {'t', 'r', 'b', 'l'};
			
			for (int i = 0; i < nbrs.length; i++){
				if (sameValue(getValAt(x,y),x,y, nbrs[i]))
					value += 1;
			}
			return value;
		}
		
		
		private boolean sameValue(int valueHere, int x, int y, char direction)
		throws IllegalStateException{
			/**
			 * sameValue
			 * 
			 * Compares two bordering spaces.
			 * 
			 * @param int x		column
			 * @param int y		row
			 * @param String direction 
			 * (possible: "t",  "r", "b", "l")
			 * @return boolean	are the two spaces equal
			 * @throws IllegalStateException	
			 * if direction is not valid
			 */
			// Directions have to be very implicit!
			//FIXME Exception throws because of nothing
			//if (	direction != 'l' ||
			//		direction != 'r' ||
			//		direction != 'b' ||
			//		direction != 't')
			//	throw new IllegalStateException("Unknown direction");
			System.out.printf("%s\n" , direction);
			// If the earth a disc, be aware of the abyss!
			if (	(x == 0 				&& direction == 'r')  || 
					(x == this.getWidth() 	&& direction == 'l') ||
					(y == 0					&& direction == 'b')||
					(y == this.getHeight()  && direction == 't'))
				return false;
			
			int valueThere;
			
			switch (direction){
			//Are the relative coordinates r this way??
			
			/*  
			 * just for imagination. 
			 * 
			 * 			^(y)
			 * 			|	  t
			 * 			|
			 * 			|	l * r
			 * 			|	 
			 * 			|	  b
			 * ---------+------------>b
			 * 			|			(x)
			 * 			|
			 * 			|
			 * 			
			 *****************************/
			case 't':
				valueThere = getValAt(x  , y+1);
				break;
			case 'r':
				valueThere = getValAt(x+1, y);
				break;
			case 'b':
				valueThere = getValAt(x  , y-1);
				break;
			default:
				valueThere = getValAt(x-1, y);			
			}
			//return true if equal
			return (valueHere == valueThere);
			
		}

		public int getColors() {
			return colors;
		}

		public void setColors(int colors) {
			this.colors = colors;
		}
		
		public void deleteElement(int x, int y){
			this.area[x][y] = 0;
		}
	}
	
	
}
